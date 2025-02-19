import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Saeed Alneyadi
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to the block string that is the body of
     *          the instruction string at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";
        /*
         * Variables Declarations/Initialization
         */
        String id1;
        String id2;
        /*
         * Checks whether "INTSRUCTION" keyword is correct. If it is, it will
         * report the written ERROR message. Otherwise, It will not do anything.
         */
        Reporter.assertElseFatalError(Tokenizer.isKeyword(tokens.dequeue()),
                "Syntax Error on INSTRUCTION Keyword.");
        /*
         * Setting the identifier of the instruction to {@code id1}.
         */
        id1 = tokens.dequeue();
        /*
         * Checks whether "IS" keyword is correct. If it is, it will report the
         * written ERROR message. Otherwise, It will not do anything.
         */
        Reporter.assertElseFatalError(Tokenizer.isKeyword(tokens.dequeue()),
                "Syntax Error on IS Keyword.");
        /*
         * Calling parseBlock() on {@code body}.
         */
        body.parseBlock(tokens);
        /*
         * Checks whether "END" keyword is correct. If it is, it will report the
         * written ERROR message. Otherwise, It will not do anything.
         */
        Reporter.assertElseFatalError(Tokenizer.isKeyword(tokens.dequeue()),
                "Syntax Error on END Keyword.");
        /*
         * Setting the identifier of the instruction at the END to {@code id2}.
         */
        id2 = tokens.dequeue();
        /*
         * Checks whether the identifiers equals to each other. If it is, it
         * will report the written ERROR message. Otherwise, It will not do
         * anything.
         */
        Reporter.assertElseFatalError(id1.equals(id2),
                "Syntax Error on Identifier: "
                        + "Identifiers at the begining and at the end are not equal.");
        /*
         * Checks whether the name of the instruction is an identifier. If it
         * is, it will report the written ERROR message. Otherwise, It will not
         * do anything.
         */
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(id1),
                "Syntax Error on Identifier: "
                        + "The name of the instruction is not an identifier.");
        /*
         * Checks whether the identifier not equal to the primitive
         * Instructions. If it is, it will report the written ERROR message.
         * Otherwise, It will not do anything.
         */
        Reporter.assertElseFatalError(
                !(id1.equals("move") || id1.equals("turnleft")
                        || id1.equals("turnright") || id1.equals("infect")
                        || id1.equals("skip")),
                "Syntax Error on Identifier: "
                        + "The name of the intruction have a name that is identical "
                        + "to the primtive instructions.");
        /*
         * RETURN Statement: Returning {@code id1}.
         */
        return id1;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        /*
         * Variables Declarations/Initialization
         */
        String id1;
        String id2;
        Map<String, Statement> context = this.newContext();
        Statement body = this.newBody();
        this.swapContext(context);
        this.swapBody(body);
        /*
         * Checks whether "PROGRAM" keyword is correct. If it is, it will report
         * the written ERROR message. Otherwise, It will not do anything.
         */
        Reporter.assertElseFatalError(Tokenizer.isKeyword(tokens.dequeue()),
                "Syntax Error on PROGRAM Keyword.");
        /*
         * Setting the identifier of the instruction to {@code id1}.
         */
        id1 = tokens.dequeue();
        /*
         * Checks whether "IS" keyword is correct. If it is, it will report the
         * written ERROR message. Otherwise, It will not do anything.
         */
        Reporter.assertElseFatalError(Tokenizer.isKeyword(tokens.dequeue()),
                "Syntax Error on IS Keyword.");
        /*
         * Convert the tokens to instructions and store them to (@code context}.
         */
        String command = tokens.front();
        while (!command.equals("BEGIN")) {
            Statement block = body.newInstance();

            String instr = parseInstruction(tokens, block);

            Reporter.assertElseFatalError(!context.hasKey(instr),
                    "Syntax Error on instructions identifer: "
                            + "Multiple instructions has the same identifer name.");

            context.add(instr, block);

            command = tokens.front();
        }
        /*
         * Checks whether "BEGIN" keyword is correct. If it is, it will report
         * the written ERROR message. Otherwise, It will not do anything.
         */
        Reporter.assertElseFatalError(Tokenizer.isKeyword(tokens.dequeue()),
                "Syntax Error on BEGIN Keyword.");
        /*
         * Calling parseBlock() on {@code body}.
         */
        body.parseBlock(tokens);
        /*
         * Checks whether "END" keyword is correct. If it is, it will report the
         * written ERROR message. Otherwise, It will not do anything.
         */
        Reporter.assertElseFatalError(Tokenizer.isKeyword(tokens.dequeue()),
                "Syntax Error on END Keyword.");
        /*
         * Setting the identifier of the instruction at the END to {@code id2}.
         */
        id2 = tokens.dequeue();
        /*
         * Checks whether the identifiers equals to each other. If it is, it
         * will report the written ERROR message. Otherwise, It will not do
         * anything.
         */
        Reporter.assertElseFatalError(id1.equals(id2),
                "Syntax Error on Identifier: "
                        + "Identifiers at the begining and at the end are not equal.");
        /*
         * Checks whether the name of the program is an identifier. If it is, it
         * will report the written ERROR message. Otherwise, It will not do
         * anything.
         */
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(id1),
                "Syntax Error on Identifier: "
                        + "The name of the program is not an identifier.");
        /*
         * Checks whether the identifier not equal to the primitive
         * Instructions. If it is, it will report the written ERROR message.
         * Otherwise, It will not do anything.
         */
        Reporter.assertElseFatalError(
                !(id1.equals("move") || id1.equals("turnleft")
                        || id1.equals("turnright") || id1.equals("infect")
                        || id1.equals("skip")),
                "Syntax Error on Identifier: "
                        + "The name of the program have a name that is identical "
                        + "to the primtive instructions.");
        /*
         * Setting the name of {@code this} to {@code id1}.
         */
        this.setName(id1);
        /*
         * Swapping the context and body of {@code this} with {@code context}
         * and {@code body}.
         */
        this.swapContext(context);
        this.swapBody(body);
        /*
         * Checks whether {@code tokens} has BL instructions after the "END"
         * keyword. If it is, it will report an error. Otherwise, it will not.
         */
        Reporter.assertElseFatalError(
                tokens.front().equals(Tokenizer.END_OF_INPUT),
                "Syttax Error: There is syttax after the \"END\" keyword.");
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
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
