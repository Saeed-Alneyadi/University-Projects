import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Character;
import java.util.ArrayList;

public class Scanner {
    // Instance Variable for Scanner Object
    ArrayList<String> tokens = new ArrayList<>();
    int current = 0;

    // Initialize the scanner
    public Scanner(String filename) {
        // Variable Declaration
        BufferedReader input;

        // Open, Read file, tokenize it, and return a list of tokens.
        try {
            input = new BufferedReader(new FileReader(filename));
            int ch1 = 0;
            String token = "";

            do {
                ch1 = input.read();
                boolean chCond = (Character.isDigit(ch1) || Character.isAlphabetic(ch1));
                boolean tokenCond = (Character.isDigit(token.indexOf(token.length())) || Character.isAlphabetic(token.indexOf(token.length())));

                // Check whether the last character of token String or current Character is akpahbetic or digit.
                if (chCond || tokenCond) {
                    token = token + (char) ch1;
                } else {
                    // Add token to tokens list if token is not empty. Restore token String to empty String.
                    if (!token.isEmpty()) {
                        this.tokens.add(token);
                        token = "";
                    }
                    
                    // This block handle the case for assignment operator and not avoid adding ch1 and ch2 to tokens list.
                    if (!Character.isWhitespace((char) ch1)) {
                        int ch2 = input.read();
                        if (ch1 == ':' && ch2 == '=') {
                            this.tokens.add((char) ch1 + "" + (char) ch2);
                        } else {
                            this.tokens.add((char) ch1 + "");
                            if (!Character.isWhitespace(ch2)) {
                                token = token + (char) ch2;
                            }
                        }
                    }
                }
            } while (ch1 != -1);
        } catch (IOException e) {
            System.err.println("Error Initialize the scanner");
            return;
        }
        
        // Close the file.
        try {
            input.close();
        } catch (IOException e) {
            System.err.println("Error closing file");
            return;
        }
    }

    // Advance to the next token
    public void nextToken() {
        current++;
    }

    // Return the current token
    public Core currentToken() {
        // Variables Initialization
        Core result = Core.ERROR;
        String token = this.tokens.get(current);

        // Check whether the token is an integer greater than or equal to 0 and less than and equal to 100003.
        try {
            if (Integer.parseInt(token) >= 0 && Integer.parseInt(token) <= 100003) {
                result = Core.CONST;
            }
        } catch (NumberFormatException e) {}

        // Check whether the token matches the regular experssion and if it does, it will check whether it is a keyword or not.
        if (token.matches("([a-z]|[A-Z])([a-z]|[A-Z]|[0-9])*")) {
            result = Core.CONST;
            switch (token) {
                case "procedure":
                    result = Core.PROCEDURE;
                    break;
                case "begin":
                    result = Core.BEGIN;
                    break;
                case "is":
                    result = Core.IS;
                    break;
                case "end":
                    result = Core.END;
                    break;
                case "if":
                    result = Core.IF;
                    break;
                case "else":
                    result = Core.ELSE;
                    break;
                case "in":
                    result = Core.IN;
                    break;
                case "integer":
                    result = Core.INTEGER;
                    break;
                case "return":
                    result = Core.RETURN;
                    break;
                case "do":
                    result = Core.DO;
                    break;
                case "new":
                    result = Core.NEW;
                    break;
                case "not":
                    result = Core.NOT;
                    break;
                case "and":
                    result = Core.AND;
                    break;
                case "or":
                    result = Core.OR;
                    break;
                case "out":
                    result = Core.OUT;
                    break;
                case "array":
                    result = Core.ARRAY;
                    break;
                case "then":
                    result = Core.THEN;
                    break;
                case "while":
                    result = Core.WHILE;
                    break;
                case "+":
                    result = Core.ADD;
                    break;
                case "-":
                    result = Core.SUBTRACT;
                    break;
                case "*":
                    result = Core.MULTIPLY;
                    break;
                case "/":
                    result = Core.DIVIDE;
                    break;
                case ":=":
                    result = Core.ASSIGN;
                    break;
                case "=":
                    result = Core.EQUAL;
                    break;
                case "<":
                    result = Core.LESS;
                    break;
                case ":":
                    result = Core.COLON;
                    break;
                case ";":
                    result = Core.SEMICOLON;
                    break;
                case ".":
                    result = Core.PERIOD;
                    break;
                case ",":
                    result = Core.COMMA;
                    break;
                case "(":
                    result = Core.LPAREN;
                    break;
                case ")":
                    result = Core.RPAREN;
                    break;
                case "[":
                    result = Core.LBRACE;
                    break;
                case "]":
                    result = Core.RBRACE;
                    break;
                case "-1":
                    result = Core.EOS;
                    break;
                default:
                    result = Core.ID;
                    break;
            }
        } else {
            // This switch statement checks for symbols.
            switch (token) {
                case "+":
                    result = Core.ADD;
                    break;
                case "-":
                    result = Core.SUBTRACT;
                    break;
                case "*":
                    result = Core.MULTIPLY;
                    break;
                case "/":
                    result = Core.DIVIDE;
                    break;
                case ":=":
                    result = Core.ASSIGN;
                    break;
                case "=":
                    result = Core.EQUAL;
                    break;
                case "<":
                    result = Core.LESS;
                    break;
                case ":":
                    result = Core.COLON;
                    break;
                case ";":
                    result = Core.SEMICOLON;
                    break;
                case ".":
                    result = Core.PERIOD;
                    break;
                case ",":
                    result = Core.COMMA;
                    break;
                case "(":
                    result = Core.LPAREN;
                    break;
                case ")":
                    result = Core.RPAREN;
                    break;
                case "[":
                    result = Core.LBRACE;
                    break;
                case "]":
                    result = Core.RBRACE;
                    break;
                case "-1":
                    result = Core.EOS;
                    break;
                default:
                    break;
            }
        }

        // Print Error Message if result is a enum core ERROR.
        if (result.equals(Core.ERROR) && !token.equals("\uFFFF")) {
            System.out.println("ERROR: Unknown Script \"" + token + "\"");
        }

        return result;
    }

	// Return the identifier string
    public String getId() {
        if (this.tokens.get(current).matches("([a-z]|[A-Z])([a-z]|[A-Z]|[0-9])*")) {
            return this.tokens.get(current);
        } else {
            System.err.println("ERROR: getId() has been called. String token is not an Id. Empty String has been retured.");
            return "";
        }
    }

	// Return the constant value
    public int getConst() {
        try {
            return Integer.valueOf(this.tokens.get(current));
        } catch (NumberFormatException e) {
            System.err.println("ERROR: getConst() called with a token not constant. -1 has been returned.");
            return -1;
        }
    }

}
