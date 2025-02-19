import java.util.Map;

public class Expr {
    Term term;
    Expr expr;
    boolean plus;

    void parse(Scanner scnr) {
        term = new Term();
        term.parse(scnr);

        if (scnr.currentToken() == Core.ADD) {
            plus = true;
            scnr.nextToken();

            expr = new Expr();
            expr.parse(scnr);
        } else if (scnr.currentToken() == Core.SUBTRACT) {
            plus = false;
            scnr.nextToken();

            expr = new Expr();
            expr.parse(scnr);
        }
        // System.out.println("Expr Reached");
    }

    void print(Scanner scnr) {
        term.print(scnr);

        if (expr != null) {
            if (plus) {
                System.out.print(" + ");
                expr.print(scnr);
            } else {
                System.out.print(" - ");
                expr.print(scnr);
            }
        }
        // System.out.println("Expr Reached");
    }

    void semanticCheck(Scanner scnr, Map<String, String> map) {
        term.semanticCheck(scnr, map);

        if (expr != null) {
            if (plus) {
                expr.semanticCheck(scnr, map);
            } else {
                expr.semanticCheck(scnr, map);
            }
        }
    }
}
