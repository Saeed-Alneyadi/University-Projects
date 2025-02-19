import java.util.Map;

public class Term {
    Factor fctr;
    Term term;
    boolean astrisk;

    void parse(Scanner scnr) {
        fctr = new Factor();
        fctr.parse(scnr);

        if (scnr.currentToken() == Core.MULTIPLY) {
            astrisk = true;
            scnr.nextToken();

            term = new Term();
            term.parse(scnr);
        } else if (scnr.currentToken() == Core.DIVIDE) {
            astrisk = false;
            scnr.nextToken();

            term = new Term();
            term.parse(scnr);
        }
        // System.out.println("Term Reached");
    }

    void print(Scanner scnr) {
        fctr.print(scnr);

        if (term != null) {
            if (astrisk) {
                System.out.print(" * ");
                term.print(scnr);
            } else {
                System.out.print(" / ");
                term.print(scnr);
            }
        }
        // System.out.println("Term Reached");
    }

    void semanticCheck(Scanner scnr, Map<String, String> map) {
        fctr.semanticCheck(scnr, map);

        if (term != null) {
            if (astrisk) {
                term.semanticCheck(scnr, map);
            } else {
                term.semanticCheck(scnr, map);
            }
        }
    }
}
