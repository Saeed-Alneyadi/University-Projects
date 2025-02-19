import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Test Class of NNCalcController1.
 *
 * @author Saeed Alneyadi
 */
public class NNCalcController1Test {

    /*
     * Test Cases of processClearEvent().
     */

    /**
     * Testing without changing value of bottom of (model).
     */
    @Test
    public void processClearEventTest() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether (bottom)
         * is equal to 0 after testing the method.
         */
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(0);

        /*
         * Testing the method.
         */
        controller.processClearEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom).
         */
        assertEquals(bottomExpected, bottom);
    }

    /**
     * Testing with changing the value of bottom of (model) to 1.
     */
    @Test
    public void processClearEventTest1() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether (bottom)
         * is equal to 0 after testing the method.
         */
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(0);

        /*
         * Changing the value of bottom of (model).
         */
        controller.processAddNewDigitEvent(1);

        /*
         * Testing the method.
         */
        controller.processClearEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom).
         */
        assertEquals(bottomExpected, bottom);
    }

    /**
     * Testing with changing the value of bottom of (model) to 1.
     */
    @Test
    public void processClearEventTest210() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether (bottom)
         * is equal to 0 after testing the method.
         */
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(0);

        /*
         * Changing the value of bottom of (model).
         */
        controller.processAddNewDigitEvent(2);
        controller.processAddNewDigitEvent(1);
        controller.processAddNewDigitEvent(0);

        /*
         * Testing the method.
         */
        controller.processClearEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom).
         */
        assertEquals(bottomExpected, bottom);
    }

    /*
     * Test Cases of processSwapEvent().
     */

    /**
     * Testing with top = 0 and bottom = 1.
     */
    @Test
    public void processSwapEventTestT0B1() {
        /*
         * Creating (topExpected), (top), (bottom) and (bottomExpected) to be
         * checked whether (bottom) is equal to (#top) and (top) equals
         * (#bottom) after testing the method.
         */
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber topExpected = new NaturalNumber2(1);
        NaturalNumber bottomExpected = new NaturalNumber2(0);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(1);

        /*
         * Testing the method.
         */
        controller.processSwapEvent();

        /*
         * Checks whether (topExpected) equals (top) and (bottomExpected) equals
         * (bottom).
         */
        assertEquals(topExpected, top);
        assertEquals(bottomExpected, bottom);
    }

    /**
     * Testing with top = 10 and bottom = 21.
     */
    @Test
    public void processSwapEventTestT10B21() {
        /*
         * Creating (topExpected), (top), (bottom) and (bottomExpected) to be
         * checked whether (bottom) is equal to (#top) and (top) equals
         * (#bottom) after testing the method.
         */
        final int topNum = 21;
        final int bottomNum = 10;
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber topExpected = new NaturalNumber2(topNum);
        NaturalNumber bottomExpected = new NaturalNumber2(bottomNum);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(1);
        controller.processAddNewDigitEvent(0);
        controller.processEnterEvent();
        controller.processClearEvent();
        controller.processAddNewDigitEvent(2);
        controller.processAddNewDigitEvent(1);

        /*
         * Testing the method.
         */
        controller.processSwapEvent();

        /*
         * Checks whether (topExpected) equals (top) and (bottomExpected) equals
         * (bottom).
         */
        assertEquals(topExpected, top);
        assertEquals(bottomExpected, bottom);
    }

    /**
     * Testing with top = 0 and bottom = 121.
     */
    @Test
    public void processSwapEventTestT0B121() {
        /*
         * Creating (topExpected), (top), (bottom) and (bottomExpected) to be
         * checked whether (bottom) is equal to (#top) and (top) equals
         * (#bottom) after testing the method.
         */
        final int topNum = 121;
        final int bottomNum = 0;
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber topExpected = new NaturalNumber2(topNum);
        NaturalNumber bottomExpected = new NaturalNumber2(bottomNum);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(1);
        controller.processAddNewDigitEvent(2);
        controller.processAddNewDigitEvent(1);

        /*
         * Testing the method.
         */
        controller.processSwapEvent();

        /*
         * Checks whether (topExpected) equals (top) and (bottomExpected) equals
         * (bottom).
         */
        assertEquals(topExpected, top);
        assertEquals(bottomExpected, bottom);
    }

    /*
     * Test Cases of processEnterEvent().
     */

    /**
     * Testing with bottom = 1.
     */
    @Test
    public void processEnterEventTest1() {
        /*
         * Creating (top) and (bottom) to be checked whether they are equal or
         * not after testing the method.
         */
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();

        /*
         * Changing the value of bottom of (model).
         */
        controller.processAddNewDigitEvent(1);

        /*
         * Testing the method.
         */
        controller.processEnterEvent();

        /*
         * Checks whether (bottom) equals (top).
         */
        assertEquals(top, bottom);
    }

    /**
     * Testing with bottom = 99.
     */
    @Test
    public void processEnterEventTest99() {
        /*
         * Creating (top) and (bottom) to be checked whether they are equal or
         * not after testing the method.
         */
        final int nine = 9;
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();

        /*
         * Changing the value of bottom of (model).
         */
        controller.processAddNewDigitEvent(nine);
        controller.processAddNewDigitEvent(nine);

        /*
         * Testing the method.
         */
        controller.processEnterEvent();

        /*
         * Checks whether (bottom) equals (top).
         */
        assertEquals(top, bottom);
    }

    /**
     * Testing with bottom = 1210.
     */
    @Test
    public void processEnterEventTest1210() {
        /*
         * Creating (top) and (bottom) to be checked whether they are equal or
         * not after testing the method.
         */
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();

        /*
         * Changing the value of bottom of (model).
         */
        controller.processAddNewDigitEvent(1);
        controller.processAddNewDigitEvent(2);
        controller.processAddNewDigitEvent(1);
        controller.processAddNewDigitEvent(0);

        /*
         * Testing the method.
         */
        controller.processEnterEvent();

        /*
         * Checks whether (bottom) equals (top).
         */
        assertEquals(top, bottom);
    }

    /*
     * Test Cases of processAddEvent().
     */

    /**
     * Testing with top = 1 and bottom = 1.
     */
    @Test
    public void processAddEventTestT1B1() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method. Creating (top) to be checked
         * whether it is equal to zero or not.
         */
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(2);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(1);
        controller.processEnterEvent();
        controller.processClearEvent();
        controller.processAddNewDigitEvent(1);

        /*
         * Testing the method.
         */
        controller.processAddEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom). Checks whether (top)
         * equals zero.
         */
        assertEquals(new NaturalNumber2(0), top);
        assertEquals(bottomExpected, bottom);
    }

    /**
     * Testing with top = 2 and bottom = 9.
     */
    @Test
    public void processAddEventTestT2B9() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method. Creating (top) to be checked
         * whether it is equal to zero or not.
         */
        final int eleven = 11;
        final int nine = 9;
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(eleven);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(2);
        controller.processEnterEvent();
        controller.processClearEvent();
        controller.processAddNewDigitEvent(nine);

        /*
         * Testing the method.
         */
        controller.processAddEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom). Checks whether (top)
         * equals zero.
         */
        assertEquals(new NaturalNumber2(0), top);
        assertEquals(bottomExpected, bottom);
    }

    /**
     * Testing with top = 10 and bottom = 95.
     */
    @Test
    public void processAddEventTestT10B95() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method. Creating (top) to be checked
         * whether it is equal to zero or not.
         */
        final int five = 5;
        final int nine = 9;
        final int result = 105;
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(result);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(1);
        controller.processAddNewDigitEvent(0);
        controller.processEnterEvent();
        controller.processClearEvent();
        controller.processAddNewDigitEvent(nine);
        controller.processAddNewDigitEvent(five);

        /*
         * Testing the method.
         */
        controller.processAddEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom). Checks whether (top)
         * equals zero.
         */
        assertEquals(new NaturalNumber2(0), top);
        assertEquals(bottomExpected, bottom);
    }
    /*
     * Test Cases of processSubtractEvent().
     */

    /**
     * Testing with top = 1 and bottom = 1.
     */
    @Test
    public void processSubtractEventTestT1B1() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method. Creating (top) to be checked
         * whether it is equal to zero or not.
         */
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(0);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(1);
        controller.processEnterEvent();
        controller.processClearEvent();
        controller.processAddNewDigitEvent(1);

        /*
         * Testing the method.
         */
        controller.processSubtractEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom). Checks whether (top)
         * equals zero.
         */
        assertEquals(new NaturalNumber2(0), top);
        assertEquals(bottomExpected, bottom);
    }

    /**
     * Testing with top = 11 and bottom = 2.
     */
    @Test
    public void processSubtractEventTestT11B2() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method. Creating (top) to be checked
         * whether it is equal to zero or not.
         */
        final int nine = 9;
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(nine);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(1);
        controller.processAddNewDigitEvent(1);
        controller.processEnterEvent();
        controller.processClearEvent();
        controller.processAddNewDigitEvent(2);

        /*
         * Testing the method.
         */
        controller.processSubtractEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom). Checks whether (top)
         * equals zero.
         */
        assertEquals(new NaturalNumber2(0), top);
        assertEquals(bottomExpected, bottom);
    }

    /**
     * Testing with top = 101 and bottom = 2.
     */
    @Test
    public void processSubtractEventTestT101B2() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method. Creating (top) to be checked
         * whether it is equal to zero or not.
         */
        final int result = 99;
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(result);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(1);
        controller.processAddNewDigitEvent(0);
        controller.processAddNewDigitEvent(1);
        controller.processEnterEvent();
        controller.processClearEvent();
        controller.processAddNewDigitEvent(2);

        /*
         * Testing the method.
         */
        controller.processSubtractEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom). Checks whether (top)
         * equals zero.
         */
        assertEquals(new NaturalNumber2(0), top);
        assertEquals(bottomExpected, bottom);
    }

    /*
     * Test Cases of processMultiplyEvent().
     */

    /**
     * Testing with top = 0 and bottom = 1.
     */
    @Test
    public void processMultiplyEventTestT0B1() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method. Creating (top) to be checked
         * whether it is equal to zero or not.
         */
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(0);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(1);

        /*
         * Testing the method.
         */
        controller.processMultiplyEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom). Checks whether (top)
         * equals zero.
         */
        assertEquals(new NaturalNumber2(0), top);
        assertEquals(bottomExpected, bottom);
    }

    /**
     * Testing with top = 5 and bottom = 2.
     */
    @Test
    public void processMultiplyEventTestT5B2() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method. Creating (top) to be checked
         * whether it is equal to zero or not.
         */
        final int five = 5;
        final int ten = 10;
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(ten);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(five);
        controller.processEnterEvent();
        controller.processClearEvent();
        controller.processAddNewDigitEvent(2);

        /*
         * Testing the method.
         */
        controller.processMultiplyEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom). Checks whether (top)
         * equals zero.
         */
        assertEquals(new NaturalNumber2(0), top);
        assertEquals(bottomExpected, bottom);
    }

    /**
     * Testing with top = 11 and bottom = 12.
     */
    @Test
    public void processMultiplyEventTestT11B12() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method. Creating (top) to be checked
         * whether it is equal to zero or not.
         */
        final int result = 132;
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(result);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(1);
        controller.processAddNewDigitEvent(1);
        controller.processEnterEvent();
        controller.processClearEvent();
        controller.processAddNewDigitEvent(1);
        controller.processAddNewDigitEvent(2);

        /*
         * Testing the method.
         */
        controller.processMultiplyEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom). Checks whether (top)
         * equals zero.
         */
        assertEquals(new NaturalNumber2(0), top);
        assertEquals(bottomExpected, bottom);
    }

    /*
     * Test Cases of processDivideEvent().
     */

    /**
     * Testing with top = 0 and bottom = 1.
     */
    @Test
    public void processDivideEventTestT0B1() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method. Creating (top) to be checked
         * whether it is equal to zero or not.
         */
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(0);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(0);
        controller.processEnterEvent();
        controller.processClearEvent();
        controller.processAddNewDigitEvent(1);

        /*
         * Testing the method.
         */
        controller.processDivideEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom). Checks whether (top)
         * equals zero.
         */
        assertEquals(new NaturalNumber2(0), top);
        assertEquals(bottomExpected, bottom);
    }

    /**
     * Testing with top = 2 and bottom = 1.
     */
    @Test
    public void processDivideEventTestT2B1() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method. Creating (top) to be checked
         * whether it is equal to zero or not.
         */
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(2);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(2);
        controller.processEnterEvent();
        controller.processClearEvent();
        controller.processAddNewDigitEvent(1);

        /*
         * Testing the method.
         */
        controller.processDivideEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom). Checks whether (top)
         * equals zero.
         */
        assertEquals(new NaturalNumber2(0), top);
        assertEquals(bottomExpected, bottom);
    }

    /**
     * Testing with top = 145 and bottom = 12.
     */
    @Test
    public void processDivideEventTestT145B12() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method. Creating (top) to be checked
         * whether it is equal to zero or not.
         */
        final int four = 4;
        final int five = 5;
        final int result = 12;
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(result);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(1);
        controller.processAddNewDigitEvent(four);
        controller.processAddNewDigitEvent(five);
        controller.processEnterEvent();
        controller.processClearEvent();
        controller.processAddNewDigitEvent(1);
        controller.processAddNewDigitEvent(2);

        /*
         * Testing the method.
         */
        controller.processDivideEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom). Checks whether (top)
         * equals zero.
         */
        assertEquals(new NaturalNumber2(0), top);
        assertEquals(bottomExpected, bottom);
    }

    /*
     * Test Cases of processPowerEvent().
     */

    /**
     * Testing with top = 2 and bottom = 0.
     */
    @Test
    public void processPowerEventTestT2B0() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method. Creating (top) to be checked
         * whether it is equal to zero or not.
         */
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(1);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(2);
        controller.processEnterEvent();
        controller.processClearEvent();
        controller.processAddNewDigitEvent(0);

        /*
         * Testing the method.
         */
        controller.processPowerEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom). Checks whether (top)
         * equals zero.
         */
        assertEquals(new NaturalNumber2(0), top);
        assertEquals(bottomExpected, bottom);
    }

    /**
     * Testing with top = 10 and bottom = 1.
     */
    @Test
    public void processPowerEventTestT10B1() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method. Creating (top) to be checked
         * whether it is equal to zero or not.
         */
        final int ten = 10;
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(ten);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(1);
        controller.processAddNewDigitEvent(0);
        controller.processEnterEvent();
        controller.processClearEvent();
        controller.processAddNewDigitEvent(1);

        /*
         * Testing the method.
         */
        controller.processPowerEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom). Checks whether (top)
         * equals zero.
         */
        assertEquals(new NaturalNumber2(0), top);
        assertEquals(bottomExpected, bottom);
    }

    /**
     * Testing with top = 10 and bottom = 5.
     */
    @Test
    public void processPowerEventTestT50B5() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method. Creating (top) to be checked
         * whether it is equal to zero or not.
         */
        final int five = 5;
        final int result = 100000;
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(result);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(1);
        controller.processAddNewDigitEvent(0);
        controller.processEnterEvent();
        controller.processClearEvent();
        controller.processAddNewDigitEvent(five);

        /*
         * Testing the method.
         */
        controller.processPowerEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom). Checks whether (top)
         * equals zero.
         */
        assertEquals(new NaturalNumber2(0), top);
        assertEquals(bottomExpected, bottom);
    }

    /*
     * Test Cases of processRootEvent().
     */

    /**
     * Testing with top = 4 and bottom = 2.
     */
    @Test
    public void processRootEventTestT4B2() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method. Creating (top) to be checked
         * whether it is equal to zero or not.
         */
        final int four = 4;
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(2);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(four);
        controller.processEnterEvent();
        controller.processClearEvent();
        controller.processAddNewDigitEvent(2);

        /*
         * Testing the method.
         */
        controller.processRootEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom). Checks whether (top)
         * equals zero.
         */
        assertEquals(new NaturalNumber2(0), top);
        assertEquals(bottomExpected, bottom);
    }

    /**
     * Testing with top = 17 and bottom = 4.
     */
    @Test
    public void processRootEventTestT17B4() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method. Creating (top) to be checked
         * whether it is equal to zero or not.
         */
        final int four = 4;
        final int seven = 7;
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(2);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(1);
        controller.processAddNewDigitEvent(seven);
        controller.processEnterEvent();
        controller.processClearEvent();
        controller.processAddNewDigitEvent(four);

        /*
         * Testing the method.
         */
        controller.processRootEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom). Checks whether (top)
         * equals zero.
         */
        assertEquals(new NaturalNumber2(0), top);
        assertEquals(bottomExpected, bottom);
    }

    /**
     * Testing with top = 255 and bottom = 8.
     */
    @Test
    public void processRootEventTestT255B8() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method. Creating (top) to be checked
         * whether it is equal to zero or not.
         */
        final int five = 5;
        final int eight = 8;
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(1);

        /*
         * Changing the value of top and bottom of (model).
         */
        controller.processAddNewDigitEvent(2);
        controller.processAddNewDigitEvent(five);
        controller.processAddNewDigitEvent(five);
        controller.processEnterEvent();
        controller.processClearEvent();
        controller.processAddNewDigitEvent(eight);

        /*
         * Testing the method.
         */
        controller.processRootEvent();

        /*
         * Checks whether (bottomExpected) equals (bottom). Checks whether (top)
         * equals zero.
         */
        assertEquals(new NaturalNumber2(0), top);
        assertEquals(bottomExpected, bottom);
    }

    /*
     * Test Cases of processAddNewDigitEvent().
     */

    /**
     * Testing with 0 as an input to the method.
     */
    @Test
    public void processAddNewDigitEventTest0() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method.
         */
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(0);

        /*
         * Testing the method.
         */
        controller.processAddNewDigitEvent(0);

        /*
         * Checks whether (bottomExpected) equals (bottom).
         */
        assertEquals(bottomExpected, bottom);
    }

    /**
     * Testing with 91 as an input to the method.
     */
    @Test
    public void processAddNewDigitEventTest91() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method.
         */
        final int nine = 9;
        final int expected = 91;
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(expected);

        /*
         * Testing the method.
         */
        controller.processAddNewDigitEvent(nine);
        controller.processAddNewDigitEvent(1);

        /*
         * Checks whether (bottomExpected) equals (bottom).
         */
        assertEquals(bottomExpected, bottom);
    }

    /**
     * Testing with 120 as an input to the method.
     */
    @Test
    public void processAddNewDigitEventTest120() {
        /*
         * Creating (bottom) and (bottomExpected) to be checked whether they are
         * equal or not after testing the method.
         */
        final int expected = 120;
        NNCalcModel model = new NNCalcModel1();
        NNCalcController controller = new NNCalcController1(model,
                new NNCalcView1());
        NaturalNumber bottom = model.bottom();
        NaturalNumber bottomExpected = new NaturalNumber2(expected);

        /*
         * Testing the method.
         */
        controller.processAddNewDigitEvent(1);
        controller.processAddNewDigitEvent(2);
        controller.processAddNewDigitEvent(0);

        /*
         * Checks whether (bottomExpected) equals (bottom).
         */
        assertEquals(bottomExpected, bottom);
    }

}
