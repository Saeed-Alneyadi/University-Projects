import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    /*
     * Tests of combination
     */

    @Test
    public void testCombination_2() {
        String str1 = "ATTGTTACGGTT";
        String str2 = "TTAAGCTAGGCT";
        final int overlap = 2;
        String combination = StringReassembly.combination(str1, str2, overlap);
        String combinationExpected = "ATTGTTACGGTTAAGCTAGGCT";
        assertEquals(combinationExpected, combination);
    }

    @Test
    public void testCombination_3() {
        String str1 = "ATGCGCTAGAT";
        String str2 = "GATAAGCCTAG";
        final int overlap = 3;
        String combination = StringReassembly.combination(str1, str2, overlap);
        String combinationExpected = "ATGCGCTAGATAAGCCTAG";
        assertEquals(combinationExpected, combination);
    }

    @Test
    public void testCombination_4() {
        String str1 = "AGTCCCGATGAGTTG";
        String str2 = "GTTGCCTGAGTCAAG";
        final int overlap = 4;
        String combination = StringReassembly.combination(str1, str2, overlap);
        String combinationExpected = "AGTCCCGATGAGTTGCCTGAGTCAAG";
        assertEquals(combinationExpected, combination);
    }

    /*
     * Tests of addToSetAvoidingSubstrings
     */

    @Test
    public void testAddToSetAvoidingSubstrings_eye() {
        Set<String> strSet = new Set1L<String>();
        Set<String> strSetExpected = new Set1L<String>();
        String str = "eye";
        strSet.add("OSU");
        strSet.add("ohio");
        strSet.add("buckeye");
        strSetExpected.add("OSU");
        strSetExpected.add("ohio");
        strSetExpected.add("buckeye");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSetExpected, strSet);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_columbus() {
        Set<String> strSet = new Set1L<String>();
        Set<String> strSetExpected = new Set1L<String>();
        String str = "columbus";
        strSet.add("OSU");
        strSet.add("ohio");
        strSet.add("buckeye");
        strSetExpected.add("OSU");
        strSetExpected.add("ohio");
        strSetExpected.add("buckeye");
        strSetExpected.add("columbus");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSetExpected, strSet);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_ohiostate() {
        Set<String> strSet = new Set1L<String>();
        Set<String> strSetExpected = new Set1L<String>();
        String str = "ohiostate";
        strSet.add("OSU");
        strSet.add("ohio");
        strSet.add("buckeye");
        strSetExpected.add("OSU");
        strSetExpected.add("buckeye");
        strSetExpected.add("ohiostate");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSetExpected, strSet);
    }

    /*
     * Tests of linesFromInput
     */

    @Test
    public void testLinesFromInput_ohio() {
        SimpleWriter out = new SimpleWriter1L("out_ohio.txt");
        out.println("ohio");
        out.println("OSU");
        out.println("columbus");
        SimpleReader in = new SimpleReader1L("out_ohio.txt");

        Set<String> linesFromInputExpected = new Set1L<>();

        linesFromInputExpected.add("ohio");
        linesFromInputExpected.add("OSU");
        linesFromInputExpected.add("columbus");

        Set<String> linesFromInput = StringReassembly.linesFromInput(in);

        assertEquals(linesFromInputExpected, linesFromInput);
    }

    /*
     * Tests of printWithLineSeparators
     */

    @Test
    public void testPrintWithLineSeparators_OSU() {
        SimpleWriter out = new SimpleWriter1L("out_OSU.txt");
        SimpleWriter outExpected = new SimpleWriter1L("outExpected_OSU.txt");
        String text = "OSU~ohiostate~columbus";
        outExpected.println("OSU");
        outExpected.println("ohiostate");
        outExpected.print("columbus");
        StringReassembly.printWithLineSeparators(text, out);
        SimpleReader in = new SimpleReader1L("out_OSU.txt");
        SimpleReader inExpected = new SimpleReader1L("outExpected_OSU.txt");

        while (!(in.atEOS()) && !(inExpected.atEOS())) {
            assertEquals(inExpected.nextLine(), in.nextLine());
        }
    }
}
