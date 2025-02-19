import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

/**
 * This program generates an HTML file with a tag cloud of specified size from
 * input text file.
 *
 * @author Isaac Shores.22
 * @author Saeed Alneyadi.11
 */
public final class JavaTagCloudGenerator {

    /**
     * Default constructor.
     */
    private JavaTagCloudGenerator() {
    }

    /**
     * Separators.
     */
    private static final String SEPARATORS = " \t\n\r,.-:;/\"!?_@#$%&*[]()";

    /**
     * The maximum font size.
     */
    private static final double MAX_FONT_SIZE = 40;

    /**
     * The increment for font size.
     */
    private static final int INCREMENT_FONT_SIZE = 8;

    /**
     * Returns a negative integer, zero, or a positive integer as the first
     * argument is less than, equal to, or greater than the second.
     *
     */
    private static class IntCompare implements Comparator<String> {

        /**
         * Map to use in map comparator.
         */
        private Map<String, Integer> compareMap;

        /**
         * Map comparator.
         *
         * @param compareMap
         */
        IntCompare(Map<String, Integer> compareMap) {
            this.compareMap = compareMap;
        }

        @Override
        public int compare(String str1, String str2) {
            int firstInt = this.compareMap.get(str1);
            int secondInt = this.compareMap.get(str2);
            if (secondInt > firstInt) {
                return 1;
            } else if (firstInt > secondInt) {
                return -1;
            } else {
                return str2.compareTo(str1);
            }
        }
    }

    /**
     * Counts all words from file and adds word and count to {@code wordMap}.
     *
     * @param inputFileStream
     *            {@code BufferedReader} input stream
     *
     *
     *            <pre>
     * @return {@code wordMap} containing all words and their counts from input
     * {@code inputFileStream} is empty at end
     * </pre>
     * @throws IOException
     */
    public static Map<String, Integer> countWords(
            BufferedReader inputFileStream) throws IOException {
        assert inputFileStream
                .ready() : "Violation of: inputFileStream is open";

        /*
         * Creates word map to be returned.
         */
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        String word = "";
        try {
            /*
             * Main loop that loops through each token of a line from text file.
             * If token is a word, add to word map and set value to 1. If token
             * is already in word map, increase value by 1.
             */
            String line = inputFileStream.readLine();
            while (line != null) {
                /*
                 * Sets entire line of text to lower case.
                 */
                line = line.toLowerCase();
                int length = line.length();
                for (int i = 0; i < length; i++) {
                    /*
                     * Separates words based on token separators.
                     */
                    if (!SEPARATORS
                            .contains(Character.toString(line.charAt(i)))) {
                        Character letter = line.charAt(i);
                        word = word.concat(letter.toString());
                    } else if (!word.equals("")) {
                        if (wordMap.containsKey(word)) {
                            int value = wordMap.get(word);
                            value++;
                            wordMap.put(word, value);
                        } else {
                            wordMap.put(word, 1);
                        }
                        word = "";
                    }
                }
                if (!word.equals("")) {
                    if (wordMap.containsKey(word)) {
                        int value = wordMap.get(word);
                        value++;
                        wordMap.put(word, value);
                    } else {
                        wordMap.put(word, 1);
                    }
                    word = "";
                }
                line = inputFileStream.readLine();
            }
        } catch (IOException e) {
            System.err.print("Error reading stream from file " + e);
        }
        return wordMap;
    }

    /**
     * Takes a map of words and their counts and sorts the top N most occurring
     * in alphabetical order and returns a Queue containing their corresponding
     * fonts sizes.
     *
     * @param countedWords
     *            {@code Map<String,Integer>} containing all words and their
     *            counts
     * @param cloudSize
     *            {@code Integer} containing the number of words to display in
     *            tagCloud
     * @param orderedWordMap
     *            A {@code TreeMap} that will sort word entries and their counts
     *
     * @return A {@code Queue} of the fonts
     *
     * @updates orderedWordMap
     * @ensures <pre>
     * {@code Queue} fontSizes is proportional to their occurrences and in order.
     * </pre>
     */
    public static Queue<Integer> orderWords(Map<String, Integer> countedWords,
            int cloudSize, Map<String, Integer> orderedWordMap) {

        /*
         * Creates comparator and respective map and a queue to return.
         */
        IntCompare integerCompare = new IntCompare(countedWords);
        TreeMap<String, Integer> wordsByCount = new TreeMap<String, Integer>(
                integerCompare);
        Queue<Integer> fontSizeQueue = new LinkedList<Integer>();
        orderedWordMap.clear();

        /*
         * Adds words to set by count.
         */
        Set<Entry<String, Integer>> wordSet = countedWords.entrySet();
        for (Entry<String, Integer> entry : wordSet) {
            wordsByCount.put(entry.getKey(), entry.getValue());
        }

        /*
         * Adds words to word map.
         */
        double max = wordsByCount.firstEntry().getValue();
        double min = wordsByCount.lastEntry().getValue();
        int counter = 0;
        while (counter < cloudSize && !wordsByCount.isEmpty()) {
            Entry<String, Integer> nextWord = wordsByCount.firstEntry();
            String key = nextWord.getKey();
            int value = nextWord.getValue();
            orderedWordMap.put(key, value);
            wordsByCount.remove(key);
            counter++;
        }

        /*
         * Sets word font and adds words to word queue by alphabet.
         */
        for (Map.Entry<String, Integer> entry : orderedWordMap.entrySet()) {
            int fontSize = 0;
            int currentCount = entry.getValue();
            if (currentCount > min) {
                if (!((max - min) == 0)) {
                    double font = Math
                            .ceil((MAX_FONT_SIZE * (currentCount - min))
                                    / (max - min));
                    fontSize = (int) font;
                    fontSize += INCREMENT_FONT_SIZE;
                } else {
                    double font = Math
                            .ceil((MAX_FONT_SIZE * (currentCount - min)));
                    fontSize = (int) font;
                    fontSize += INCREMENT_FONT_SIZE;
                }

            }
            fontSizeQueue.add(fontSize);
        }

        /*
         * Returns cloud size words sorted by alphabet.
         */
        return fontSizeQueue;
    }

