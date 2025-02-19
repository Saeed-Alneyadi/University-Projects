import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * The test class of NNCalcModel1 class.
 *
 * @author Saeed Alneyadi
 */
public class NNCalcModel1Test {

    /*
     * Test Case of top().
     */

    /**
     * Testing whether top() returns 0 when an object is created from
     * NNCalcModel1.
     */
    @Test
    public void topTest0() {
        /*
         * Creating (object) to do the test on the (top) NaturalNumber.
         */
        NNCalcModel1 object = new NNCalcModel1();

        /*
         * Creating (topNumberExpected) and (topNumber) to test whether (top)
         * value is equal to the expected.
         */
        NaturalNumber topNumberExpected = new NaturalNumber2(0);
        NaturalNumber topNumber = object.top();

        /*
         * Testing whether (topNumberExpected) and (topNumber) are equal.
         */
        assertEquals(topNumberExpected, topNumber);
    }

    /*
     * Test Case of bottom().
     */

    /**
     * Testing whether bottom() returns 0 when an object is created from
     * NNCalcModel1.
     */
    @Test
    public void bottomTest0() {
        /*
         * Creating (object) to do the test on the (bottom) NaturalNumber.
         */
        NNCalcModel1 object = new NNCalcModel1();

        /*
         * Creating (bottomNumberExpected) and (bottomNumber) to test whether
         * (bottom) value is equal to the expected.
         */
        NaturalNumber bottomNumberExpected = new NaturalNumber2(0);
        NaturalNumber bottomNumber = object.bottom();

        /*
         * Testing whether (bottomNumberExpected) and (bottomNumber) are equal.
         */
        assertEquals(bottomNumberExpected, bottomNumber);
    }

}
