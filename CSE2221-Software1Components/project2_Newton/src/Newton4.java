import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Saeed Alneyadi
 *
 */
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error (epsilon).
     *
     * @param x
     *            positive number to compute square root of
     * @param epsilon
     *            the chosen relative error
     * @return estimate of square root
     */
    private static double sqrt(double x, double epsilon) {
        /*
         * Variables Initializations
         */
        final double epsilonSq = epsilon * epsilon;
        double est = x;

        /*
         * Estimating the square root using Newton iteration within a relative
         * error of no more than 0.01%.
         */
        while (!(Math.abs(est * est - x) / x < epsilonSq) && (est != 0)) {
            est = (est + (x / est)) / 2;
        }

        /*
         * Returning the estimate of the square root of x.
         */
        return est;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        /*
         * Objects Instantiations and Variables Initializations
         */
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        double relErrVal = 0;
        double inVal = 0;
        double result = 0;

        /*
         * Prompting the user for relative error (epsilon).
         */
        out.print("Enter the wanted relative error (epsilon): ");
        relErrVal = Double.parseDouble(in.nextLine());
        out.println("");

        /*
         * Prompting the user for positive double to get estimated square root
         * of it.
         */
        out.println(
                "Enter a positive double to get estimated square root of it, ");
        out.print("(Enter a negative number to exit): ");
        inVal = Double.parseDouble(in.nextLine());

        while (inVal >= 0) {
            /*
             * Estimating the square root using Newton iteration within a
             * relative error of no more than 0.01% by calling sqrt(double x,
             * double epsilon) function.
             */
            result = sqrt(inVal, relErrVal);

            /*
             * Printing the result from the function.
             */
            out.print("The estimated square root of " + inVal);
            out.print(" within a relative error of no more than 0.01%: ");
            out.println(result);
            out.println("");

            /*
             * Prompting the user for relative error (epsilon).
             */
            out.print("Enter the wanted relative error (epsilon): ");
            relErrVal = Double.parseDouble(in.nextLine());
            out.println("");

            /*
             * Prompting the user for positive double to get estimated square
             * root of it.
             */
            out.println(
                    "Enter a positive double to get estimated square root of it, ");
            out.print("(Enter a negative number to exit): ");
            inVal = Double.parseDouble(in.nextLine());
            out.println("");
        }

        /*
         * Close input and output streams.
         */
        in.close();
        out.close();
    }

}
