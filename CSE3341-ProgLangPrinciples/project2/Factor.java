import java.util.Map;

public class Factor {
    String name;
    Expr expr;
    int cnst;
    int status;

    void parse(Scanner scnr) {
        if (scnr.currentToken() == Core.ID) {
            status = 0;
            name = scnr.getId();
            scnr.nextToken();
            

            if (scnr.currentToken() == Core.LBRACE) {
                status = 1;
                scnr.nextToken();

                expr = new Expr();
                expr.parse(scnr);

                if (scnr.currentToken() != Core.RBRACE) {
                    System.out.println("Error: Parser: Missing \"]\" Right Bracket");
                    System.exit(1);
                }
                scnr.nextToken();
            }
        } else if (scnr.currentToken() == Core.CONST) {
            status = 2;
            cnst = scnr.getConst();
            scnr.nextToken();
        } else if (scnr.currentToken() == Core.LPAREN) {
            status = 3;
            scnr.nextToken();

            expr = new Expr();
            expr.parse(scnr);

            if (scnr.currentToken() != Core.RPAREN) {
                System.out.println("Error: Parser: Missing \")\" Right Parenthesis");
                System.exit(1);
            }
            scnr.nextToken();
        } else {
            System.out.println("Error: Parser: Syntax Error: Wrong Operator or Sign Used");
            System.exit(1);
        }
        // System.out.println("Factor Reached");
    }

    void print(Scanner scnr) {
        switch(status) {
            case 0:
                System.out.print(name);
                break;
            case 1:
                System.out.print(name + "[");
                expr.print(scnr);
                System.out.print("]");
                break;
            case 2:
                System.out.print(cnst);
                break;
            case 3:
                System.out.print("(");
                expr.print(scnr);
                System.out.print(")");
                break;
            default:
                break;
        }
        // System.out.println("Term Reached");
    }

    void semanticCheck(Scanner scnr, Map<String, String> map) {
        switch(status) {
            case 0:
                if (!map.containsKey(name) && !Main.idExistCheck(name)) {
                    System.out.println("ERROR: Semantics: Variable \"" + name + "\" doesn't exist");
                    System.exit(1);
                }
                break;
            case 1:
                if (!map.containsKey(name) && !Main.idExistCheck(name)) {
                    System.out.println("ERROR: Semantics: Variable \"" + name + "\" doesn't exist");
                    System.exit(1);
                }
                expr.semanticCheck(scnr, map);
                break;
            case 2:
                break;
            case 3:
                expr.semanticCheck(scnr, map);
                break;
            default:
                break;
        }
    }
}
