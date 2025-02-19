import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Saeed Alneyadi
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        /*
         * NaturalNumber Object and Array Instantiations, with Variables
         * Initialization.
         */
        NaturalNumber result = new NaturalNumber2();
        NaturalNumber[] numbers = new NaturalNumber2[exp.numberOfChildren()];
        String expLabel = exp.label();

        /*
         * Loops over the child tags of (exp) XMLTree.
         */
        for (int idx = 0; idx < exp.numberOfChildren(); idx++) {
            /*
             * XMLTree (tag) Creation for (exp) child with index (idx).
             */
            XMLTree tag = exp.child(idx);
            String tagLabel = tag.label();

            /*
             * Checks whether (tagLabel) equals "number". If it is, number[idx]
             * is assigned the attribute value of (tag). Otherwise, the returned
             * value from evaluate(tag) will be assigned to numbers[idx].
             */
            if (tagLabel.equals("number")) {
                String strNum = tag.attributeValue("value");

                /*
                 * This will ensures that the NaturalNumber2 receive a
                 * non-negative number.
                 */
                if (Integer.parseInt(strNum) >= 0) {
                    numbers[idx] = new NaturalNumber2(strNum);
                } else {
                    // Stops running the method and displays ERROR Message.
                    String msg = "Violation of: Integer.parseInt(strNum) is a ";
                    msg = msg + "non-negative number.";
                    Reporter.fatalErrorToConsole(msg);
                }

            } else {
                numbers[idx] = evaluate(tag);
            }

            /*
             * Checks the value of (idx) and (expLabel).
             */
            if (idx == 0) {

                result = numbers[idx];

            } else if (expLabel.equals("times")) {

                result.multiply(numbers[idx]);

            } else if (expLabel.equals("divide")) {

                /*
                 * This will ensures that division by zero will not happen.
                 */
                if (!(numbers[idx].isZero())) {
                    result.divide(numbers[idx]);
                } else {
                    // Stops running the method and displays ERROR Message.
                    Reporter.fatalErrorToConsole(
                            "Violation of: numbers[idx] is positive number.");
                }

            } else if (expLabel.equals("plus")) {

                result.add(numbers[idx]);

            } else if (expLabel.equals("minus")) {

                NaturalNumber zero = new NaturalNumber2();

                if (numbers[idx].compareTo(zero) >= 0) {
                    result.subtract(numbers[idx]);
                } else {
                    // Stops running the method and displays ERROR Message.
                    Reporter.fatalErrorToConsole(
                            "Violation of: numbers[idx] is non-negative number.");
                }

            }

        }

        /*
         * RETURN Statement: Returning NaturalNumber (result).
         */
        return result;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
