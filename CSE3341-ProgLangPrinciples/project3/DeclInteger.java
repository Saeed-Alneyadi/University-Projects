class DeclInteger {
	Id id;
	
	public void parse() {
		Parser.expectedToken(Core.INTEGER);
		Parser.scanner.nextToken();
		id = new Id();
		id.parse();
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}
	
	public void print(int indent) {
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		System.out.print("integer ");
		id.print();
		System.out.println(";");
	}

	public void execute() {
		// Variables Initialization
		Memory.Value value = new Memory.Value();
		value.type = Core.INTEGER;
		value.intValue = 0;
		
		String idName = id.execute();
		// Add the new Integer to global scope or local scope based on $Memory.dsFlag
		if (Memory.dsFlag) {
			Memory.global.put(idName, value);
		} else {
			Memory.local.peek().put(idName, value);
		}
	}
}