import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Isaac Shores.22
 * @author Saeed Alneyadi.11
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size
    
    /**
     * Tests constructors with default configuration.
     */
    @Test
    public final void testDefaultConstructor() {
        Map<String, String> n = this.constructorTest();
        Map<String, String> nExpected = this.constructorRef();
        assertEquals(n, nExpected);
    }
    
    /**
     * Tests constructors with String args.
     */
    @Test
    public final void testConstructorWithArgs() {
        /*
         * Variable declaration/initialization.
         */
        Map<String, String> n = this.createFromArgsTest("a", "b");
        Map<String, String> nExpected = this.createFromArgsRef("a", "b");

        /*
         * Check values of variables versus expected.
         */
        assertEquals(n, nExpected);
    }
    
    /*
     * Test cases for kernel methods - add.
     */
    
    /**
     * Tests implementation of add.
     */
    @Test
    public final void testAdd() {
        /*
         * Variable declaration/initialization.
         */
        Map<String, String> n = this.createFromArgsTest("a", "b");
        Map<String, String> nExpected = this.createFromArgsRef("a", "b", "c", "d");

        /*
         * Call method being tested.
         */
        n.add("c", "d");

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
    }
    
    /**
     * Tests implementation of add to an empty map.
     */
    @Test
    public final void testAddToEmpty() {
        /*
         * Variable declaration/initialization.
         */
        Map<String, String> n = this.createFromArgsTest();
        Map<String, String> nExpected = this.createFromArgsRef("a", "b");

        /*
         * Call method being tested.
         */
        n.add("a", "b");

        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
    }
    
    /*
     * Test cases for kernel methods - remove.
     */
    
    /**
     * Tests implementation of remove.
     */
    @Test
    public final void testRemove() {
    	/*
    	 * Variable declaration/initialization.
    	 */
    	Map<String, String> n = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> nExpected = this.createFromArgsRef("a", "b");
        Map.Pair<String, String> r;
        
        /*
         * Call method being tested.
         */
        r = n.remove("c");
        
        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
        assertEquals("c", r.key());
        assertEquals("d", r.value());
    }
    
    /*
     * Test cases for kernel methods - removeAny.
     */
    
    /**
     * Tests implementation of removeAny.
     */
    @Test
    public final void testRemoveAny() {
    	/*
    	 * Variable declaration/initialization.
    	 */
    	Map<String, String> n = this.createFromArgsTest("a", "b", "c", "d", "e", "f");
        Map<String, String> nExpected = this.createFromArgsRef("a", "b", "c", "d", "e", "f");
        Map.Pair<String, String> r;
        
        /*
         * Call method being tested.
         */
        r = n.removeAny();
        
        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected.hasKey(r.key()), true);
        
        nExpected.remove(r.key());
        
        assertEquals(nExpected, n);
    }
    
    /*
     * Test cases for kernel methods - value.
     */
    
    /**
     * Tests implementation of value.
     */
    @Test
    public final void testValue() {
    	/*
    	 * Variable declaration/initialization.
    	 */
    	Map<String, String> n = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> nExpected = this.createFromArgsRef("a", "b");
        Map.Pair<String, String> r;
        
        /*
         * Call method being tested.
         */
        r = n.remove("c");
        
        /*
         * Check values of variables versus expected.
         */
        assertEquals(nExpected, n);
        assertEquals("d", r.value());
    }
    
    /*
     * Test cases for kernel methods - hasKey.
     */
    
    /**
     * Tests implementation of hasKey.
     */
    @Test
    public final void testHasKey() {
    	/*
    	 * Variable declaration/initialization.
    	 */
    	Map<String, String> n = this.createFromArgsTest("a", "b");
        
        /*
         * Check values of variables versus expected.
         */
        assertEquals(true, n.hasKey("a"));
    }
    
    /**
     * Tests implementation of hasKey with different key.
     */
    @Test
    public final void testHasKeyFalse() {
    	/*
    	 * Variable declaration/initialization.
    	 */
    	Map<String, String> n = this.createFromArgsTest("a", "b");
        
        /*
         * Check values of variables versus expected.
         */
        assertEquals(false, n.hasKey("c"));
    }
    
    /*
     * Test cases for kernel methods - size.
     */
    
    /**
     * Tests implementation of size.
     */
    @Test
    public final void testSize() {
    	/*
    	 * Variable declaration/initialization.
    	 */
    	Map<String, String> n = this.createFromArgsTest("a", "b", "c", "d");
        
        /*
         * Check values of variables versus expected.
         */
        assertEquals(2, n.size());
    }

    /**
     * Tests implementation of size with no pairs.
     */
    @Test
    public final void testSizeEmpty() {
    	/*
    	 * Variable declaration/initialization.
    	 */
    	Map<String, String> n = this.createFromArgsTest();
        
        /*
         * Check values of variables versus expected.
         */
        assertEquals(0, n.size());
    }
}
