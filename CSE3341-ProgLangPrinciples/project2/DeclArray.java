import java.util.Map;

public class DeclArray {
    String arrId;

    void parse(Scanner scnr) {
        if (scnr.currentToken() != Core.ARRAY) {
            System.out.println("ERROR: Parser: Missing \"Array\" Keyword");
            System.exit(1);
        }
        scnr.nextToken();

        if (scnr.currentToken() != Core.ID) {
            System.out.println("ERROR: Parser: Missing Array's Identifier (ID) Name");
            System.exit(1);
        }
        arrId = scnr.getId();
        scnr.nextToken();

        if (scnr.currentToken() != Core.SEMICOLON) {
            System.out.println("ERROR: Parser: Missing \";\" Semicolon");
            System.exit(1);
        }
        scnr.nextToken();
        // System.out.println("DeclArray Reached");
    }

    void print(Scanner scnr) {
        System.out.println("array " + arrId + ";");
        // System.out.println("DeclArray Reached");
    }

    void semanticCheck(Scanner scnr, Map<String, String> map) {
        if (map.containsKey(arrId) || Main.idExistCheck(arrId)) {
            System.out.println("ERROR: Semantics: Double Declared Variable \"" + arrId + "\"");
            System.exit(1);
        } else {
            map.put(arrId, "array");
        }
    }
}
