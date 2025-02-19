import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Produces HTML files for the terms with their corresponding definitions inside
 * the input file.
 *
 * @author Saeed Alneyadi
 *
 */
public final class Glossary {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    /**
     * Outputs the {@code Map<String,String>} object where each key is a term
     * inside {@code inFile} have a corresponding value that contains the
     * definition for the term.
     *
     * @param fileInName
     *            the name of the file contains the terms with their definitions
     *            in specific order.
     * @return a sequence that contains all terms in alphabetical order.
     * @requires the file with the name {@code fileInName} consists of a single
     *           term on the first line, its definition on the next one or more
     *           lines (terminated by an empty line), another term on the next
     *           line, its definition on the next one or more lines (terminated
     *           by an empty line), etc.
     * @ensures generateTermsSequence /= <>
     *
     *
     */
    public static Sequence<String> generateTermsSequence(String fileInName) {
        /*
         * Objects Instantiations
         */
        Sequence<String> terms = new Sequence1L<>();
        SimpleReader fileIn = new SimpleReader1L(fileInName);

        /*
         * Stores the terms inside (terms) Sequence.
         */
        while (!fileIn.atEOS()) {
            String term = fileIn.nextLine();

            String line = fileIn.nextLine();
            while (!line.equals("")) {
                line = fileIn.nextLine();
            }

            if (terms.length() > 1) {
                for (int idx = 0; idx < terms.length() - 1; idx++) {
                    String str1 = terms.entry(idx);
                    String str2 = terms.entry(idx + 1);

                    if (str1.compareTo(str2) > 0) {
                        terms.replaceEntry(idx, str2);
                        terms.replaceEntry(idx + 1, str1);
                    }
                }
            }

            terms.add(0, term);
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
     * @param location
     *
     * @requires terms /= <> and location /= ""
     * @ensures Index HTML file is generated under {@code location}.
     */
    public static void generateIndexHTMLFile(Sequence<String> terms,
            String location) {
        assert terms != null : "Violation of: terms is not null";

        /*
         * SimpleWriter Object Creation for the index HTML file.
         */
        SimpleWriter fileOut = new SimpleWriter1L(location + "/index.html");

        /*
         * Writing the page title HTML code.
         */
        fileOut.println("<html> <head> <title>Glossary</title>");
        fileOut.println("</head> <body>");

        /*
         * Writing the page headers HTML code.
         */
        fileOut.println("<h2>Glossary</h2>");
        fileOut.println("<hr>");
        fileOut.println("<h3>Index</h3>");

        /*
         * Writing the unordered list of links.
         */
        fileOut.println("<ul>");
        for (int idx = 0; idx < terms.length(); idx++) {
            String term = terms.entry(idx);

            fileOut.print("<li>");
            fileOut.print("<a href=\"" + term + ".html\">" + term);
            fileOut.println("</a></li>");
        }
        fileOut.println("</ul>");

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
     * Generate the terms HTML files where it contains the term as a title and
     * heading. Also, it contains the
     *
     * @param terms
     *            the Sequence that contains all terms in alphabetical order.
     * @param location
     *            the folder name where the HTML files are stored.
     * @param fileInName
     *            the name of the input file, where it contains all terms with
     *            their corresponding definitions.
     * @requires terms /= <>, location is a name of a folder, and fileInName is
     *           a name of input file.
     * @ensures Terms HTML files is generated under {@code location}.
     */
    public static void generateHTMLFilesForTerms(Sequence<String> terms,
            String location, String fileInName) {
        assert terms != null : "Violation of: terms is not null";

        /*
         * Objects Instantiations
         */
        SimpleReader fileIn = new SimpleReader1L(fileInName);

        /*
         * Define separator characters for test
         */
        final String separatorStr = " \t, ";
        Set<Character> separatorSet = new Set1L<>();
        generateElements(separatorStr, separatorSet);

        while (!fileIn.atEOS()) {
            /*
             * Variable Initialization
             */
            String term = fileIn.nextLine();

            /*
             * Opening output stream for HTML file.
             */
            SimpleWriter fileOut = new SimpleWriter1L(
                    location + "/" + term + ".html");

            /*
             * Writing the page title HTML code.
             */
            fileOut.println("<html> <head> <title>" + term + "</title>");
            fileOut.println("</head> <body>");

            /*
             * Writing the page header HTML code.
             */
            fileOut.println("<h2><b><i><font color=\"red\">" + term
                    + "</font></i></b></h2>");

            /*
             * Writing the HTML code for the definition of the term, where it
             * contains some links to the terms that are in the same Sequence
             * (terms).
             */
            fileOut.print("<blockquote>");
            String definition = fileIn.nextLine();
            while (!definition.equals(" ")) {
                int position = 0;
                while (position < definition.length()) {
                    String word = nextWordOrSeparator(definition, position,
                            separatorSet);

                    boolean needLink = false;
                    int idx = 0;
                    while (idx < terms.length() && !needLink) {

                        if (word.equals(terms.entry(idx))) {
                            fileOut.print("<a href=\"" + word + ".html\">"
                                    + word + "</a>");
                            needLink = true;
                        }

                        idx++;
                    }

                    if (!needLink) {
                        fileOut.print(word);
                    }

                    position += word.length();
                }

                definition = " " + fileIn.nextLine();
            }
            fileOut.println("</blockquote>");
            fileOut.println("<hr>");

            /*
             * Writing the code for the link used to return to the index page.
             */
            fileOut.println(
                    "<p>Return to <a href=\"index.html\">index</a>.</p>");
            fileOut.println();
            fileOut.println("</body></html>");

            /*
             * Closing the input and output streams of (fileIn) and (fileOut).
             */
            fileIn.close();
            fileOut.close();
        }
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
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param charSet
     *            the {@code Set} to be replaced
     * @replaces charSet
     * @ensures charSet = entries(str)
     */
    public static void generateElements(String str, Set<Character> charSet) {
        assert str != null : "Violation of: str is not null";
        assert charSet != null : "Violation of: charSet is not null";

        /*
         * Stores every character of (str) inside (charSet).
         */
        for (int idx = 0; idx < str.length(); idx++) {
            char ch = str.charAt(idx);

            if (!charSet.contains(ch)) {
                charSet.add(ch);
            }
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        /*
         * Opening input and output streams.
         */
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Variables Initialization
         */
        String fileInName = "";
        String folderName = "";

        /*
         * Prompting for the name of the input file.
         */
        out.print(
                "Enter the name of the input file (with \".txt\" extension): ");
        fileInName = in.nextLine();

        /*
         * Stores the terms inside (terms) Sequence from a file with a name of
         * (fileInName) by calling generateTermsSequence.
         */
        Sequence<String> terms = generateTermsSequence(fileInName);

        /*
         * Prompting for the name of a folder (folderName) where all the output
         * files will be saved.
         */
        out.print(
                "Enter the name of a folder where all the output files will be saved: ");
        folderName = in.nextLine();

        /*
         * Generate all HTML files by calling the procedures
         * generateIndexHTMLFile and generateHTMLFilesForTerms.
         */
        generateIndexHTMLFile(terms, folderName);
        generateHTMLFilesForTerms(terms, folderName, fileInName);

        /*
         * Printing a statement that declare the succession of the program run.
         */
        out.println();
        out.println("The HTML files are SUCCESSFULLY generated.");

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
