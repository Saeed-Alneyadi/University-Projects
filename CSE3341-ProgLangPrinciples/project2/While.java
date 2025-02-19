import java.util.HashMap;
import java.util.Map;

public class While {
    Cond cd;
    StmtSeq ss;

    void parse(Scanner scnr) {
        if (scnr.currentToken() != Core.WHILE) {
            System.out.println("ERROR: Parser: Missing \"while\" Keyword");
            System.exit(1);
        }
        scnr.nextToken();

        cd = new Cond();
        cd.parse(scnr);

        if (scnr.currentToken() != Core.DO) {
            System.out.println("ERROR: Parser: Missing \"do\" Keyword");
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
        // System.out.println("While Reached");
    }

    void print(Scanner scnr) {
        System.out.print("while ");
        cd.print(scnr);
        System.out.println(" do");
        ss.print(scnr);
        System.out.println("end");
        // System.out.println("While Reached");
    }

    void semanticCheck(Scanner scnr, Map<String, String> map) {
        cd.semanticCheck(scnr, map);

        Main.stack.push(map);
        ss.semanticCheck(scnr, new HashMap<>());
        map = Main.stack.pop();
    }
}
