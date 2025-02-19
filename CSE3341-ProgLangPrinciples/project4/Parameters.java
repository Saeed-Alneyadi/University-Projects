import java.util.ArrayList;
public class Parameters {
    public static ArrayList<String> paramList = new ArrayList<String>();
    public static boolean formalFlag = true;
    String name;
    Parameters param;

    public void parse() {
        // Check for ID name Existence. Output Error Message if not.
        if (Parser.scanner.currentToken() != Core.ID) {
            System.out.println("ERROR: Parser: Missing Identifier (ID) Name");
            System.exit(1);
        }
        name = Parser.scanner.getId();
        Parser.scanner.nextToken();

        // Handles Formal Parameters Duplication Error
        if (formalFlag) {
            if (!paramList.contains(name)) {
                paramList.add(name);
            } else {
                System.out.println("ERROR: Parser: Duplicate Parameter Declaration");
                System.exit(1);
            }
        }

        // Check for character "," Existence. Output Error Message if not.
        if (Parser.scanner.currentToken() == Core.COMMA) {
            Parser.scanner.nextToken();
            param = new Parameters();
            param.parse();
        }
    }

    public void print(int indent) {
        for (int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
        System.out.print(name + ", ");
        if (param != null) {
            param.print(indent); // Parameter Print
        }
    }

    public ArrayList<String> execute() {
        ArrayList<String> result; // Variable Declartion
        
        if (param == null) {
            result = new ArrayList<>(); // New List in case of if we reached last parameter
        } else {
            result = param.execute(); // Executing Recursively
        }
        result.add(name); // Adding name to the list

        return result; // Return Statement
    }
}
