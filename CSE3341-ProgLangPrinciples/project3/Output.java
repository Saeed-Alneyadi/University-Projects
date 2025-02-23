public class Output implements Stmt {
	Expr expr;
	
	public void parse() {
		Parser.expectedToken(Core.OUT);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.LPAREN);
		Parser.scanner.nextToken();
		expr = new Expr();
		expr.parse();
		Parser.expectedToken(Core.RPAREN);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}
	
	public void print(int indent) {
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		System.out.print("out(");
		expr.print();
		System.out.println(");");
	}

	public void execute() {
		// Printing Expr value to the output stream
		System.out.println(expr.execute());
	}
}