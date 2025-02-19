import java.util.HashMap;
import java.util.Map;

public class Cond {
    Cmpr cp;
    Cond cd;
    int oprt = 0;

    void parse(Scanner scnr) {
        if (scnr.currentToken() != Core.NOT) {
            cp = new Cmpr();
            cp.parse(scnr);

            if (scnr.currentToken() == Core.OR || scnr.currentToken() == Core.AND) {
                if (scnr.currentToken() == Core.OR) {
                    oprt = 2;
                } else {
                    oprt = 3;
                }
                scnr.nextToken();

                cd = new Cond();
                cd.parse(scnr);
            } else {
                oprt = 0;
            }
        } else {
            oprt = 1;
            scnr.nextToken();

            cd = new Cond();
            cd.parse(scnr);
        }
        // System.out.println("Cond Reached");
    }

    void print(Scanner scnr) {
        switch (oprt) {
            case 0:
                cp.print(scnr);
                break;
            case 1:
                System.out.print("not ");
                cd.print(scnr);
                break;
            case 2:
                cp.print(scnr);
                System.out.print(" or ");
                cd.print(scnr);
                break;
            case 3:
                cp.print(scnr);
                System.out.print(" and ");
                cd.print(scnr);
                break;
            default:
                System.out.print("ERROR: Printer: Unidentifiable Operator to Print");
                System.exit(1);
                break;
        }
        // System.out.println("Cond Reached");
    }

    void semanticCheck(Scanner scnr, Map<String, String> map) {
        switch (oprt) {
            case 0:
                cp.semanticCheck(scnr, map);
                break;
            case 1:
                cd.semanticCheck(scnr, map);
                break;
            case 2:
                cp.semanticCheck(scnr, map);
                cd.semanticCheck(scnr, map);
                break;
            case 3:
                cp.semanticCheck(scnr, map);
                cd.semanticCheck(scnr, map);
                break;
            default:
                System.out.println("ERROR: Semantics: Unidentifiable Operator to Print");
                System.exit(1);
                break;
        }
    }
}
