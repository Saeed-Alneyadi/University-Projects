class Factor {
	Id id;
	Expr index;
	int constant;
	Expr expr;
	
	void parse() {
		if (Parser.scanner.currentToken() == Core.ID) {
			id = new Id();
			id.parse();
			if (Parser.scanner.currentToken() == Core.LBRACE) {
				Parser.scanner.nextToken();
				index = new Expr();
				index.parse();
				Parser.expectedToken(Core.RBRACE);
				Parser.scanner.nextToken();
				
			}
		} else if (Parser.scanner.currentToken() == Core.CONST) {
			constant = Parser.scanner.getConst();
			Parser.scanner.nextToken();
		} else if (Parser.scanner.currentToken() == Core.LPAREN) {
			Parser.scanner.nextToken();
			expr = new Expr();
			expr.parse();
			Parser.expectedToken(Core.RPAREN);
			Parser.scanner.nextToken();
		} else {
			System.out.println("ERROR: Expected ID, CONST, LPAREN, or IN, recieved " + Parser.scanner.currentToken());
			System.exit(0);
		}
	}
	
	void print() {
		if (id != null) {
			id.print();
			if (index != null) {
				System.out.print("[");
				index.print();
				System.out.print("]");
			}
		} else if (expr != null) {
			System.out.print("(");
			expr.print();
			System.out.print(")");
		} else {
			System.out.print(constant);
		}
	}

	int execute() {
		// Variable Decleration
		int result;
		
		if (id != null) {
			String idName = id.execute();
			Memory.Value value = Memory.getValue(idName);
			if (value.type == Core.INTEGER) {
				result = value.intValue;
			} else if (value.arrayValue != null) {
				if (index != null) {
					int idx = index.execute();
					
					if (idx >= 0 && idx < value.arrayValue.length) {
						result = value.arrayValue[idx];
					} else {
						result = 0;
						System.out.println("ERROR: EXECUTION: Given index " + idx + " is out of Array " + idName + " valid indexes.");
						System.exit(0);
					}
				} else {
					result = value.arrayValue[0];
				}
			} else {
				result = 0;
				System.out.println("ERROR: EXECUTION: Array " + idName + " is not Initialized (null).");
				System.exit(0);
			}
		} else if (expr != null) {
			// Expr Execution
			result = expr.execute();
		} else {
			// Returning Constant
			result = constant;
		}

		// Return Statement
		return result;
	}
}