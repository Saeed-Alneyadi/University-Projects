import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Call implements Stmt {
    String name;
    Parameters param;

    public void parse() {
        // Check for keyword "begin" Existence. Output Error Message if not.
        if (Parser.scanner.currentToken() != Core.BEGIN) {
            System.out.println("ERROR: Parser: Missing \"begin\" Keyword");
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
        param.parse();

        // Check for character ")" Existence. Output Error Message if not.
        if (Parser.scanner.currentToken() != Core.RPAREN) {
            System.out.println("ERROR: Parser: Missing \")\" Right Parenthesis");
            System.exit(1);
        }
        Parser.scanner.nextToken();

        // Check for character ";" Existence. Output Error Message if not.
        if (Parser.scanner.currentToken() != Core.SEMICOLON) {
            System.out.println("ERROR: Parser: Missing \";\" Semicolon");
            System.exit(1);
        }
        Parser.scanner.nextToken();
    }

    public void print(int indent) {
        for (int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
        System.out.print("begin " + name + " (");
        param.print(indent); // Parameter Print
        System.out.println(");");
    }

    public void execute() {
        // Execute the called function. Output Error Message if function doesn't exist.
        if (Memory.funcList.containsKey(name)) {
            // Variables Declartions And Initialization
            Stack<HashMap<String, Memory.Value>> frame = new Stack<HashMap<String, Memory.Value>>();
            frame.push(new HashMap<String, Memory.Value>());
            Function calledFunc = Memory.funcList.get(name);
            ArrayList<String> formalParamList = calledFunc.param.execute();
            ArrayList<String> actualParamList = param.execute();

            // Linking Actual Parameters to Formal Parameters. Output Error Message if size is not the same.
            if (actualParamList.size() == formalParamList.size()) {
                for (int idx = 0; idx < actualParamList.size(); idx++) {
                    frame.peek().put(formalParamList.get(idx), Memory.getLocalOrGlobal(actualParamList.get(idx)));
                }
            } else {
                System.out.println("ERROR: Execuator: Actual and Formal Parameters Number Mismatch");
                System.exit(1);
            }

            Memory.local.push(frame); // Pushing Frame to stack of frames
            calledFunc.ss.execute(); // Funciton StmtSeq Execution
            Memory.local.pop(); // Poping Frame from stack of frames
        } else {
            System.out.println("ERROR: Execuator: Called Function \"" + name + "\" Doesn't Exist");
            System.exit(1);
        }
    }
}
