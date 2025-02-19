import java.util.HashMap;

class Loop implements Stmt {
	Cond cond;
	StmtSeq ss;
	
	public void parse() {
		Parser.scanner.nextToken();
		cond = new Cond();;
		cond.parse();
		Parser.expectedToken(Core.DO);
		Parser.scanner.nextToken();
		ss = new StmtSeq();
		ss.parse();
		Parser.expectedToken(Core.END);
		Parser.scanner.nextToken();
	}
	
	public void print(int indent) {
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		System.out.print("while ");
		cond.print();
		System.out.println(" do");
		ss.print(indent+1);
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		System.out.println("end");
	}

	public void execute() {
		// Variable Initialization
		boolean condition = cond.execute();

		// Pushing map to stack $Memory.local
		Memory.local.push(new HashMap());

		while (condition) {
			ss.execute();
			condition = cond.execute();
		}

		// Popping map from stack $Memory.local
		Memory.local.pop();
	}
}