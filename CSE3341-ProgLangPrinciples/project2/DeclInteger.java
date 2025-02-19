import java.util.Map;

public class DeclInteger {
    String intId;

    void parse(Scanner scnr) {
        if (scnr.currentToken() != Core.INTEGER) {
            System.out.println("ERROR: Parser: Missing \"Integer\" Keyword");
            System.exit(1);
        }
        scnr.nextToken();

        if (scnr.currentToken() != Core.ID) {
            System.out.println("ERROR: Parser: Missing Integer's Identifier (ID) Name");
            System.exit(1);
        }
        intId = scnr.getId();
        scnr.nextToken();

        if (scnr.currentToken() != Core.SEMICOLON) {
            System.out.println("ERROR: Parser: Missing \";\" Semicolon");
            System.exit(1);
        }
        scnr.nextToken();
        // System.out.println("DeclInteger Reached");
    }

    void print(Scanner scnr) {
        System.out.println("integer " + intId + ";");
        // System.out.println("DeclInteger Reached");
    }

    void semanticCheck(Scanner scnr, Map<String, String> map) {
        if (map.containsKey(intId) || Main.idExistCheck(intId)) {
            System.out.println("ERROR: Semantics: Double Declared Variable \"" + intId + "\"");
            System.exit(1);
        } else {
            map.put(intId, "integer");
        }
    }
}
