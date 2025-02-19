import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 * GlossaryTest Testing Class.
 */
public class GlossaryTest {

    /*
     * Test Cases of generateElementsTest.
     */

    /**
     * Testing by using "$1%2# $_%&+=" as an input.
     */
    @Test
    public void generateElementsTestSpecialLetters() {
        /*
         * Creating String (str), (charSet) and (charSetExpected) Sets.
         * (charSet) is created to be inputed to the method. So, in order to be
         * compared with (charSetExpected).
         */
        Set<Character> charSet = new Set1L<>();
        Set<Character> charSetExpected = new Set1L<>();
        String str = "$1%*# $-@!^_&+=";

        /*
         * Adding expected element to (charSetExpected).
         */
        charSetExpected.add('$');
        charSetExpected.add('1');
        charSetExpected.add('%');
        charSetExpected.add('*');
        charSetExpected.add('#');
        charSetExpected.add(' ');
        charSetExpected.add('-');
        charSetExpected.add('@');
        charSetExpected.add('!');
        charSetExpected.add('^');
        charSetExpected.add('_');
        charSetExpected.add('&');
        charSetExpected.add('+');
        charSetExpected.add('=');

        /*
         * Testing the method generateElements.
         */
        Glossary.generateElements(str, charSet);

        /*
         * Check whether the (charSet) is equal to the expected one
         * (charSetExpected).
         */
        assertEquals(charSetExpected, charSet);
    }

    /**
     * Testing by using "a" as an input.
     */
    @Test
    public void generateElementsTestA() {
        /*
         * Creating String (str), (charSet) and (charSetExpected) Sets.
         * (charSet) is created to be inputed to the method. So, in order to be
         * compared with (charSetExpected).
         */
        Set<Character> charSet = new Set1L<>();
        Set<Character> charSetExpected = new Set1L<>();
        String str = "a";

        /*
         * Adding expected element to (charSetExpected).
         */
        charSetExpected.add('a');

        /*
         * Testing the method generateElements.
         */
        Glossary.generateElements(str, charSet);

        /*
         * Check whether the (charSet) is equal to the expected one
         * (charSetExpected).
         */
        assertEquals(charSetExpected, charSet);
    }

    /**
     * Testing by using "hello" as an input.
     */
    @Test
    public void generateElementsTestHello() {
        /*
         * Creating String (str), (charSet) and (charSetExpected) Sets.
         * (charSet) is created to be inputed to the method. So, in order to be
         * compared with (charSetExpected).
         */
        Set<Character> charSet = new Set1L<>();
        Set<Character> charSetExpected = new Set1L<>();
        String str = "hello";

        /*
         * Adding expected element to (charSetExpected).
         */
        charSetExpected.add('h');
        charSetExpected.add('e');
        charSetExpected.add('l');
        charSetExpected.add('o');

        /*
         * Testing the method generateElements.
         */
        Glossary.generateElements(str, charSet);

        /*
         * Check whether the (charSet) is equal to the expected one
         * (charSetExpected).
         */
        assertEquals(charSetExpected, charSet);
    }

    /**
     * Testing by using "ohiostate" as an input.
     */
    @Test
    public void generateElementsTestOhiostate() {
        /*
         * Creating String (str), (charSet) and (charSetExpected) Sets.
         * (charSet) is created to be inputed to the method. So, in order to be
         * compared with (charSetExpected).
         */
        Set<Character> charSet = new Set1L<>();
        Set<Character> charSetExpected = new Set1L<>();
        String str = "ohiostate";

        /*
         * Adding expected element to (charSetExpected).
         */
        charSetExpected.add('o');
        charSetExpected.add('h');
        charSetExpected.add('i');
        charSetExpected.add('s');
        charSetExpected.add('t');
        charSetExpected.add('a');
        charSetExpected.add('e');

        /*
         * Testing the method generateElements.
         */
        Glossary.generateElements(str, charSet);

        /*
         * Check whether the (charSet) is equal to the expected one
         * (charSetExpected).
         */
        assertEquals(charSetExpected, charSet);
    }

    /*
     * Test Cases of nextWordOrSeparator.
     */

