class DeclSeq {
	Decl decl;
	DeclSeq ds;
	
	void parse() {
		decl = new Decl();
		decl.parse();
		if (Parser.scanner.currentToken() != Core.BEGIN) {
			ds = new DeclSeq();
			ds.parse();
		}
	}
	
	void print(int indent) {
		decl.print(indent);
		if (ds != null) {
			ds.print(indent);
		}
	}

	void execute() {
		// Decl Execution
		decl.execute();
		
		// DeclSeQ Execution based on whether it is null or not
		if (ds != null) {
			ds.execute();
		}
	}
}