import java.util.*;

class Procedure {
	String name;
	DeclSeq ds;
	StmtSeq ss;
	
	void parse() {
		Parser.expectedToken(Core.PROCEDURE);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.ID);
		name = Parser.scanner.getId();
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.IS);
		Parser.scanner.nextToken();
		if (Parser.scanner.currentToken() != Core.BEGIN) {
			ds = new DeclSeq();
			ds.parse();
		}
		Parser.expectedToken(Core.BEGIN);
		Parser.scanner.nextToken();
		ss = new StmtSeq();
		ss.parse();
		Parser.expectedToken(Core.END);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.EOS);
	}
	
	void print() {
		System.out.println("procedure " + name + " is");
		if (ds != null) {
			ds.print(1);
		}
		System.out.println("begin ");
		ss.print(1);
		System.out.println("end");
	}

	void execute() {
		// Variables Initialization
		Memory.global = new HashMap<>();
		Memory.local = new Stack<>();

		// Execute the declaration sequence,
		// i.e. create space in memory for the variables
		if (ds != null) {
			Memory.dsFlag = true;
			ds.execute();
			Memory.dsFlag = false;
		}
		Memory.local.add(new HashMap<>());
		
		// Execute the statement sequence
		ss.execute();

		Memory.local.pop();

		Memory.global = null;
		Memory.local = null;
	}
}