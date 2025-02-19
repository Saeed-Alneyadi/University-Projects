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
public final class Newton2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        /*
         * Variables Initializations
         */
        final double epsilonSq = 0.0001 * 0.0001;
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
        String repeat = "y";
        double inVal = 0;
        double result = 0;

        while (repeat.equals("y")) {
            /*
             * Prompting the user for positive double to get estimated square
             * root of it.
             */
            out.print(
                    "Enter a positive double to get estimated square root of it: ");
            inVal = Double.parseDouble(in.nextLine());

            /*
             * Estimating the square root using Newton iteration within a
             * relative error of no more than 0.01% by calling sqrt(double x)
             * function.
             */
            result = sqrt(inVal);

            /*
             * Printing the result from the function.
             */
            out.print("The estimated square root of " + inVal);
            out.print(" within a relative error of no more than 0.01%: ");
            out.println(result);
            out.println("");

            /*
             * Asking if the user want to estimate another square root.
             */
            out.println("Do you want to estimate another square root?");
            out.print("Enter \"y\" for Yes, and anything else for No: ");
            repeat = in.nextLine();
            out.println("");
        }

        /*
         * Close input and output streams.
         */
        in.close();
        out.close();
    }

}
