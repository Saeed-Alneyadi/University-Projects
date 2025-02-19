import java.util.Map;

public class Out {
    Expr expr;

    void parse(Scanner scnr) {
        if (scnr.currentToken() != Core.OUT) {
            System.out.println("ERROR: Parser: Missing \"out\" Keyword");
            System.exit(1);
        }
        scnr.nextToken();

        if (scnr.currentToken() != Core.LPAREN) {
            System.out.println("ERROR: Parser: Missing \"(\" Left Parenthesis");
            System.exit(1);
        }
        scnr.nextToken();

        expr = new Expr();
        expr.parse(scnr);

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
        // System.out.println("Out Reached");
    }

    void print(Scanner scnr) {
        System.out.print("out(");
        expr.print(scnr);
        System.out.print(")");
        System.out.println(";");
        // System.out.println("Out Reached");
    }

    void semanticCheck(Scanner scnr, Map<String, String> map) {
        expr.semanticCheck(scnr, map);
    }
}
