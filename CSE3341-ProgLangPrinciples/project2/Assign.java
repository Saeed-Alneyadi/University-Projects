import java.util.Map;

public class Assign {
    String name1;
    String name2;
    Expr expr1;
    Expr expr2;
    int flag;

    void parse(Scanner scnr) {
        if (scnr.currentToken() != Core.ID) {
            System.out.println("ERROR: Parser: Missing Identifier (ID) Name");
            System.exit(1);
        }
        name1 = scnr.getId();
        scnr.nextToken();

        if (scnr.currentToken() != Core.ASSIGN) {
            flag = 1;
            if (scnr.currentToken() != Core.LBRACE) {
                System.out.println("ERROR: Parser: Missing \"[\" Left Bracket or Wrong Assignment Operator (" + scnr.currentToken() + ")");
                System.exit(1);
            }
            scnr.nextToken();

            expr1 = new Expr();
            expr1.parse(scnr);

            if (scnr.currentToken() != Core.RBRACE) {
                System.out.println("ERROR: Parser: Missing \"]\" Right Bracket");
                System.exit(1);
            }
            scnr.nextToken();

            if (scnr.currentToken() != Core.ASSIGN) {
                System.out.println("ERROR: Parser: Missing \":=\" Assignment Operator");
                System.exit(1);
            }
            scnr.nextToken();

            expr2 = new Expr();
            expr2.parse(scnr);
        } else {
            scnr.nextToken();
            if (scnr.currentToken() != Core.NEW) {
                if (scnr.currentToken() != Core.ARRAY) {
                    flag = 0;
                    expr1 = new Expr();
                    expr1.parse(scnr);
                } else {
                    flag = 3;
                    scnr.nextToken();

                    if (scnr.currentToken() != Core.ID) {
                        System.out.println("ERROR: Parser: Missing Array's Identifier (ID) Name");
                        System.exit(1);
                    }
                    name2 = scnr.getId();
                    scnr.nextToken();
                }
            } else {
                flag = 2;
                scnr.nextToken();

                if (scnr.currentToken() != Core.INTEGER) {
                    System.out.println("ERROR: Parser: Missing Integer's Keyword");
                    System.exit(1);
                }
                scnr.nextToken();

                if (scnr.currentToken() != Core.LBRACE) {
                    System.out.println("ERROR: Parser: Missing \"[\" Left Bracket");
                    System.exit(1);
                }
                scnr.nextToken();

                expr1 = new Expr();
                expr1.parse(scnr);

                if (scnr.currentToken() != Core.RBRACE) {
                    System.out.println("ERROR: Parser: Missing \"[\" Right Bracket");
                    System.exit(1);
                }
                scnr.nextToken();
            }
        }
        if (scnr.currentToken() != Core.SEMICOLON) {
                System.out.println("ERROR: Parser: Missing \";\" Semicolon");
                System.exit(1);
        }
        scnr.nextToken();
        // System.out.println("Assign Reached");
    }

    void print(Scanner scnr) {
        System.out.print(name1);

        switch (flag) {
            case 0:
                System.out.print(" := ");
                expr1.print(scnr);
                System.out.println(";");
                break;
            case 1:
                System.out.print("[");
                expr1.print(scnr);
                System.out.print("] := ");
                expr2.print(scnr);
                System.out.println(";");
                break;
            case 2:
                System.out.print(" := new integer [");
                expr1.print(scnr);
                System.out.println("];");
                break;
            case 3:
                System.out.print(" := array " + name2 + " ;");
                break;
            default:
                System.out.println("ERROR: Printer: Unknown Flag in Assign Class");
                System.exit(1);
                break;
        }

        // System.out.println("Assign Reached");
    }

    void semanticCheck(Scanner scnr, Map<String, String> map) {
        switch(flag) {
            case 0:
                if (!map.containsKey(name1) && !Main.idExistCheck(name1)) {
                    System.out.println("ERROR: Semantics: Variable \"" + name1 + "\" doesn't exist");
                    System.exit(1);
                }
                expr1.semanticCheck(scnr, map);

                break;
            case 1:
                if (!map.containsKey(name1) && !Main.idExistCheck(name1)) {
                    System.out.println("ERROR: Semantics: Array \"" + name1 + "\" doesn't exist");
                    System.exit(1);
                } else {
                    if (map.containsKey(name1)) {
                        String realType = map.get(name1);

                        if (!realType.equals("array")) {
                            System.out.println("ERROR: Semantics: Array \"" + name1 + "\" Type doesn't match");
                            System.exit(1);
                        }
                    } else if (!Main.idTypeCheck(name1, "array")) {
                        System.out.println("ERROR: Semantics: Array \"" + name1 + "\" Type doesn't match");
                        System.exit(1);
                    }
                }
                expr1.semanticCheck(scnr, map);
                expr2.semanticCheck(scnr, map);

                break;
            case 2:
                if (!map.containsKey(name1) && !Main.idExistCheck(name1)) {
                    System.out.println("ERROR: Semantics: Variable \"" + name1 + "\" doesn't exist");
                    System.exit(1);
                } else {
                    if (map.containsKey(name1)) {
                        String realType = map.get(name1);

                        if (!realType.equals("array")) {
                            System.out.println("ERROR: Semantics: Array \"" + name1 + "\" Type doesn't match");
                            System.exit(1);
                        }
                    } else if (!Main.idTypeCheck(name1, "array")) {
                        System.out.println("ERROR: Semantics: Array \"" + name1 + "\" Type doesn't match");
                        System.exit(1);
                    }
                }
                expr1.semanticCheck(scnr, map);

                break;
            case 3:
                if (!map.containsKey(name1) && !Main.idExistCheck(name1)) {
                    System.out.println("ERROR: Semantics: Variable \"" + name1 + "\" doesn't exist");
                    System.exit(1);
                } else {
                    if (map.containsKey(name1)) {
                        String realType = map.get(name1);

                        if (!realType.equals("array")) {
                            System.out.println("ERROR: Semantics: Array \"" + name1 + "\" Type doesn't match");
                            System.exit(1);
                        }
                    } else if (!Main.idTypeCheck(name1, "array")) {
                        System.out.println("ERROR: Semantics: Array \"" + name1 + "\" Type doesn't match");
                        System.exit(1);
                    }
                }

                if (!map.containsKey(name2) && !Main.idExistCheck(name2)) {
                    System.out.println("ERROR: Semantics: Variable \"" + name2 + "\" doesn't exist");
                    System.exit(1);
                } else {
                    if (map.containsKey(name2)) {
                        String realType = map.get(name2);

                        if (!realType.equals("array")) {
                            System.out.println("ERROR: Semantics: Array \"" + name2 + "\" Type doesn't match");
                            System.exit(1);
                        }
                    } else if (!Main.idTypeCheck(name1, "array")) {
                        System.out.println("ERROR: Semantics: Array \"" + name2 + "\" Type doesn't match");
                        System.exit(1);
                    }
                }
                break;
            default:
                System.out.println("ERROR: Semantics: Unknown Flag in Assign Class");
                System.exit(1);
                break;
        }
    }
}
