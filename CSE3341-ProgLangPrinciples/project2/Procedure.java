import java.util.HashMap;
import java.util.Map;

public class Procedure {
    String name;
    DeclSeq ds;
    StmtSeq ss;

    void parse(Scanner scnr) {
        if (scnr.currentToken() != Core.PROCEDURE) {
            System.out.println("ERROR: Parser: Missing \"procedure\" Keyword");
            System.exit(1);
        }
        scnr.nextToken();

        if (scnr.currentToken() != Core.ID) {
            System.out.println("ERROR: Parser: Missing Identifier (ID) Name");
            System.exit(1);
        }
        name = scnr.getId();
        scnr.nextToken();

        if (scnr.currentToken() != Core.IS) {
            System.out.println("ERROR: Parser: Missing \"is\" Keyword");
            System.exit(1);
        }
        scnr.nextToken();

        if (scnr.currentToken() != Core.BEGIN) {
            ds = new DeclSeq();
            ds.parse(scnr);
        }

        if (scnr.currentToken() != Core.BEGIN) {
            System.out.println("ERROR: Parser: Missing \"begin\" Keyword");
            System.exit(1);
        }
        scnr.nextToken();

        ss = new StmtSeq();
        ss.parse(scnr);

        if (scnr.currentToken() != Core.END) {
            System.out.println("ERROR: Parser: Missing \"end\" Keyword");
            System.exit(1);
        }
        scnr.nextToken();

        if (scnr.currentToken() != Core.EOS) {
            System.out.println("ERROR: Parser: Missing EOS (End of Stream) Character");
            System.exit(1);
        }
        scnr.nextToken();
    }

    void print(Scanner scnr) {
        System.out.println("procedure " + name + " is ");
        if (ds != null) {
            ds.print(scnr);
        }

        System.out.println("begin");
        ss.print(scnr);
        System.out.println("end");
    }

    void semanticCheck(Scanner scnr) {
        Map<String, String> declsMap = new HashMap<>();
        
        if (ds != null) {
            ds.semanticCheck(scnr, declsMap);
        }
        
        ss.semanticCheck(scnr, declsMap);
    }
}