import java.util.HashMap;
import java.util.Map;

public class If {
    Cond cd;
    StmtSeq ss1;
    StmtSeq ss2;

    void parse(Scanner scnr) {
        if (scnr.currentToken() != Core.IF) {
            System.out.println("ERROR: Parser: Missing \"if\" Keyword");
            System.exit(1);
        }
        scnr.nextToken();

        cd = new Cond();
        cd.parse(scnr);

        if (scnr.currentToken() != Core.THEN) {
            System.out.println("ERROR: Parser: Missing \"then\" Keyword");
            System.exit(1);
        }
        scnr.nextToken();

        ss1 = new StmtSeq();
        ss1.parse(scnr);

        if (scnr.currentToken() != Core.END) {
            if (scnr.currentToken() != Core.ELSE) {
                System.out.println("ERROR: Parser: Missing \"else\" Keyword");
                System.exit(1);
            }
            scnr.nextToken();

            ss2 = new StmtSeq();
            ss2.parse(scnr);
        }

        if (scnr.currentToken() != Core.END) {
            System.out.println("ERROR: Parser: Missing \"end\" Keyword");
            System.exit(1);
        }
        scnr.nextToken();
        // System.out.println("If Reached");
    }

    void print(Scanner scnr) {
        System.out.print("if ");
        cd.print(scnr);
        System.out.println(" then");
        ss1.print(scnr);

        if (ss2 != null) {
            System.out.println("else");
            ss2.print(scnr);
        }

        System.out.println("end");
        // System.out.println("If Reached");
    }

    void semanticCheck(Scanner scnr, Map<String, String> map) {
        cd.semanticCheck(scnr, map);

        Main.stack.push(map);
        ss1.semanticCheck(scnr, new HashMap<>());
        map = Main.stack.pop();

        if (ss2 != null) {
            Main.stack.push(map);
            ss2.semanticCheck(scnr, map);
            map = Main.stack.pop();
        }
    }
}
