import java.util.Map;

public class Stmt {
    Assign as;
    If ifNT;
    While wil;
    Out ot;
    In inNT;
    Decl d;

    void parse(Scanner scnr) {
        if (scnr.currentToken() == Core.ID) {
            as = new Assign();
            as.parse(scnr);
        } else if (scnr.currentToken() == Core.IF){
            ifNT = new If();
            ifNT.parse(scnr);
        } else if (scnr.currentToken() == Core.WHILE){
            wil = new While();
            wil.parse(scnr);
        } else if (scnr.currentToken() == Core.OUT){
            ot = new Out();
            ot.parse(scnr);
        } else if (scnr.currentToken() == Core.IN){
            inNT = new In();
            inNT.parse(scnr);
        } else if (scnr.currentToken() == Core.INTEGER || scnr.currentToken() == Core.ARRAY) {
            d = new Decl();
            d.parse(scnr);
        } else {
            System.out.println("ERROR: Parser: Unknown Statment");
            System.exit(1);
        }
        // System.out.println("Stmt Reached");
    }

    void print(Scanner scnr) {
        if (as != null) {
            as.print(scnr);
        } else if (ifNT != null) {
            ifNT.print(scnr);
        } else if (wil != null) {
            wil.print(scnr);
        } else if (ot != null) {
            ot.print(scnr);
        } else if (inNT != null) {
            inNT.print(scnr);
        } else if (d != null) {
            d.print(scnr);
        }
        // System.out.println("Stmt Reached");
    }

    void semanticCheck(Scanner scnr, Map<String, String> map) {
        if (as != null) {
            as.semanticCheck(scnr, map);
        } else if (ifNT != null) {
            ifNT.semanticCheck(scnr, map);
        } else if (wil != null) {
            wil.semanticCheck(scnr, map);
        } else if (ot != null) {
            ot.semanticCheck(scnr, map);
        } else if (inNT != null) {
            inNT.semanticCheck(scnr, map);
        } else if (d != null) {
            d.semanticCheck(scnr, map);
        }
    }
}
