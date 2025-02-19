import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Saeed Alneyadi
 * @author Isaac Shores
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Single digit prime integer.
     */
    private final int testVar1 = 3;

    /**
     * Two digit integer.
     */
    private final int testVar2 = 35;

    /**
     * Three digit prime integer.
     */
    private final int testVar3 = 353;

    /**
     * Four digit integer.
     */
    private final int testVar4 = 3530;

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /*
     * Test cases for constructors
     */

    /**
     * Tests constructors with default configuration.
     */
    @Test
    public final void testDefaultConstructor() {
        /*
         * Variable declaration/initialization.
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
    }

    /**
     * Tests constructors with integer arg.
     */
    @Test
    public final void testConstructorInt() {
        /*
         * Variable declaration/initialization.
         */
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);

        /*
         * Check values of variables versus expected.
         */
        assertEquals(n, nExpected);
    }

    /*
     * Test cases for kernel methods - multiplyBy10.
     */

    /**
     * Tests implementation of multiplyBy10 with integers.
     */
    @Test
    public final void testMultiplyBy10Int() {
        /*
         * Variable declaration/initialization.
         */
        int input = this.testVar3;
        int output = this.testVar4;
        NaturalNumber n = this.constructorTest(input);
        NaturalNumber nExpected = this.constructorRef(output);

        /*
         * Call method being tested.
         */
        n.multiplyBy10(0);

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
    }

    /**
     * Tests implementation of multiplyBy10 with Strings.
     */
    @Test
    public final void testMultiplyBy10String() {
        /*
         * Variable declaration/initialization.
         */
        String input = "345";
        String output = "3450";
        NaturalNumber n = this.constructorTest(input);
        NaturalNumber nExpected = this.constructorRef(output);

        /*
         * Call method being tested.
         */
        n.multiplyBy10(0);

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
    }

    /**
     * Tests implementation of multiplyBy10 with Natural Numbers.
     */
    @Test
    public final void testMultiplyBy10NN() {
        /*
         * Variable declaration/initialization.
         */
        int inputInt = this.testVar3;
        int outputInt = this.testVar4;
        NaturalNumber inputNN = this.constructorTest(inputInt);
        NaturalNumber outputNN = this.constructorTest(outputInt);

        NaturalNumber n = this.constructorTest(inputNN);
        NaturalNumber nExpected = this.constructorRef(outputNN);

        /*
         * Call method being tested.
         */
        n.multiplyBy10(0);

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
    }

    /**
     * Tests implementation of multiplyBy10 with integers at max.
     */
    @Test
    public final void testMultiplyBy10MaxInt() {
        /*
         * Variable declaration/initialization.
         */
        int input = Integer.MAX_VALUE;
        NaturalNumber n = this.constructorTest(input);
        NaturalNumber nExpected = this.constructorRef(input);

        /*
         * Call method being tested.
         */
        nExpected.multiplyBy10(0);
        n.multiplyBy10(0);

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
    }

    /**
     * Tests implementation of multiplyBy10 with String of max integer.
     */
    @Test
    public final void testMultiplyBy10MaxString() {
        /*
         * Variable declaration/initialization.
         */
        String input = Integer.toString(Integer.MAX_VALUE);
        String output = "21474836470";
        NaturalNumber n = this.constructorTest(input);
        NaturalNumber nExpected = this.constructorRef(output);

        /*
         * Call method being tested.
         */
        n.multiplyBy10(0);

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
    }

    /**
     * Tests implementation of multiplyBy10 with Natural Numbers of max integer.
     */
    @Test
    public final void testMultiplyBy10MaxIntNN() {
        /*
         * Variable declaration/initialization.
         */
        int input = Integer.MAX_VALUE;
        NaturalNumber output = this.constructorTest(input);
        NaturalNumber n = this.constructorTest(input);
        NaturalNumber nExpected = this.constructorRef(output);

        /*
         * Call method being tested.
         */
        nExpected.multiplyBy10(0);
        n.multiplyBy10(0);
        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
    }

    /**
     * Tests implementation of multiplyBy10 with integers at zero.
     */
    @Test
    public final void testMultiplyBy10ZeroInt() {
        /*
         * Variable declaration/initialization.
         */
        int input = 0;
        int output = 0;
        NaturalNumber n = this.constructorTest(input);
        NaturalNumber nExpected = this.constructorRef(output);
        /*
         * Call method being tested.
         */
        n.multiplyBy10(0);
        nExpected.multiplyBy10(0);
        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test cases for kernel methods - divideBy10:
     */

    /**
     * Tests implementation of divideBy10 with integers.
     */
    @Test
    public final void testDivideBy10Int() {
        /*
         * Variable declaration/initialization.
         */
        int input = this.testVar3;
        int output = this.testVar2;
        int remExpected = this.testVar1;
        NaturalNumber n = this.constructorTest(input);
        NaturalNumber nExpected = this.constructorRef(output);

        /*
         * Call method being tested.
         */
        int rem = n.divideBy10();

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
        assertEquals(remExpected, rem);
    }

    /**
     * Tests implementation of divideBy10 with String.
     */
    @Test
    public final void testDivideBy10String() {
        /*
         * Variable declaration/initialization.
         */
        String input = "343";
        String output = "34";
        String remExpected = "3";
        NaturalNumber n = this.constructorTest(input);
        NaturalNumber nExpected = this.constructorRef(output);

        /*
         * Call method being tested.
         */
        int remInt = n.divideBy10();
        String remString = Integer.toString(remInt);

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
        assertEquals(remExpected, remString);
    }

    /**
     * Tests implementation of divideBy10 with Natural Numbers.
     */
    @Test
    public final void testDivideBy10NN() {
        /*
         * Variable declaration/initialization.
         */
        int inputInt = this.testVar3;
        int outputInt = this.testVar2;
        int remExpected = this.testVar1;
        NaturalNumber inputNN = this.constructorTest(inputInt);
        NaturalNumber outputNN = this.constructorTest(outputInt);

        NaturalNumber n = this.constructorTest(inputNN);
        NaturalNumber nExpected = this.constructorRef(outputNN);

        /*
         * Call method being tested.
         */
        int rem = n.divideBy10();

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
        assertEquals(remExpected, rem);
    }

    /**
     * Tests implementation of divideBy10 with integer of zero.
     */
    @Test
    public final void testDivideBy10ZeroInt() {
        /*
         * Variable declaration/initialization.
         */
        int input = 0;
        int output = 0;
        int remExpected = 0;
        NaturalNumber n = this.constructorTest(input);
        NaturalNumber nExpected = this.constructorRef(output);

        /*
         * Call method being tested.
         */
        int rem = n.divideBy10();

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
        assertEquals(remExpected, rem);
    }

    /**
     * Tests implementation of divideBy10 with String of zero.
     */
    @Test
    public final void testDivideBy10ZeroString() {
        /*
         * Variable declaration/initialization.
         */
        String input = "0";
        String output = "0";
        String remExpected = "0";
        NaturalNumber n = this.constructorTest(input);
        NaturalNumber nExpected = this.constructorRef(output);

        /*
         * Call method being tested.
         */
        int remInt = n.divideBy10();
        String rem = Integer.toString(remInt);

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
        assertEquals(remExpected, rem);
    }

    /**
     * Tests implementation of divideBy10 with Natural Numbers of zero.
     */
    @Test
    public final void testDivideBy10ZeroNN() {
        /*
         * Variable declaration/initialization.
         */
        int inputInt = 0;
        int outputInt = 0;
        int remExpected = 0;
        NaturalNumber inputNN = this.constructorTest(inputInt);
        NaturalNumber outputNN = this.constructorTest(outputInt);

        NaturalNumber n = this.constructorTest(inputNN);
        NaturalNumber nExpected = this.constructorRef(outputNN);

        /*
         * Call method being tested.
         */
        int rem = n.divideBy10();

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
        assertEquals(remExpected, rem);
    }

    /**
     * Tests implementation of divideBy10 with single integer.
     */
    @Test
    public final void testDivideBy10SingleInt() {
        /*
         * Variable declaration/initialization.
         */
        int input = this.testVar1;
        int output = 0;
        int remExpected = this.testVar1;
        NaturalNumber n = this.constructorTest(input);
        NaturalNumber nExpected = this.constructorRef(output);

        /*
         * Call method being tested.
         */
        int rem = n.divideBy10();

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
        assertEquals(remExpected, rem);
    }

    /**
     * Tests implementation of divideBy10 with single digit String.
     */
    @Test
    public final void testDivideBy10SingleString() {
        /*
         * Variable declaration/initialization.
         */
        String input = "3";
        String output = "0";
        String remExpected = "3";
        NaturalNumber n = this.constructorTest(input);
        NaturalNumber nExpected = this.constructorRef(output);

        /*
         * Call method being tested.
         */
        int remInt = n.divideBy10();
        String rem = Integer.toString(remInt);
        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
        assertEquals(remExpected, rem);
    }

    /*
     * Test cases for kernel methods - isZero.
     */

    /**
     * Tests implementation of isZero with integer zero.
     */
    @Test
    public final void testIsZeroInt() {
        /*
         * Variable declaration/initialization.
         */
        boolean isZero = true;
        int first = 0;
        NaturalNumber n = this.constructorTest(first);
        NaturalNumber nExpected = this.constructorRef(first);

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
        assertEquals(n.isZero(), isZero);
    }

    /**
     * Tests implementation of isZero with String of zero.
     */
    @Test
    public final void testIsZeroString() {
        /*
         * Variable declaration/initialization.
         */
        boolean isZero = true;
        String input = "0";
        NaturalNumber n = this.constructorTest(input);
        NaturalNumber nExpected = this.constructorRef(input);

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
        assertEquals(n.isZero(), isZero);
    }

    /**
     * Tests implementation of isZero with Natural Number zero.
     */
    @Test
    public final void testIsZeroNN() {
        /*
         * Variable declaration/initialization.
         */
        int inputInt = 0;
        boolean isZero = true;
        NaturalNumber inputNN = this.constructorTest(inputInt);

        NaturalNumber n = this.constructorTest(inputNN);
        NaturalNumber nExpected = this.constructorRef(inputNN);

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
        assertEquals(n.isZero(), isZero);
    }

    /**
     * Tests implementation of isZero with non-zero integer.
     */
    @Test
    public final void testIsZeroIntNonZero() {
        /*
         * Variable declaration/initialization.
         */
        boolean isZero = true;
        int input = this.testVar2;
        NaturalNumber n = this.constructorTest(input);
        NaturalNumber nExpected = this.constructorRef(input);

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
        assertEquals(n.isZero(), !isZero);
    }

    /**
     * Tests implementation of isZero with non-zero String.
     */
    @Test
    public final void testIsZeroStringNonZero() {
        /*
         * Variable declaration/initialization.
         */
        boolean isZero = true;
        String first = "3430";
        NaturalNumber n = this.constructorTest(first);
        NaturalNumber nExpected = this.constructorRef(first);

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
        assertEquals(n.isZero(), !isZero);
    }

    /**
     * Tests implementation of isZero with non-zero Natural Number.
     */
    @Test
    public final void testIsZeroNNNonZero() {
        /*
         * Variable declaration/initialization.
         */
        boolean isZero = true;
        int inputInt = this.testVar4;
        NaturalNumber inputNN = this.constructorTest(inputInt);

        NaturalNumber n = this.constructorTest(inputNN);
        NaturalNumber nExpected = this.constructorRef(inputNN);

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
        assertEquals(n.isZero(), !isZero);
    }

}
