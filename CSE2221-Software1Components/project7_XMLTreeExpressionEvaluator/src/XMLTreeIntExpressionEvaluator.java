import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Saeed Alneyadi
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
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
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        /*
         * Variables and Arrays Initialization
         */
        int result = 0;
        int[] numbers = new int[exp.numberOfChildren()];
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

                numbers[idx] = Integer.parseInt(strNum);
            } else {
                numbers[idx] = evaluate(tag);
            }

            /*
             * Checks the value of (idx) and (expLabel).
             */
            if (idx == 0) {

                result = numbers[idx];

            } else if (expLabel.equals("times")) {

                result *= numbers[idx];

            } else if (expLabel.equals("divide")) {

                result /= numbers[idx];

            } else if (expLabel.equals("plus")) {

                result += numbers[idx];

            } else if (expLabel.equals("minus")) {

                result -= numbers[idx];

            }

        }

        /*
         * RETURN Statement: Returning variable (result).
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
