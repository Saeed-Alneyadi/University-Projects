import java.util.Map;

public class Decl {
    DeclInteger di;
    DeclArray da;
    
    void parse(Scanner scnr) {
        if (scnr.currentToken() == Core.INTEGER) {
            di = new DeclInteger();
            di.parse(scnr);
        } else if (scnr.currentToken() == Core.ARRAY) {
            da = new DeclArray();
            da.parse(scnr);
        }
        // System.out.println("Decl Reached");
    }

    void print(Scanner scnr) {
        if (di != null) {
            di.print(scnr);
        } else if (da != null) {
            da.print(scnr);
        }
        // System.out.println("Decl Reached");
    }

    void semanticCheck(Scanner scnr, Map<String, String> map) {
        if (di != null) {
            di.semanticCheck(scnr, map);
        } else if (da != null) {
            da.semanticCheck(scnr, map);
        }
    }
}
