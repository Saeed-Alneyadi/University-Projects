import java.util.Map;

public class Cmpr {
    Expr expr1;
    Expr expr2;
    boolean equal;

    void parse(Scanner scnr) {
        expr1 = new Expr();
        expr1.parse(scnr);

        if (scnr.currentToken() != Core.EQUAL && scnr.currentToken() != Core.LESS) {
            System.out.println("ERROR: Parser: Missing Conditional Operator");
            System.exit(1);
        } else if (scnr.currentToken() != Core.EQUAL) {
            equal = false;
        } else {
            equal = true;
        }

        scnr.nextToken();

        expr2 = new Expr();
        expr2.parse(scnr);
        // System.out.println("Cmpr Reached");
    }

    void print(Scanner scnr) {
        expr1.print(scnr);

        if (equal) {
            System.out.print(" = ");
        } else {
            System.out.print(" < ");
        }

        expr2.print(scnr);
        // System.out.println("Cmpr Reached");
    }

    void semanticCheck(Scanner scnr, Map<String, String> map) {
        expr1.semanticCheck(scnr, map);
        expr2.semanticCheck(scnr, map);
    }
}
