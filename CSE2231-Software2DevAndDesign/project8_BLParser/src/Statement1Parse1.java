import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary methods {@code parse} and
 * {@code parseBlock} for {@code Statement}.
 *
 * @author Isaac Shores.22
 *
 */
public final class Statement1Parse1 extends Statement1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Converts {@code c} into the corresponding {@code Condition}.
     *
     * @param c
     *            the condition to convert
     * @return the {@code Condition} corresponding to {@code c}
     * @requires [c is a condition string]
     * @ensures parseCondition = [Condition corresponding to c]
     */
    private static Condition parseCondition(String c) {
        assert c != null : "Violation of: c is not null";
        assert Tokenizer
                .isCondition(c) : "Violation of: c is a condition string";
        return Condition.valueOf(c.replace('-', '_').toUpperCase());
    }

    /**
     * Parses an IF or IF_ELSE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"IF"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an if string is a proper prefix of #tokens] then
     *  s = [IF or IF_ELSE Statement corresponding to if string at start of #tokens]  and
     *  #tokens = [if string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseIf(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("IF") : ""
                + "Violation of: <\"IF\"> is proper prefix of tokens";

        /*
         * Dequeue of the IF token.
         */
        String frontToken = tokens.dequeue();

        /*
         * Checks for IF condition and creates parsed condition.
         */
        Reporter.assertElseFatalError(Tokenizer.isCondition(tokens.front()),
                "Error: Condition after IF not valid");
        Condition ifCondition = parseCondition(tokens.dequeue());

        /*
         * Dequeue of the THEN token.
         */
        frontToken = tokens.dequeue();
        Reporter.assertElseFatalError(frontToken.equals("THEN"),
                "Error: Expected THEN, found: " + frontToken);

        /*
         * creates IF statement block.
         */
        Statement ifStatement = s.newInstance();
        ifStatement.parseBlock(tokens);

        /*
         * Dequeue of the ELSE/END token. Checks if next token is either ELSE or
         * END, then parses accordingly.
         */
        frontToken = tokens.dequeue();
        Reporter.assertElseFatalError(
                frontToken.equals("ELSE") || frontToken.equals("END"),
                "Error: Expected ELSE or END, found " + frontToken);
        if (frontToken.equals("ELSE")) {

            /*
             * Creates ELSE statement block and assembles IF ELSE statement.
             */
            Statement elseStatement = s.newInstance();
            elseStatement.parseBlock(tokens);
            s.assembleIfElse(ifCondition, ifStatement, elseStatement);

            /*
             * Dequeue of the END token.
             */
            frontToken = tokens.dequeue();
            Reporter.assertElseFatalError(frontToken.equals("END"),
                    "Error: Expected END, found: " + frontToken);

        } else {
            Reporter.assertElseFatalError(frontToken.equals("END"),
                    "Error: Expected END, found: " + frontToken);

            /*
             * Assembles IF statement.
             */
            s.assembleIf(ifCondition, ifStatement);
        }

        /*
         * Dequeue of the IF token.
         */
        frontToken = tokens.dequeue();
        Reporter.assertElseFatalError(frontToken.equals("IF"),
                "Error: Expected IF, found " + frontToken);
    }

    /**
     * Parses a WHILE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"WHILE"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [a while string is a proper prefix of #tokens] then
     *  s = [WHILE Statement corresponding to while string at start of #tokens]  and
     *  #tokens = [while string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseWhile(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("WHILE") : ""
                + "Violation of: <\"WHILE\"> is proper prefix of tokens";

        /*
         * Dequeue of the WHILE token.
         */
        String frontToken = tokens.dequeue();

        /*
         * Checks if next token is condition, then creates and parses WHILE
         * condition.
         */
        Reporter.assertElseFatalError(Tokenizer.isCondition(tokens.front()),
                "Error: Condition after WHILE is not valid");
        Condition whileCondition = parseCondition(tokens.dequeue());

        /*
         * Dequeue of the DO token.
         */
        frontToken = tokens.dequeue();
        Reporter.assertElseFatalError(frontToken.equals("DO"),
                "Error: Expected DO, found: " + frontToken);

        /*
         * Creates WHIlE statement block and assembles WHILE statement.
         */
        Statement whileStatement = s.newInstance();
        whileStatement.parseBlock(tokens);
        s.assembleWhile(whileCondition, whileStatement);

        /*
         * Dequeue of the END token.
         */
        frontToken = tokens.dequeue();
        Reporter.assertElseFatalError(frontToken.equals("END"),
                "Error: Expected END, found: " + frontToken);

        /*
         * Dequeue of the WHILE token.
         */
        frontToken = tokens.dequeue();
        Reporter.assertElseFatalError(frontToken.equals("WHILE"),
                "Error: Expected WHILE, found:" + frontToken);
    }

    /**
     * Parses a CALL statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires [identifier string is a proper prefix of tokens]
     * @ensures <pre>
     * s =
     *   [CALL Statement corresponding to identifier string at start of #tokens]  and
     *  #tokens = [identifier string at start of #tokens] * tokens
     * </pre>
     */
    private static void parseCall(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0
                && Tokenizer.isIdentifier(tokens.front()) : ""
                        + "Violation of: identifier string is proper prefix of tokens";

        String identifier = tokens.dequeue();
        s.assembleCall(identifier);

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Statement1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        /*
         * Dequeue of front token and checks if it is a valid statement before
         * parsing.
         */
        String errorMsg = "ERROR: Did not find IF, WHILE, or Identifier to parse.";
        String frontToken = tokens.front();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(frontToken)
                || frontToken.equals("IF") || frontToken.equals("WHILE"),
                errorMsg);

        /*
         * Calls parse methods based on front token.
         */
        if (frontToken.equals("IF")) {
            parseIf(tokens, this);
        } else if (frontToken.equals("WHILE")) {
            parseWhile(tokens, this);
        } else {
            parseCall(tokens, this);
        }
    }

    @Override
    public void parseBlock(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        /*
         * Creates statement block.
         */
        Statement statementFromTokens = this.newInstance();
        
        /*
         * Iterates through tokens to parse until END, ELSE, or END_OF_INPUT.
         */
        for (int i = 0; !(tokens.front().equals("END")
                || tokens.front().equals("ELSE")
                || tokens.front().equals(Tokenizer.END_OF_INPUT)); i++) {

            statementFromTokens.parse(tokens);
            this.addToBlock(i, statementFromTokens);
        }
    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL statement(s) file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Statement s = new Statement1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        s.parse(tokens); // replace with parseBlock to test other method
        /*
         * Pretty print the statement(s)
         */
        out.println("*** Pretty print of parsed statement(s) ***");
        s.prettyPrint(out, 0);

        in.close();
        out.close();
    }

}
