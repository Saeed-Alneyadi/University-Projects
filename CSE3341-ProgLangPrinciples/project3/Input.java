public class Input implements Stmt {
	Id id;
	
	public void parse() {
		Parser.expectedToken(Core.IN);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.LPAREN);
		Parser.scanner.nextToken();
		id = new Id();
		id.parse();
		Parser.expectedToken(Core.RPAREN);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}
	
	public void print(int indent) {
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		System.out.print("in(");
		id.print();
		System.out.println(");");
	}

	public void execute() {
		if (Parser.inScanner.currentToken() != Core.EOS) {
			// Getting constant from input file.
			int intValue = Parser.inScanner.getConst();
			Parser.inScanner.nextToken();

			// Identifer Execution
			String idName = id.execute();
			if (Memory.global.containsKey(idName)) {
				Memory.global.get(idName).type = Core.INTEGER;
				Memory.global.get(idName).intValue = intValue;
			} else if (Memory.getValue(idName) != null) {
				Memory.getValue(idName).type = Core.INTEGER;
				Memory.getValue(idName).intValue = intValue;
			} else {
				// Variable with Identifer name doesn't exist
				System.out.println("ERROR: EXECUTION: Variable name with Inputted Identifier (ID) doesn't exist \"" + idName + "\"");
				System.exit(0);
			}
		} else {
			// Handles the Error in case of reaching the end of input stream.
			System.out.println("ERROR: EXECUTION: Input End of Stream is Reached.");
			System.exit(0);
		}
	}
}