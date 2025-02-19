import java.util.Map;

public class StmtSeq {
    Stmt s;
    StmtSeq ss;
    
    void parse(Scanner scnr) {
        s = new Stmt();
        s.parse(scnr);

        if (scnr.currentToken() != Core.END && scnr.currentToken() != Core.ELSE) {
            ss = new StmtSeq();
            ss.parse(scnr);
        }
        // System.out.println("StmtSeq Reached");
    }

    void print(Scanner scnr) {
        s.print(scnr);

        if (ss != null) {
            ss.print(scnr);
        }
        // System.out.println("StmtSeq Reached");
    }

    void semanticCheck(Scanner scnr, Map<String, String> map) {
        s.semanticCheck(scnr, map);

        if (ss != null) {
            ss.semanticCheck(scnr, map);
        }
    }
}
