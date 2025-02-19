import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Saeed Alneyadi
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void isWitnessToCompositeness_5_10() {
        NaturalNumber w = new NaturalNumber2(5);
        NaturalNumber wExpected = new NaturalNumber2(5);
        NaturalNumber n = new NaturalNumber2(10);
        NaturalNumber nExpected = new NaturalNumber2(10);
        boolean witness = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(true, witness);
    }

    @Test
    public void isWitnessToCompositeness_9_91() {
        NaturalNumber w = new NaturalNumber2(9);
        NaturalNumber wExpected = new NaturalNumber2(9);
        NaturalNumber n = new NaturalNumber2(91);
        NaturalNumber nExpected = new NaturalNumber2(91);
        boolean witness = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(false, witness);
    }

    @Test
    public void isWitnessToCompositeness_6_35() {
        NaturalNumber w = new NaturalNumber2(6);
        NaturalNumber wExpected = new NaturalNumber2(6);
        NaturalNumber n = new NaturalNumber2(35);
        NaturalNumber nExpected = new NaturalNumber2(35);
        boolean witness = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(true, witness);
    }

    @Test
    public void isPrime1_91() {
        NaturalNumber n = new NaturalNumber2(91);
        NaturalNumber nExpected = new NaturalNumber2(91);
        boolean prime = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, n);
        assertEquals(false, prime);
    }

    @Test
    public void isPrime1_83() {
        NaturalNumber n = new NaturalNumber2(83);
        NaturalNumber nExpected = new NaturalNumber2(83);
        boolean prime = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, n);
        assertEquals(true, prime);
    }

    @Test
    public void isPrime2_24() {
        NaturalNumber n = new NaturalNumber2(24);
        NaturalNumber nExpected = new NaturalNumber2(24);
        boolean prime = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(false, prime);
    }

    @Test
    public void isPrime2_103() {
        NaturalNumber n = new NaturalNumber2(103);
        NaturalNumber nExpected = new NaturalNumber2(103);
        boolean prime = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(true, prime);
    }

    @Test
    public void generateNextLikelyPrime_24() {
        NaturalNumber n = new NaturalNumber2(24);
        NaturalNumber nExpected = new NaturalNumber2(29);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void generateNextLikelyPrime_32() {
        NaturalNumber n = new NaturalNumber2(32);
        NaturalNumber nExpected = new NaturalNumber2(37);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }
}
