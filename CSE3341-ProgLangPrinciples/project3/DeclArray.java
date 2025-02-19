class DeclArray {
	Id id;
	
	public void parse() {
		Parser.expectedToken(Core.ARRAY);
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
		System.out.print("array ");
		id.print();
		System.out.println(";");
	}

	public void execute() {
		// Variables Initialization
		Memory.Value value = new Memory.Value();
		value.type = Core.ARRAY;
		
		String idName = id.execute();
		// Add the new Array to global scope or local scope based on $Memory.dsFlag
		if (Memory.dsFlag) {
			Memory.global.put(idName, value);
		} else {
			Memory.local.peek().put(idName, value);
		}
	}
}