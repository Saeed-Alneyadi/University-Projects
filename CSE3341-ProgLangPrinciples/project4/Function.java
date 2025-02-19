import java.util.ArrayList;

public class Function {
    String name;
	Parameters param;
	StmtSeq ss;

    public void parse() {
        // Check for keyword "procedure" Existence. Output Error Message if not.
        if (Parser.scanner.currentToken() != Core.PROCEDURE) {
            System.out.println("ERROR: Parser: Missing \"procedure\" Keyword");
            System.exit(1);
        }
        Parser.scanner.nextToken();

        // Check for ID name Existence. Output Error Message if not.
        if (Parser.scanner.currentToken() != Core.ID) {
            System.out.println("ERROR: Parser: Missing Identifier (ID) Name");
            System.exit(1);
        }
        name = Parser.scanner.getId();
        Parser.scanner.nextToken();

        // Check for character "(" Existence. Output Error Message if not.
        if (Parser.scanner.currentToken() != Core.LPAREN) {
            System.out.println("ERROR: Parser: Missing \"(\" Left Parenthesis");
            System.exit(1);
        }
        Parser.scanner.nextToken();

        param = new Parameters();
        Parameters.formalFlag = true;
        param.parse();
        Parameters.formalFlag = false;
        Parameters.paramList = new ArrayList<String>();

        // Check for character ")" Existence. Output Error Message if not.
        if (Parser.scanner.currentToken() != Core.RPAREN) {
            System.out.println("ERROR: Parser: Missing \")\" Right Parenthesis");
            System.exit(1);
        }
        Parser.scanner.nextToken();

        // Check for keyword "is" Existence. Output Error Message if not.
        if (Parser.scanner.currentToken() != Core.IS) {
            System.out.println("ERROR: Parser: Missing \"is\" Keyword");
            System.exit(1);
        }
        Parser.scanner.nextToken();

        // StmtSeq Parsing
        ss = new StmtSeq();
        ss.parse();

        // Check for keyword "end" Existence. Output Error Message if not.
        if (Parser.scanner.currentToken() != Core.END) {
            System.out.println("ERROR: Parser: Missing \"end\" Keyword");
            System.exit(1);
        }
        Parser.scanner.nextToken();
    }

    public void print(int indent) {
        for (int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
        System.out.print("procedure " + name + "(");
        param.print(indent); // Parameters Print
        System.out.println(") is ");
        ss.print(indent); // StmtSeq Print
        System.out.print("end");
    }

    public void execute() {
        // Add Function to list if doesn't exist. Otherwise, Output Error Message
        if (!Memory.funcList.containsKey(name)) {
            Memory.funcList.put(name, this);
        } else {
            System.out.println("ERROR: Execuator: Duplicated Function Name in Declaration: \"" + name + "\" (Overloading)");
            System.exit(1);
        }
    }
}