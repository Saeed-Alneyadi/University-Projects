import static org.junit.Assert.assertEquals;

import org.junit.Test;


import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Saeed Alneyadi.11
 * @author Isaac Shores.22
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }
    
    /*
     * Test cases for constructors -----------------------------------------------------------
     */
    
    /**
     * Tests constructors with default configuration.
     */
    @Test
    public final void testDefaultConstructor() {
        /*
         * Variable declaration/initialization.
         */
        Set<String> set = this.constructorTest();
        Set<String> setExpected = this.constructorRef();
        /*
         * Check values of variables versus expected.
         */
        assertEquals(setExpected, set);
    }
    
    /*
     * Test cases for kernel methods - add() -----------------------------------------------------------
     */
    
    /**
     * Tests implementation of add() method with {@code set} initial size equal to 1.
     */
    @Test
    public final void add1() {
        /*
         * Variable declaration/initialization.
         */
        Set<String> set = this.createFromArgsTest("a");
        Set<String> setExpected = this.createFromArgsRef("a", "b");
  
        /*
         * Call method being tested.
         */
        set.add("b");
        /*
         * Check values of variables versus expected.
         */
        assertEquals(setExpected, set);
        
    }
    
    /**
     * Tests implementation of add() method with {@code set} size equal to 0.
     */
    @Test
    public final void add2() {
        /*
         * Variable declaration/initialization.
         */
        Set<String> set = this.createFromArgsTest();
        Set<String> setExpected = this.createFromArgsRef("a");
  
        /*
         * Call method being tested.
         */
        set.add("a");
        /*
         * Check values of variables versus expected.
         */
        assertEquals(setExpected, set);
        
    }
    
    /*
     * Test cases for kernel methods - remove() -----------------------------------------------------------
     */
    
    /**
     * Tests implementation of remove() method with {@code set} size equal to 1.
     */
    @Test
    public final void remove1() {
        /*
         * Variable declaration/initialization.
         */
        Set<String> set = this.createFromArgsTest("a");
        Set<String> setExpected = this.createFromArgsRef();
  
        /*
         * Call method being tested.
         */
        set.remove("a");
        /*
         * Check values of variables versus expected.
         */
        assertEquals(setExpected, set);
        
    }
    
    /**
     * Tests implementation of remove() method with {@code set} size equal to 3.
     */
    @Test
    public final void remove2() {
        /*
         * Variable declaration/initialization.
         */
        Set<String> set = this.createFromArgsTest("a", "b", "c");
        Set<String> setExpected = this.createFromArgsRef("a", "c");
        
  
        /*
         * Call method being tested.
         */
        set.remove("b");
        /*
         * Check values of variables versus expected.
         */
        assertEquals(setExpected, set);
        
    }
    
    /*
     * Test cases for kernel methods - removeAny() --------------------------------------------------------------
     */
    
    /**
     * Tests implementation of removeAny() method with {@code set} size equal to
     * 1.
     */
    @Test
    public final void testRemoveAny1() {
        /*
         * Variable declaration/initialization.
         */
        Set<String> set = this.createFromArgsTest("a");
        Set<String> setExpected = this.createFromArgsRef();
        /*
         * Call method being tested.
         */
        set.removeAny();
        /*
         * Check values of variables versus expected.
         */
        assertEquals(setExpected, set);
    }

    /**
     * Tests implementation of removeAny() method {@code set} size equal to 3.
     */
    @Test
    public final void testRemoveAny2() {
        /*
         * Variable declaration/initialization.
         */
        Set<String> set = this.createFromArgsTest("Ohio", "USA", "Michigan");
        Set<String> setExpected = this.createFromArgsRef("Ohio", "USA");
        /*
         * Call method being tested.
         */
        set.removeAny();
        /*
         * Check values of variables versus expected.
         */
        assertEquals(setExpected, set);
    }
    
    /*
     * Test cases for kernel methods - contains() ------------------------------------------------------------------
     */
    
    /**
     * Tests implementation of contains() method with {@code set} size equal to
     * 1.
     */
    @Test
    public final void testContains1() {
        /*
         * Variable declaration/initialization.
         */
        Set<String> set = this.createFromArgsTest("a");
        Set<String> setExpected = this.createFromArgsRef("a");
        Boolean found;
        /*
         * Call method being tested.
         */
        found = set.contains("a");
        /*
         * Check values of variables versus expected.
         */
        assertEquals(setExpected, set);
        assertEquals(true, found);
    }

    /**
     * Tests implementation of contains() method with {@code set} size equal to
     * 3.
     */
    @Test
    public final void testContains2() {
        /*
         * Variable declaration/initialization.
         */
        Set<String> set = this.createFromArgsTest("Ohio", "USA", "Michigan");
        Set<String> setExpected = this.createFromArgsRef("Ohio", "USA",
                "Michigan");
        Boolean found;
        /*
         * Call method being tested.
         */
        found = set.contains("UK");
        /*
         * Check values of variables versus expected.
         */
        assertEquals(setExpected, set);
        assertEquals(false, found);
    }

    /**
     * Tests implementation of contains() method with {@code set} size equal to
     * 4.
     */
    @Test
    public final void testContains3() {
        /*
         * Variable declaration/initialization.
         */
        Set<String> set = this.createFromArgsTest("1", "2", "3", "4");
        Set<String> setExpected = this.createFromArgsRef("1", "2", "3", "4");
        Boolean found;
        /*
         * Call method being tested.
         */
        found = set.contains("5");
        /*
         * Check values of variables versus expected.
         */
        assertEquals(setExpected, set);
        assertEquals(false, found);
    }

    /*
     * Test cases for kernel methods - size() -----------------------------------------------------------
     */
    
    /**
     * Tests implementation of size() method with {@code set} size equal to 1.
     */
    @Test
    public final void testSize1() {
        /*
         * Variable declaration/initialization.
         */
        Set<String> set = this.createFromArgsTest("a");
        Set<String> setExpected = this.createFromArgsRef("a");
        int size;
        /*
         * Call method being tested.
         */
        size = set.size();
        /*
         * Check values of variables versus expected.
         */
        assertEquals(setExpected, set);
        assertEquals(1, size);
    }

    /**
     * Tests implementation of size() method with {@code set} size equal to 2.
     */
    @Test
    public final void testSize2() {
        /*
         * Variable declaration/initialization.
         */
        Set<String> set = this.createFromArgsTest("Ohio", "USA");
        Set<String> setExpected = this.createFromArgsRef("Ohio", "USA");
        int size;
        /*
         * Call method being tested.
         */
        size = set.size();
        /*
         * Check values of variables versus expected.
         */
        assertEquals(setExpected, set);
        assertEquals(2, size);
    }

    /**
     * Tests implementation of size() method with {@code set} size equal to 3.
     */
    @Test
    public final void testSize3() {
        /*
         * Variable declaration/initialization.
         */
        Set<String> set = this.createFromArgsTest("1", "2", "3");
        Set<String> setExpected = this.createFromArgsRef("1", "2", "3");
        int size;
        final int sizeExpected = 3;
        /*
         * Call method being tested.
         */
        size = set.size();
        /*
         * Check values of variables versus expected.
         */
        assertEquals(setExpected, set);
        assertEquals(sizeExpected, size);
    }

}
