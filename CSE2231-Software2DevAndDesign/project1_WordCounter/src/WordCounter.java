import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A program that counts word occurrences in a given input file and outputs an
 * HTML document with a table of the words and counts listed in alphabetical
 * order.
 *
 * @author Saeed Alneyadi
 *
 */
public final class WordCounter {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private WordCounter() {
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        /*
         * Variables Creations
         */
        String result = "";
        boolean isSperatorStr;

        /*
         * Checks whether (separators) contains the character at (position) in
         * (text). If it is, isSperatorStr is assigned true. Otherwise false.
         */
        if (separators.contains(text.charAt(position))) {
            isSperatorStr = true;
        } else {
            isSperatorStr = false;
        }

        /*
         * Stores the characters contained inside (separators) if the character
         * at (position) is contained inside (separators) and it the characters
         * not contained inside (separators) if the character at (position) is
         * not contained inside (separators).
         */
        for (int idx = position; idx < text.length(); idx++) {
            char ch = text.charAt(idx);
            boolean chIsSperatorStr = separators.contains(ch);

            if ((isSperatorStr && chIsSperatorStr)
                    || (!isSperatorStr && !chIsSperatorStr)) {
                result = result.concat(Character.toString(ch));
            } else {
                idx = text.length();
            }
        }

        return result;
    }

    /**
     * Outputs the {@code Sequence<String>} object where each key is a term
     * inside {@code inFile} have a corresponding value that contains the
     * definition for the term.
     *
     * @param inputFileName
     *            the name of the file contains the terms with their definitions
     *            in specific order.
     * @return a sequence that contains all terms in alphabetical order.
     * @requires the file with the name {@code fileInName} consists of a single
     *           term on the first line, its definition on the next one or more
     *           lines (terminated by an empty line), another term on the next
     *           line, its definition on the next one or more lines (terminated
     *           by an empty line), etc.
     * @ensures generateTermsSequence /= <> and [{@code enerateTermsSequence}
     *          will have all the words that are inside the file with name
     *          {@code inputFileName}]
     *
     *
     */
    public static Sequence<String> generateTermsSequence(String inputFileName) {
        /*
         * Objects Instantiations
         */
        Sequence<String> terms = new Sequence1L<>();
        Set<Character> separatorSet = new Set1L<>();
        SimpleReader fileIn = new SimpleReader1L(inputFileName);

        /*
         * Adding the separators to (separatorSet).
         */
        separatorSet.add(' ');
        separatorSet.add(',');
        separatorSet.add('.');
        separatorSet.add('-');
        separatorSet.add('\n');

        /*
         * Stores the terms inside (terms) Sequence.
         */
        while (!fileIn.atEOS()) {
            String line = fileIn.nextLine();

            int position = 0;
            while (position < line.length()) {
                String word = nextWordOrSeparator(line, position, separatorSet);

                if (!separatorSet.contains(word.charAt(0))) {
                    terms.add(0, word);
                }

                if (terms.length() > 1) {
                    for (int idx = 0; idx < terms.length() - 1; idx++) {
                        String str1 = terms.entry(idx);
                        String str2 = terms.entry(idx + 1);
                        int cond1 = str1.toLowerCase()
                                .compareTo(str2.toLowerCase());
                        int cond2 = str1.compareTo(str2);

                        if (cond1 > 0 || (cond1 == 0 && cond2 < 0)) {
                            terms.replaceEntry(idx, str2);
                            terms.replaceEntry(idx + 1, str1);
                        }
                    }
                }

                position += word.length();
            }

        }

        /*
         * Closing the input stream of (fileIn).
         */
        fileIn.close();

        return terms;
    }

    /**
     * Generate the index HTML file where it contains the string "Glossary" as a
     * title and heading. Also, it contains "Index" heading with a list of terms
     * stored inside {@code terms}, where each term is a link to the term's HTML
     * file that is generated from other procedure.
     *
     * @param terms
     *            the channel element XMLTree
     * @param inputFileName
     *            the name of the input file
     * @param outputFileName
     *            the name of the output file
     *
     * @requires terms /= <> and location /= ""
     * @ensures [Index HTML file is generated with a header ("Words Counted in "
     *          + inputFileName) and table separated by a line. The table
     *          contains the words each with their count.]
     */
    public static void generateHTMLFile(Sequence<String> terms,
            String inputFileName, String outputFileName) {
        assert terms != null : "Violation of: terms is not null";

        /*
         * SimpleWriter Object Creation for the index HTML file.
         */
        SimpleWriter fileOut = new SimpleWriter1L(outputFileName);

        /*
         * Writing the code of page title HTML code.
         */
        fileOut.println("<html> <head> <title>Words Counted in " + inputFileName
                + "</title>");
        fileOut.println("</head> <body>");

        /*
         * Writing the code of page headers HTML code.
         */
        fileOut.println("<h2>Words Counted in " + inputFileName + "</h2>");
        fileOut.println("<hr>");

        /*
         * Writing the code of table header cells.
         */
        fileOut.println("<table border=\"1\">");
        fileOut.println("<tbody><tr>");
        fileOut.println("<th>Words</th>");
        fileOut.println("<th>Counts</th>");

        /*
         * Writing the code of table rows cells.
         */

        for (int idx = 0; idx < terms.length(); idx++) {
            String term = terms.entry(idx);
            int count = 1;

            while ((idx + 1) < terms.length()
                    && term.equals(terms.entry(idx + 1))) {
                count++;
                idx++;
            }

            fileOut.println("<tr>");
            fileOut.print("<td>");
            fileOut.print(term);
            fileOut.println("</td>");
            fileOut.print("<td>");
            fileOut.print(count);
            fileOut.println("</td>");
            fileOut.println("</tr>");
        }

        /*
         * Writing the closing tags of <body> and <html> tags.
         */
        fileOut.println();
        fileOut.println("</body></html>");

        /*
         * Closing the output stream of (fileOut).
         */
        fileOut.close();
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

        /*
         * Prompting for the name of the input file.
         */
        out.print("Enter the name of the input file (with .txt extension): ");
        String inputFileName = in.nextLine();

        /*
         * Prompting for the name of the output file.
         */
        out.print("Enter the name of the output file (with .html extension): ");
        String outputFileName = in.nextLine();

        /*
         * Calling generateTermsSequence() function to generate a sequence with
         * all the words order in alphabetic order.
         */
        Sequence<String> terms = generateTermsSequence(inputFileName);

        /*
         * Calling generateHTMLFile() procedure to generate the HTML file with
         * the required properties.
         */
        generateHTMLFile(terms, inputFileName, outputFileName);

        /*
         * Printing a statement that declare the succession of the program run.
         */
        out.println();
        out.println("The HTML file are SUCCESSFULLY generated.");

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
