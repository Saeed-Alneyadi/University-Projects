import java.util.Map;

public class DeclSeq {
    Decl d;
    DeclSeq ds;
    
    void parse(Scanner scnr) {
        d = new Decl();
        d.parse(scnr);

        if (scnr.currentToken() == Core.INTEGER || scnr.currentToken() == Core.ARRAY) {
            ds = new DeclSeq();
            ds.parse(scnr);
        }
        // System.out.println("DeclSeq Reached");
    }

    void print(Scanner scnr) {
        d.print(scnr);
        
        if (ds != null) {
            ds.print(scnr);
        }
        // System.out.println("DeclSeq Reached");
    }

    void semanticCheck(Scanner scnr, Map<String, String> map) {
        d.semanticCheck(scnr, map);

        if (ds != null) {
            ds.semanticCheck(scnr, map);
        }
    }
}
