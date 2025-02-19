class Term {
	Factor factor;
	Term term;
	int option;
	
	void parse() {
		factor  = new Factor();
		factor.parse();
		if (Parser.scanner.currentToken() == Core.MULTIPLY) {
			option = 1;
		} else if (Parser.scanner.currentToken() == Core.DIVIDE) {
			option = 2;
		}
		if (option != 0) {
			Parser.scanner.nextToken();
			term = new Term();
			term.parse();
		}						
	}
	
	void print() {
		factor.print();
		if (option == 1) {
			System.out.print("*");
			term.print();
		} else if (option == 2) {
			System.out.print("/");
			term.print();
		}
	}

	int execute() {
		int result;

		result = factor.execute();
		if (option == 1) {
			result *= term.execute();
		} else if (option == 2) {
			int divisor = term.execute();
			if (divisor != 0) {
				result /= divisor;
			} else {
				System.out.println("ERROR: EXECUTION: Division by 0 Attempt");
				System.exit(0);
			}
		}

		return result;
		// return 1;
	}
}