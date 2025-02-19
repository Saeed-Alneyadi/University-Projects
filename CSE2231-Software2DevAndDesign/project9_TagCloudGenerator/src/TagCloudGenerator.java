import java.util.Comparator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;

/**
 * This program generates an html file with a tag cloud of specified size from
 * input text file.
 *
 * @author Isaac Shores.22
 * @author Saeed Alneyadi.11
 *
 */
public final class TagCloudGenerator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TagCloudGenerator() {
    }

    /**
     * The maximum font size.
     */
    private static final int MAX_FONT_SIZE = 40;
    /**
     * The increment for font size.
     */
    private static final int INCREMENT_FONT_SIZE = 8;

    /**
     * Returns a negative integer, positive integer, or zero if the first arg
     * that is less than, greater than, or equal to the second.
     *
     */
    private static class StringLT implements Comparator<Pair<String, Integer>> {
        @Override
        public int compare(Pair<String, Integer> key1,
                Pair<String, Integer> key2) {
            return key1.key().compareTo(key2.key());
        }
    }

    /**
     * Returns a negative integer, positive integer, or zero if the first arg
     * that is less than, greater than, or equal to the second.
     *
     */
    private static class IntegerLT
            implements Comparator<Pair<String, Integer>> {
        @Override
        public int compare(Pair<String, Integer> value1,
                Pair<String, Integer> value2) {
            return value2.value().compareTo(value1.value());

        }

    }

    /**
     * Generates all HTML for output file.
     *
     * @param cloudSize
     *            int to specify number of words to include in tag cloud.
     * @param output
     *            Output stream to write in created HTML file.
     * @param input
     *            Input stream with words to count and sort.
     * @param countedWords
     *            Map of words and the count they appear in {@code input}.
     * @param sortedWords
     *            SortingMachine of words sorted by alphabet and count.
     */
    private static void generatePage(SimpleReader input, SimpleWriter output,
            int cloudSize, Map<Pair<String, Integer>, Integer> countedWords,
            SortingMachine<Pair<String, Integer>> sortedWords) {
        /*
         * Prints header and title.
         */
        output.println("<html>\n<head>\n<title>Top " + cloudSize + " words in "
                + input.name() + "</title>");
        output.println("<link href=" + '"'
                + "http://web.cse.ohio-state.edu/software/2231/web-sw2/assignments/projects/tag-cloud-generator/data/tagcloud.css"
                + '"' + " rel=" + '"' + "stylesheet" + '"' + " type =" + '"'
                + "text/css" + '"' + ">");
        output.println("<link href=" + '"' + "doc/tagcloud.css" + '"' + " rel="
                + '"' + "stylesheet" + '"' + " type =" + '"' + "text/css" + '"'
                + ">");
        output.println("</head>\n<body>");
        output.println("<h2>Top " + cloudSize + " words in " + input.name()
                + "</h2>\n<hr>");

        /*
         * Creates box of words by alphabet and fonts set by count.
         */
        generateCloud(output, countedWords, sortedWords);

        output.println("</p>\n</div>\n</body>\n</html>");

    }

    /**
     * Generates HTML for each word, setting their font size based on
     * {@code sortedWords}
     *
     * @param output
     *            Output stream to write in created HTML file.
     * @param countedWords
     *            Map of words and their number of entries.
     * @param sortedWords
     *            SortingMachine of words sorted by alphabet.
     */
    private static void generateCloud(SimpleWriter output,
            Map<Pair<String, Integer>, Integer> countedWords,
            SortingMachine<Pair<String, Integer>> sortedWords) {
        output.println("<div class=\"cdiv\">");

        output.println("<p class=" + '"' + "cbox" + '"' + ">");
        sortedWords.changeToExtractionMode();
        while (sortedWords.size() > 0) {
            Pair<String, Integer> pair = sortedWords.removeFirst();
            output.println(
                    "<span style=" + '"' + "cursor:default" + '"' + " class="
                            + '"' + 'f' + countedWords.value(pair).toString()
                            + '"' + " title=" + '"' + "count: " + pair.value()
                            + '"' + ">" + pair.key() + "</span>");

        }
    }

    /**
     * Takes in a line of code and returns the first token based on given
     * separator characters.
     *
     * @param lineFromInput
     *            A line of text to separate into tokens.
     * @param position
     *            Index of {@code lineFromInput}.
     * @param separatorSet
     *            Set of characters to prompt token separation.
     * @return next token from {@code lineFromInput}
     * @requires <pre>
     * {@code 0 <= position < |text|}
     * </pre>
     */
    private static String generateNextToken(String lineFromInput, int position,
            Set<Character> separatorSet) {
        assert lineFromInput != null : "Violation of: text is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < lineFromInput
                .length() : "Violation of: position < |text|";

        /*
         * Increments counter until a separator is seen, then returns substring
         * from given position to position counter.
         */
        int positionCounter = position;
        if (!separatorSet.contains(lineFromInput.charAt(position))) {
            while (positionCounter < lineFromInput.length() && !separatorSet
                    .contains(lineFromInput.charAt(positionCounter))) {
                positionCounter++;
            }
        } else {
            while (positionCounter < lineFromInput.length() && separatorSet
                    .contains(lineFromInput.charAt(positionCounter))) {
                positionCounter++;
            }
        }
        return lineFromInput.substring(position, positionCounter);
    }

    /**
     * Assigns font as the value to a key of map that contains the words and count.
     *
     * @param inputFile
     *            the .txt file to count word entries in.
     * @param words
     *            Map that contains the word and its number of entries.
     * @restores words
     * @return countedWords Map of words and respective font size.
     */
    public static Map<Pair<String, Integer>, Integer> countWords(
            SimpleReader inputFile, Map<String, Integer> words) {

        /*
         * Clears word map, creates word map to be returned, and creates
         * comparator and respecive SortingMachine.
         */
        words.clear();
        Map<Pair<String, Integer>, Integer> countedWords = new Map1L<Pair<String, Integer>, Integer>();
        Comparator<Map.Pair<String, Integer>> intCompare = new IntegerLT();
        SortingMachine<Map.Pair<String, Integer>> wordsByCount = new SortingMachine1L<Map.Pair<String, Integer>>(
                intCompare);

        /*
         * Creates set of token separators.
         */
        Set<Character> separatorSet = new Set1L<Character>();
        String separators = " \t\n\r,.-:;/\"!?_@#$%&*[]()";
        for (int i = 0; i < separators.length() - 1; i++) {
            separatorSet.add(separators.charAt(i));
        }

        /*
         * Main loop until end of input file.
         */
        int position = 0;
        while (!inputFile.atEOS()) {
            String line = inputFile.nextLine();
            /*
             * Sets entire line of text to lower case.
             */
            line = line.toLowerCase();
            position = 0;

            /*
             * Loops through each token of a line from text file. If token is a
             * word, add to word map and set value to 1. If token is already in
             * word map, increase value by 1.
             */
            while (position < line.length()) {
                String token = generateNextToken(line, position, separatorSet);
                if (!separatorSet.contains(token.charAt(0))) {
                    if (!words.hasKey(token)) {
                        words.add(token, 1);
                    } else {
                        int wordValue = words.value(token);
                        wordValue++;
                        words.replaceValue(token, wordValue);
                    }
                }
                /*
                 * Shift position to next token in the line.
                 */
                position += token.length();
            }
        }

        /*
         * Sets font of words based on count, then transfers word entries into
         * countedWords map to return.
         */
        int maxFont = MAX_FONT_SIZE;
        double maxCount = 0;
        double minCount = Integer.MAX_VALUE;
        Map<String, Integer> tempWords = words.newInstance();
        while (words.size() != 0) {
            Pair<String, Integer> removedWord = words.removeAny();
            double valueRemoved = removedWord.value().doubleValue();
            if (valueRemoved > maxCount) {
                maxCount = valueRemoved;
            } else if (valueRemoved < minCount) {
                minCount = valueRemoved;
            }
            wordsByCount.add(removedWord);
            tempWords.add(removedWord.key(), removedWord.value());
        }
        words.transferFrom(tempWords);
        wordsByCount.changeToExtractionMode();
        while (wordsByCount.size() > 0) {
            int fontSize = 0;
            Pair<String, Integer> wordAndCount = wordsByCount.removeFirst();
            int countOfWord = wordAndCount.value();
            if (countOfWord > minCount) {
                double font = Math.ceil((maxFont * (countOfWord - minCount)
                        / (maxCount - minCount)));
                fontSize = (int) font;
                fontSize += INCREMENT_FONT_SIZE;
            }
            countedWords.add(wordAndCount, fontSize);
        }
        return countedWords;
    }

    /**
     * Counts the number of times each word is used in {@code words} and sorts
     * by alphabet and returns only the specified number of words from
     * {@code cloudSize}.
     *
     * @param words
     *            Map of words used in input.
     * @param cloudSize
     *            int to specify number of words to include in SortingMachine.
     * @clears {@code words}
     * @return orderedWords SortingMachine with highest used words.
     *
     */
    public static SortingMachine<Pair<String, Integer>> orderWords(
            Map<String, Integer> words, int cloudSize) {
        assert words != null : "Violation of: words is not null";

        /*
         * Creates comparators and respective sorting machines.
         */
        Comparator<Map.Pair<String, Integer>> stringCompare = new StringLT();
        SortingMachine<Map.Pair<String, Integer>> wordsByAlphabet = new SortingMachine1L<Map.Pair<String, Integer>>(
                stringCompare);

        Comparator<Map.Pair<String, Integer>> intCompare = new IntegerLT();
        SortingMachine<Map.Pair<String, Integer>> wordsByCount = new SortingMachine1L<Map.Pair<String, Integer>>(
                intCompare);

        /*
         * Adds words to count sorter.
         */
        while (words.size() != 0) {
            Pair<String, Integer> temp = words.removeAny();
            wordsByCount.add(temp);
        }

        /*
         * Adds words from count sorter to alphabet sorter, but only up to cloud
         * size.
         */
        wordsByCount.changeToExtractionMode();
        int size = wordsByCount.size();
        for (int i = 0; i < size && i < cloudSize; i++) {
            Pair<String, Integer> temp = wordsByCount.removeFirst();
            wordsByAlphabet.add(temp);
        }

        /*
         * Returns cloud size words sorted by alphabet.
         */
        return wordsByAlphabet;

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
         * Asks user for existing .txt input, name of .html output to generate,
         * and how many words to include in tag cloud.
         */
        out.print("Enter input file name with valid extension[example.txt]: ");
        SimpleReader inputFile = new SimpleReader1L(in.nextLine());
        out.print(
                "Enter output file name with valid extension[example.html]: ");
        SimpleWriter outputFile = new SimpleWriter1L(in.nextLine());
        out.print("Enter number of words to include in tag cloud: ");
        int cloudSize = in.nextInteger();

        /*
         * Creates and orders maps of words from given input file.
         */
        Map<String, Integer> words = new Map1L<String, Integer>();
        Map<Pair<String, Integer>, Integer> countedWords = countWords(inputFile,
                words);
        SortingMachine<Pair<String, Integer>> orderedWords = orderWords(words,
                cloudSize);

        /*
         * Calls main HTML generation method.
         */
        generatePage(inputFile, outputFile, cloudSize, countedWords,
                orderedWords);

        /*
         * Close input and output streams
         */
        in.close();
        inputFile.close();
        out.close();
        outputFile.close();
    }

}