    /**
     * Testing by using "hello world", 0, and Sequence with {' ',','} as an
     * input.
     */
    @Test
    public void nextWordOrSeparatorTestHelloWorld() {
        /*
         * Creating (text), (pos), and separators for input. Creating (output)
         * and (outputExpected) for testing. Both (separators) and
         * (separatorsExpected) for checking whether the Set remianed the same
         * or not.
         */
        String text = "hello world";
        final int pos = 0;
        Set<Character> separators = new Set1L<>();
        Set<Character> separatorsExpected = new Set1L<>();
        String output;
        String outputExpected = "hello";

        /*
         * Adding separator characters inside the Set.
         */
        separators.add(' ');
        separators.add(',');
        separatorsExpected.add(' ');
        separatorsExpected.add(',');

        /*
         * Testing the method.
         */
        output = Glossary.nextWordOrSeparator(text, pos, separators);

        /*
         * Checks whether the (separators) is equal to the expected one
         * (separatorsExpected). Checks whether the (output) is equal to the
         * expected one (outputExpected).
         */
        assertEquals(separatorsExpected, separators);
        assertEquals(outputExpected, output);
    }

    /**
     * Testing by using "Ohio $State", 4, and Sequence with {' ',',','$'} as an
     * input.
     */
    @Test
    public void nextWordOrSeparatorTestOhioState() {
        /*
         * Creating (text), (pos), and separators for input. Creating (output)
         * and (outputExpected) for testing. Both (separators) and
         * (separatorsExpected) for checking whether the Set remianed the same
         * or not.
         */
        String text = "Ohio $State";
        final int pos = 4;
        Set<Character> separators = new Set1L<>();
        Set<Character> separatorsExpected = new Set1L<>();
        String output;
        String outputExpected = " $";

        /*
         * Adding separator characters inside the Set.
         */
        separators.add(' ');
        separators.add(',');
        separators.add('$');
        separatorsExpected.add(' ');
        separatorsExpected.add(',');
        separatorsExpected.add('$');

        /*
         * Testing the method.
         */
        output = Glossary.nextWordOrSeparator(text, pos, separators);

        /*
         * Checks whether the (separators) is equal to the expected one
         * (separatorsExpected). Checks whether the (output) is equal to the
         * expected one (outputExpected).
         */
        assertEquals(separatorsExpected, separators);
        assertEquals(outputExpected, output);
    }

    /**
     * Testing by using "CSE", 1, and Sequence with {' ',','} as an input.
     */
    @Test
    public void nextWordOrSeparatorTestComputerScience() {
        /*
         * Creating (text), (pos), and separators for input. Creating (output)
         * and (outputExpected) for testing. Both (separators) and
         * (separatorsExpected) for checking whether the Set remianed the same
         * or not.
         */
        String text = "CSE";
        final int pos = 1;
        Set<Character> separators = new Set1L<>();
        Set<Character> separatorsExpected = new Set1L<>();
        String output;
        String outputExpected = "SE";

        /*
         * Adding separator characters inside the Set.
         */
        separators.add(' ');
        separators.add(',');
        separatorsExpected.add(' ');
        separatorsExpected.add(',');

        /*
         * Testing the method.
         */
        output = Glossary.nextWordOrSeparator(text, pos, separators);

        /*
         * Checks whether the (separators) is equal to the expected one
         * (separatorsExpected). Checks whether the (output) is equal to the
         * expected one (outputExpected).
         */
        assertEquals(separatorsExpected, separators);
        assertEquals(outputExpected, output);
    }

    /*
     * Test Cases of generateTermsSequence.
     */

    /**
     * Testing by using zero terms inside the file as an input.
     */
    @Test
    public void generateTermsSequenceTest0Terms() {
        /*
         * Creating (output) and (outputExpected) to check whether the sequence
         * returned by the method is correct as expected.
         */
        String fileInName = "test0.txt";
        Sequence<String> output;
        Sequence<String> outputExpected = new Sequence1L<>();

        /*
         * Testing the method.
         */
        output = Glossary.generateTermsSequence(fileInName);

        /*
         * Checks whether the (output) is equal to the expected one
         * (outputExpected).
         */
        assertEquals(outputExpected, output);
    }

