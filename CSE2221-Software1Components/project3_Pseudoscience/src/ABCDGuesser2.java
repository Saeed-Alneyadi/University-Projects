import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * The program guesses the exponents in de Jager formula.
 *
 * @author Saeed Alneyadi
 */
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {

    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        /*
         * Variables Initializations
         */
        double posDouble = 0;
        String input = "";

        /*
         * Prompting the user for a positive real number (double).
         */
        out.print("Please enter a positive real number: ");
        input = in.nextLine();

        /*
         * Checks whether the user enter an double. If so, it converts it to
         * double and stores it to (posDouble).
         */
        if (FormatChecker.canParseDouble(input)) {
            posDouble = Double.parseDouble(input);
        }

        /*
         * Loops until the user enters a positive double.
         */
        while (!FormatChecker.canParseDouble(input) || posDouble <= 0) {
            /*
             * Tell the user that he didn't enter a positive double.
             */
            out.println("Incorrect input.");
            out.println("");

            /*
             * Prompting the user for a positive real number (double).
             */
            out.print("Please enter a positive real number: ");
            input = in.nextLine();

            /*
             * Checks whether the user enter an double. If so, it converts it to
             * double and stores it to (posDouble).
             */
            if (FormatChecker.canParseDouble(input)) {
                posDouble = Double.parseDouble(input);
            }
        }

        /*
         * Returning (posDouble).
         */
        return posDouble;

    }

    /**
     * Generate a random integer in the interval of [0, max].
     *
     * @param max
     *            the maximum number that the random number generator will
     *            provide.
     * @param rnd
     *            Random Object
     * @return a positive real number entered by the user
     */
    private static int getRandNum(int max, Random rnd) {
        /*
         * Variable Initialization
         */
        int randNum;

        /*
         * GeneraE a random integer in the interval of [0, max].
         */
        randNum = (int) (rnd.nextDouble() * max);

        /*
         * Returning (randNum).
         */
        return randNum;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        /*
         * Variables Initializations
         */
        double posDouble = 0;
        String input = "";

        /*
         * Prompting the user for a positive real number (double).
         */
        out.print("Please enter a positive real number not equal to 1.0: ");
        input = in.nextLine();

        /*
         * Checks whether the user enter an double. If so, it converts it to
         * double and stores it to (posDouble).
         */
        if (FormatChecker.canParseDouble(input)) {
            posDouble = Double.parseDouble(input);
        }

        /*
         * Loops until the user enters a positive double.
         */
        while (!FormatChecker.canParseDouble(input) || posDouble <= 0
                || posDouble == 1) {
            /*
             * Tell the user that he didn't enter a positive double.
             */
            out.println("Incorrect input.");
            out.println("");

            /*
             * Prompting the user for a positive real number (double).
             */
            out.print("Please enter a positive real number not equal to 1.0: ");
            input = in.nextLine();

            /*
             * Checks whether the user enter an double. If so, it converts it to
             * double and stores it to (posDouble).
             */
            if (FormatChecker.canParseDouble(input)) {
                posDouble = Double.parseDouble(input);
            }
        }

        /*
         * Returning (posDouble).
         */
        return posDouble;
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
        Random rnd = new Random1L();

        /*
         * Arrays Initializations and Variables Declarations
         */
        final int four = 4;
        final int cons = 16;
        final double relErr = 0.01;
        int calCount = 0;
        double muo, result = 1;
        double[] bases = new double[four];
        double[] exps = new double[four];
        final double[] constants = { -5, -4, -3, -2, -1, -1 / 2, -1 / 3, -1 / 4,
                0, 1 / 4, 1 / 3, 1 / 2, 1, 2, 3, 4, 5 };

        /*
         * Prompting the user for the value of muo (μ).
         */
        out.println("Enter the value of muo (μ): ");
        muo = getPositiveDouble(in, out);
        out.println("");

        /*
         * Prompting the user for the value of (w).
         */
        out.println("Enter the value of (w): ");
        bases[0] = getPositiveDoubleNotOne(in, out);
        out.println("");

        /*
         * Prompting the user for the value of (x).
         */
        out.println("Enter the value of (x): ");
        bases[1] = getPositiveDoubleNotOne(in, out);
        out.println("");

        /*
         * Prompting the user for the value of (y).
         */
        out.println("Enter the value of (y): ");
        bases[2] = getPositiveDoubleNotOne(in, out);
        out.println("");

        /*
         * Prompting the user for the value of (z).
         */
        out.println("Enter the value of (z): ");
        bases[2 + 1] = getPositiveDoubleNotOne(in, out);
        out.println("");

        /*
         * This FOR loop generates randoms exponents and calculates (bases)
         * arrays values to the power of (exps) arrays values, where the powers
         * multiplied to each other.
         */
        for (int i = 0; i < four; i++) {
            exps[i] = constants[getRandNum(cons, rnd)];
            result *= (Math.pow(bases[i], exps[i]));
        }

        /*
         * Loops until the relative error become less than (relErr).
         */
        for (calCount = 0; ((Math.abs(result - muo))
                / muo) >= relErr; calCount++) {
            /*
             * This FOR loop generates randoms exponents and calculates (bases)
             * arrays values to the power of (exps) arrays values, where the
             * powers multiplied to each other.
             */
            result = 1;
            for (int j = 0; j < four; j++) {
                exps[j] = constants[getRandNum(cons, rnd)];
                result *= (Math.pow(bases[j], exps[j]));
            }
        }

        /*
         * Printing the exponents combination of exponents that minimizes the
         * error of the approximation of μ.
         */
        out.println("Combination of exponents that minimizes the error ");
        out.println("of the approximation of μ:");
        out.println("a = " + exps[0]);
        out.println("b = " + exps[1]);
        out.println("c = " + exps[2]);
        out.println("d = " + exps[2 + 1]);
        out.print("And they results: ");
        out.println(result, 2, false);
        out.println("It took " + calCount + " times to reach these results.");

        /*
         * Closing input and output streams.
         */
        in.close();
        out.close();
    }
}
