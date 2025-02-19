class Cmpr {
	Expr expr1;
	Expr expr2;
	int option;
	
	void parse() {
		expr1 = new Expr();
		expr1.parse();
		if (Parser.scanner.currentToken() == Core.EQUAL) {
			option = 0;
		} else if (Parser.scanner.currentToken() == Core.LESS) {
			option = 1;
		} else {
			System.out.println("ERROR: Expected EQUAL or LESS, recieved " + Parser.scanner.currentToken());
			System.exit(0);
		}
		Parser.scanner.nextToken();
		expr2 = new Expr();
		expr2.parse();
	}
	
	void print() {
		expr1.print();
		switch(option) {
			case 0:
				System.out.print("=");
				break;
			case 1:
				System.out.print("<");
				break;
		}
		expr2.print();
	}

	boolean execute() {
		// Variables Initialization
		boolean result;
		int exprInt1 = expr1.execute();

		// Execute the equal or less than operator in conditions
		if (option == 0) {
			result = (exprInt1 == expr2.execute());
		} else {
			result = (exprInt1 < expr2.execute());
		}

		// Return Statement
		return result;
	}
}