    /**
     * Generates HTML for each word, setting their font size based on
     * {@code orderedWordMap}.
     *
     * @param output
     *            Output stream to write in created HTML file.
     * @param orderedWordQueue
     *            Queue of words sorted by alphabet.
     * @param orderedWordMap
     *            TreeMap of words and their number of entries.
     */
    private static void generateCloud(PrintWriter output,
            TreeMap<String, Integer> orderedWordMap,
            Queue<Integer> orderedWordQueue) {
        output.println("<div class=\"cdiv\">");
        output.println("<p class=\"cbox\">");

        while (orderedWordMap.size() > 0) {
            output.println("<span style=\"cursor:default\" class=" + '"' + 'f' + orderedWordQueue.remove() + "\" title=\"count: "
                    + orderedWordMap.firstEntry().getValue() + "\">"
                    + orderedWordMap.firstEntry().getKey() + "</span>");
            orderedWordMap.remove(orderedWordMap.firstEntry().getKey());
        }
    }

    /**
     * Creates the HTML code with the header, word name, and appropriate font
     * size, the footer.
     *
     * @param orderedWordMap
     *            {code TreeMap} that contains the top N words sorted
     *            alphabetically with their counts
     *
     * @param orderedWordQueue
     *            {@code Queue} containing fontSizes retrieved from entries in
     *            TreeMap
     *
     *
     * @param output
     *            {@code PrintWrtier}output stream
     *
     * @param inputFileName
     * @param cloudSize
     *
     *
     */
    public static void generatePage(Queue<Integer> orderedWordQueue,
            TreeMap<String, Integer> orderedWordMap, PrintWriter output,
            String inputFileName, int cloudSize) {
        assert !orderedWordQueue
                .isEmpty() : "Violation of: orderedWordQueue cannot be empty";
        assert !orderedWordMap
                .isEmpty() : "Violation of: orderedWordMap cannot be empty";
        assert !inputFileName.equals(
                "") : "Violation of: inputFileName cannot be an empty string";

        /*
         * Prints header and title.
         */
        output.println("<html>\n<head>\n<title>Top " + cloudSize + " words in "
                + inputFileName + "</title>");
        output.println("<link href=" + '"'
                + "http://web.cse.ohio-state.edu/software/2231/web-sw2/assignments/projects/tag-cloud-generator/data/tagcloud.css"
                + "\" rel=\"stylesheet\" type =\"text/css\">");
        output.println("<link href=\"doc/tagcloud.css\" rel=\"stylesheet\" type =\"text/css\">");

        output.println("</head>\n<body>");
        output.println("<h2>Top " + cloudSize + " words in " + inputFileName
                + "</h2>");
        output.println("<hr>");

        /*
         * Creates box of words by alphabet and fonts set by count.
         */
        generateCloud(output, orderedWordMap, orderedWordQueue);

        output.println("</p>\n</div>\n</body>\n</html>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        /*
         * Creates input and output streams.
         */
        BufferedReader input = new BufferedReader(
                new InputStreamReader(System.in));
        BufferedReader inputFileStream = null;
        PrintWriter outputFileStream = null;

        /*
         * Asks user for existing .txt input, name of .html output to generate,
         * and how many words to include in tag cloud.
         */
        System.out.print(
                "Enter input file name with valid extension[example.txt]: ");
        String inputFileName = "";
        try {
            inputFileName = input.readLine();
        } catch (IOException e) {
            System.err.println("Error reading stream from system input " + e);
            return;
        }
        try {
            inputFileStream = new BufferedReader(new FileReader(inputFileName));
        } catch (IOException e) {
            System.err.println("Error opening reader to file location " + e);
            return;
        }

        System.out.print(
                "Enter output file name with valid extension[example.html]: ");
        String outputFileName = "";
        try {
            outputFileName = input.readLine();
        } catch (IOException e) {
            System.err.println("Error reading stream from system input " + e);
            inputFileStream.close();
            return;
        }
        System.out.print("Enter number of words to include in tag cloud: ");
        int cloudSize = 0;
        try {
            cloudSize = Integer.parseInt(input.readLine());
        } catch (IOException e) {
            System.err.println("Error reading stream from system input " + e);
            inputFileStream.close();
            return;
        }
        outputFileStream = new PrintWriter(
                new BufferedWriter(new FileWriter(outputFileName)));

        /*
         * Creates and orders maps and queue of words from given input file.
         */
        Map<String, Integer> countedWords = countWords(inputFileStream);
        TreeMap<String, Integer> orderedWordMap = new TreeMap<String, Integer>();
        Queue<Integer> orederedWordQueue = orderWords(countedWords, cloudSize,
                orderedWordMap);

        /*
         * Calls main HTML generation method.
         */
        generatePage(orederedWordQueue, orderedWordMap, outputFileStream,
                inputFileName, cloudSize);

        /*
         * Close input and output streams
         */
        inputFileStream.close();
        input.close();
        outputFileStream.close();
    }

}
