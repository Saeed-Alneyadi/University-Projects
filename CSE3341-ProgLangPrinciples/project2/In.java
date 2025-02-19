import java.util.Map;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class In {
    String name;

    void parse(Scanner scnr) {
        if (scnr.currentToken() != Core.IN) {
            System.out.println("ERROR: Parser: Missing \"in\" Keyword");
            System.exit(1);
        }
        scnr.nextToken();

        if (scnr.currentToken() != Core.LPAREN) {
            System.out.println("ERROR: Parser: Missing \"(\" Left Parenthesis");
            System.exit(1);
        }
        scnr.nextToken();

        if (scnr.currentToken() != Core.ID) {
            System.out.println("ERROR: Parser: Missing Identifier (ID) Name");
            System.exit(1);
        }
        name = scnr.getId();
        scnr.nextToken();

        if (scnr.currentToken() != Core.RPAREN) {
            System.out.println("ERROR: Parser: Missing \")\" Right Parenthesis");
            System.exit(1);
        }
        scnr.nextToken();

        if (scnr.currentToken() != Core.SEMICOLON) {
            System.out.println("ERROR: Parser: Missing \";\" Semicolon");
            System.exit(1);
        }
        scnr.nextToken();
        // System.out.println("In Reached");
    }

    void print(Scanner scnr) {
        System.out.println("in(" + name + ");");
        // System.out.println("In Reached");
    }

    void semanticCheck(Scanner scnr, Map<String, String> map) {
        if (!map.containsKey(name) && !Main.idExistCheck(name)) {
            System.out.println("ERROR: Semantics: Variable \"" + name + "\" doesn't exist");
            System.exit(1);
        }
    }
}