    /**
     * Testing by using zero terms inside the file as an input.
     */
    @Test
    public void generateTermsSequenceTest1Term() {
        /*
         * Creating (output) and (outputExpected) to check whether the sequence
         * returned by the method is correct as expected.
         */
        String fileInName = "test1.txt";
        Sequence<String> output;
        Sequence<String> outputExpected = new Sequence1L<>();

        /*
         * Adding elements for (outputExpected)
         */
        outputExpected.add(outputExpected.length(), "Java");

        /*
         * Testing the method.
         */
        output = Glossary.generateTermsSequence(fileInName);

        /*
         * Checks whether the (output) is equal to the expected one
         * (outputExpected).
         */
        assertEquals(outputExpected, output);
    }

    /**
     * Testing by using zero terms inside the file as an input.
     */
    @Test
    public void generateTermsSequenceTest2Terms() {
        /*
         * Creating (output) and (outputExpected) to check whether the sequence
         * returned by the method is correct as expected.
         */
        String fileInName = "test2.txt";
        Sequence<String> output;
        Sequence<String> outputExpected = new Sequence1L<>();

        /*
         * Adding elements for (outputExpected)
         */
        outputExpected.add(outputExpected.length(), "MacOSBigSur");
        outputExpected.add(outputExpected.length(), "Windows10");

        /*
         * Testing the method.
         */
        output = Glossary.generateTermsSequence(fileInName);

        /*
         * Checks whether the (output) is equal to the expected one
         * (outputExpected).
         */
        assertEquals(outputExpected, output);
    }

    /**
     * Testing by using zero terms inside the file as an input.
     */
    @Test
    public void generateTermsSequenceTest3Terms() {
        /*
         * Creating (output) and (outputExpected) to check whether the sequence
         * returned by the method is correct as expected.
         */
        String fileInName = "test3.txt";
        Sequence<String> output;
        Sequence<String> outputExpected = new Sequence1L<>();

        /*
         * Adding elements for (outputExpected)
         */
        outputExpected.add(outputExpected.length(), "Banana");
        outputExpected.add(outputExpected.length(), "Apple");
        outputExpected.add(outputExpected.length(), "Orange");

        /*
         * Testing the method.
         */
        output = Glossary.generateTermsSequence(fileInName);

        /*
         * Checks whether the (output) is equal to the expected one
         * (outputExpected).
         */
        assertEquals(outputExpected, output);
    }

    /*
     * Test Cases of generateTermsSequence.
     */

    /**
     * Testing of generateTermsSequence.
     */
    @Test
    public void generateIndexHTMLFileTest() {
        /*
         * Creating input, checking object and values.
         */
        String location = "data";
        Sequence<String> terms = new Sequence1L<>();
        Sequence<String> termsExpected = new Sequence1L<>();

        /*
         * Adding elements to (terms) and (termsExpected).
         */
        terms.add(terms.length(), "book");
        terms.add(terms.length(), "definition");
        terms.add(terms.length(), "glossary");
        terms.add(terms.length(), "language");
        terms.add(terms.length(), "meaning");
        terms.add(terms.length(), "term");
        terms.add(terms.length(), "word");
        termsExpected.add(termsExpected.length(), "book");
        termsExpected.add(termsExpected.length(), "definition");
        termsExpected.add(termsExpected.length(), "glossary");
        termsExpected.add(termsExpected.length(), "language");
        termsExpected.add(termsExpected.length(), "meaning");
        termsExpected.add(termsExpected.length(), "term");
        termsExpected.add(termsExpected.length(), "word");

        /*
         * Testing the method.
         */
        Glossary.generateIndexHTMLFile(terms, location);

        /*
         * Opening stream for the file generated and the check HTML code.
         */
        SimpleReader outputExpected = new SimpleReader1L("check.html");
        SimpleReader output = new SimpleReader1L(location + "/index.html");

        /*
         * Checks whether the (output) is equal to the expected one
         * (outputExpected).
         */
        assertEquals(termsExpected, terms);
        while (!(output.atEOS()) && !(outputExpected.atEOS())) {
            assertEquals(outputExpected.nextLine(), output.nextLine());
        }

        /*
         * Closing input streams.
         */
        output.close();
        outputExpected.close();
    }
}
