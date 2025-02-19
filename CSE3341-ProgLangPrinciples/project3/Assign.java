class Assign implements Stmt {
	// type is
	// 0 if id := <expr> assignment
	// 1 if id[<expr>] := <expr> assignment
	// 2 if "new" assignment
	// 3 if "array" assignment
	int type;
	// assignTo is the id on the LHS of assignment
	Id assignTo;
	// assignFrom is the id on RHS of "array" assignment
	Id assignFrom;
	// Two possible expressions in an assignment
	Expr index;
	Expr expr;
	
	public void parse() {
		type = 0;
		assignTo = new Id();
		assignTo.parse();
		if (Parser.scanner.currentToken() == Core.LBRACE) {
			type = 1;
			Parser.scanner.nextToken();
			index = new Expr();
			index.parse();
			Parser.expectedToken(Core.RBRACE);
			Parser.scanner.nextToken();
		}
		Parser.expectedToken(Core.ASSIGN);
		Parser.scanner.nextToken();
		if (Parser.scanner.currentToken() == Core.NEW) {
			type = 2;
			Parser.scanner.nextToken();
			Parser.expectedToken(Core.INTEGER);
			Parser.scanner.nextToken();
			Parser.expectedToken(Core.LBRACE);
			Parser.scanner.nextToken();
			index = new Expr();
			index.parse();
			Parser.expectedToken(Core.RBRACE);
			Parser.scanner.nextToken();
		} else if (Parser.scanner.currentToken() == Core.ARRAY) {
			type = 3;
			Parser.scanner.nextToken();
			assignFrom = new Id();
			assignFrom.parse();
		} else {
			expr = new Expr();
			expr.parse();
		}
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}
	
	public void print(int indent) {
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		assignTo.print();
		if (type == 1) {
			System.out.print("[");
			index.print();
			System.out.print("]");
		}
		System.out.print(":=");
		if (type == 0 || type == 1) {
			expr.print();
		} else if (type == 2) {
			System.out.print("new integer[");
			index.print();
			System.out.print("]");
		} else if (type == 3) {
			System.out.print("array ");
			assignFrom.print();
		}
		System.out.println(";");
	}

	public void execute() {
		// Variable Initialization
		String idName = assignTo.execute();
		Memory.Value valueObj = Memory.getValue(idName);
		int exprInt;
		int indexInt;

		// Choose which tree objects to execute
		if (type == 0) {
			// Execute the integer assignment
			exprInt = expr.execute();
			if (valueObj.type == Core.INTEGER) {
				valueObj.intValue = exprInt;
			} else if (valueObj.type == Core.ARRAY) {
				if (valueObj.arrayValue != null) {
					valueObj.arrayValue[0] = exprInt;
				} else {
					System.out.println("ERROR: EXECUTION: Array " + idName + " is not Initialized (null).");
					System.exit(0);
				}
			}
		} else if (type == 1) {
			// Execute the array element assignment
			exprInt = expr.execute();
			indexInt = index.execute();
			if (indexInt >= 0 && indexInt < valueObj.arrayValue.length) {
				if (valueObj.arrayValue != null) {
					valueObj.arrayValue[indexInt] = exprInt;
				} else {
					System.out.println("ERROR: EXECUTION: Array " + idName + " is not Initialized (null).");
					System.exit(0);
				}
			} else {
				System.out.println("ERROR: EXECUTION: Given index " + indexInt + " is out of Array " + idName + " valid indexes.");
				System.exit(0);
			}
		} else if (type == 2) {
			// Create a new array with $IndexInt
			indexInt = index.execute();
			valueObj.arrayValue = new int[indexInt];
		} else if (type == 3) {
			// Create a new refrence to the array $idName.
			Memory.Value arrayObj = new Memory.Value();
			arrayObj.type = Core.ARRAY;
			arrayObj.arrayValue = Memory.getValue(assignFrom.execute()).arrayValue;

			// Add the array to the correct scope based on $Memory.dsFlag
			if (Memory.dsFlag) {
				Memory.global.put(idName, arrayObj);
			} else {
				Memory.local.peek().put(idName, arrayObj);
			}
		}
	}
}