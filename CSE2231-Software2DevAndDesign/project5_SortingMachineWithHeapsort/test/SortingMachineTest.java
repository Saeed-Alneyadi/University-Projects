import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Isaac Shores.22
 * @author Saeed Alneyadi.11
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Test case for constructors.
     */

    @Test
    public final void testConstructor() {
    	/*
         * Variable declaration/initialization.
         */
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        
        /*
         * Check values of variables versus expected.
         */
        assertEquals(mExpected, m);
    }
    
    /*
     * Test cases for kernel methods. ------------------------------------------------------
     */

    /**
     * Tests implementation of add() method with no elements.
     */
    @Test
    public final void testAddEmpty() {
    	/*
         * Variable declaration/initialization.
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "1");
        
        /*
         * Call method being tested.
         */
        m.add("1");
        
        /*
         * Check values of variables versus expected.
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests implementation of size() method with no elements.
     */
    @Test
    public final void testSizeEmpty() {
    	/*
         * Variable declaration/initialization.
         */
    	SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        int sizeExpected = 0;
        
    	/*
         * Call method being tested.
         */
        int mSize = m.size();
        
        /*
         * Check values of variables versus expected.
         */
        assertEquals(sizeExpected, mSize);
    }
    
    /**
     * Tests implementation of size() method with one element inside.
     */
    @Test
    public final void testSizeNonEmpty1() {
    	/*
         * Variable declaration/initialization.
         */
    	SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "1");
        int sizeExpected = 1;
        
    	/*
         * Call method being tested.
         */
        int mSize = m.size();
        
        /*
         * Check values of variables versus expected.
         */
        assertEquals(sizeExpected, mSize);
    }
    
    /**
     * Tests implementation of size() method with two elements inside.
     */
    @Test
    public final void testSizeNonEmpty2() {
    	/*
         * Variable declaration/initialization.
         */
    	SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "1", "2");
        int sizeExpected = 2;
        
    	/*
         * Call method being tested.
         */
        int mSize = m.size();
        
        /*
         * Check values of variables versus expected.
         */
        assertEquals(sizeExpected, mSize);
    }
    /**
     * Tests implementation of add() method with zero element inside.
     */
    @Test
    public final void testAdd1() {
        /*
         * Variable declaration/initialization.
         */
        SortingMachine<String> sm = this.constructorTest(ORDER);
        SortingMachine<String> smExpected = this.constructorRef(ORDER);
        /*
         * Call method being tested.
         */
        sm.add("A");
        smExpected.add("A");
        /*
         * Check values of variables versus expected.
         */
        assertEquals(smExpected, sm);
    }

    /**
     * Tests implementation of add() method with one element inside.
     */
    @Test
    public final void testAdd2() {
        /*
         * Variable declaration/initialization.
         */
        SortingMachine<String> sm = this.createFromArgsTest(ORDER, true, "1");
        SortingMachine<String> smExpected = this.createFromArgsTest(ORDER, true,
                "1", "2");
        /*
         * Call method being tested.
         */
        sm.add("2");
        /*
         * Check values of variables versus expected.
         */
        assertEquals(smExpected, sm);
    }

    /**
     * Tests implementation of add() method with two element inside.
     */
    @Test
    public final void testAdd3() {
        /*
         * Variable declaration/initialization.
         */
        SortingMachine<String> sm = this.createFromArgsTest(ORDER, true, "i",
                "ii");
        SortingMachine<String> smExpected = this.createFromArgsRef(ORDER, true,
                "i", "ii", "iii");
        /*
         * Call method being tested.
         */
        sm.add("iii");
        /*
         * Check values of variables versus expected.
         */
        assertEquals(smExpected, sm);
    }

    /**
     * Tests implementation of changeToExtractionMode() method.
     */
    @Test
    public final void testChangeToExtractionModeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        
        /*
         * Call method being tested.
         */
        m.changeToExtractionMode();
        
        /*
         * Check values of variables versus expected.
         */
        assertEquals(mExpected, m);
    }
    
    /**
     * Tests implementation of changeToExtractionMode() method with one element inside.
     */
    @Test
    public final void testChangeToExtractionModeNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "1");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false, "1");
        
        /*
         * Call method being tested.
         */
        m.changeToExtractionMode();
        
        /*
         * Check values of variables versus expected.
         */
        assertEquals(mExpected, m);
    }
    
    /**
     * Tests implementation of removeFirst() method with one element inside.
     */
    @Test
    public final void testRemoveFirst1() {
        /*
         * Variable declaration/initialization.
         */
        SortingMachine<String> sm = this.createFromArgsTest(ORDER, false, "A");
        SortingMachine<String> smExpected = this.createFromArgsRef(ORDER,
                false);
        /*
         * Call method being tested.
         */
        String removed = sm.removeFirst();
        /*
         * Check values of variables versus expected.
         */
        assertEquals(smExpected, sm);
        assertEquals("A", removed);
    }

    /**
     * Tests implementation of removeFirst() method with two element inside.
     */
    @Test
    public final void testRemoveFirst2() {
        /*
         * Variable declaration/initialization.
         */
        SortingMachine<String> sm = this.createFromArgsTest(ORDER, false, "1",
                "2");
        SortingMachine<String> smExpected = this.createFromArgsRef(ORDER, false,
                "2");
        
        /*
         * Call method being tested.
         */
        String removed = sm.removeFirst();
        /*
         * Check values of variables versus expected.
         */
        assertEquals(smExpected, sm);
        assertEquals("1", removed);
    }
    
    /**
     * Tests implementation of IsInInsertionMode() method.
     */
    @Test
    public final void testIsInInsertionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        
        /*
         * Call method being tested.
         */
        boolean isInInsertion = m.isInInsertionMode();
        
        /*
         * Check values of variables versus expected.
         */
        assertEquals(true, isInInsertion);
        assertEquals(mExpected, m);
    }

}
