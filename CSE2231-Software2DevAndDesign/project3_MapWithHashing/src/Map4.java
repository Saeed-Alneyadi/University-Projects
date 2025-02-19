import java.util.Iterator;
import java.util.NoSuchElementException;

import components.map.Map;
import components.map.Map2;
import components.map.MapSecondary;

/**
 * {@code Map} represented as a hash table using {@code Map}s for the buckets,
 * with implementations of primary methods.
 *
 * @param <K>
 *            type of {@code Map} domain (key) entries
 * @param <V>
 *            type of {@code Map} range (associated value) entries
 * @convention <pre>
 * |$this.hashTable| > 0  and
 * for all i: integer, pf: PARTIAL_FUNCTION, x: K
 *     where (0 <= i  and  i < |$this.hashTable|  and
 *            <pf> = $this.hashTable[i, i+1)  and
 *            x is in DOMAIN(pf))
 *   ([computed result of x.hashCode()] mod |$this.hashTable| = i))  and
 * for all i: integer
 *     where (0 <= i  and  i < |$this.hashTable|)
 *   ([entry at position i in $this.hashTable is not null])  and
 * $this.size = sum i: integer, pf: PARTIAL_FUNCTION
 *     where (0 <= i  and  i < |$this.hashTable|  and
 *            <pf> = $this.hashTable[i, i+1))
 *   (|pf|)
 * </pre>
 * @correspondence <pre>
 * this = union i: integer, pf: PARTIAL_FUNCTION
 *            where (0 <= i  and  i < |$this.hashTable|  and
 *                   <pf> = $this.hashTable[i, i+1))
 *          (pf)
 * </pre>
 *
 * @author Put your name here
 *
 */
public class Map4<K, V> extends MapSecondary<K, V> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Default size of hash table.
     */
    private static final int DEFAULT_HASH_TABLE_SIZE = 101;

    /**
     * Buckets for hashing.
     */
    private Map<K, V>[] hashTable;

    /**
     * Total size of abstract {@code this}.
     */
    private int size;

    /**
     * Computes {@code a} mod {@code b} as % should have been defined to work.
     *
     * @param a
     *            the number being reduced
     * @param b
     *            the modulus
     * @return the result of a mod b, which satisfies 0 <= {@code mod} < b
     * @requires b > 0
     * @ensures <pre>
     * 0 <= mod  and  mod < b  and
     * there exists k: integer (a = k * b + mod)
     * </pre>
     */
    private static int mod(int a, int b) {
        assert b > 0 : "Violation of: b > 0";
        /*
         * Variables Initialization
         */
        int result = a % b;
        /*
         * Checks if (result) is less than or equal to 0. If it is, the
         * addition* of (b) and (result) will be stored to (result).
         */
        if (result < 0) {
            result += b;
        }
        /*
         * RETURN Statement: Returning (result).
         */
        return result;
    }

    /**
     * Creator of initial representation.
     *
     * @param hashTableSize
     *            the size of the hash table
     * @requires hashTableSize > 0
     * @ensures <pre>
     * |$this.hashTable| = hashTableSize  and
     * for all i: integer
     *     where (0 <= i  and  i < |$this.hashTable|)
     *   ($this.hashTable[i, i+1) = <{}>)  and
     * $this.size = 0
     * </pre>
     */
    @SuppressWarnings("unchecked")
    private void createNewRep(int hashTableSize) {
        /*
         * With "new Map<K, V>[...]" in place of "new Map[...]" it does not
         * compile; as shown, it results in a warning about an unchecked
         * conversion, though it cannot fail.
         */
        this.hashTable = new Map[hashTableSize];
        /*
         * Creates new Map2 object for each bucket (this.hashTable[idx]) and
         * assign their references to these buckets.
         */
        for (int idx = 0; idx < this.hashTable.length; idx++) {
            this.hashTable[idx] = new Map2<K, V>();
        }
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Map4() {
        /*
         * Calling createNewRep() with input (ten) to create a map array with 10
         * element or buckets.
         */
        final int ten = 10;
        this.createNewRep(ten);
    }

    /**
     * Constructor resulting in a hash table of size {@code hashTableSize}.
     *
     * @param hashTableSize
     *            size of hash table
     * @requires hashTableSize > 0
     * @ensures this = {}
     */
    public Map4(int hashTableSize) {
        /*
         * Calling createNewRep() with input (hashTableSize) to create a map
         * array with number of element or buckets equals (hashTableSize).
         */
        this.createNewRep(hashTableSize);
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final Map<K, V> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep(DEFAULT_HASH_TABLE_SIZE);
    }

    @Override
    public final void transferFrom(Map<K, V> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Map4<?, ?> : ""
                + "Violation of: source is of dynamic type Map4<?,?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Map4<?,?>, and
         * the ?,? must be K,V or the call would not have compiled.
         */
        Map4<K, V> localSource = (Map4<K, V>) source;
        this.hashTable = localSource.hashTable;
        this.size = localSource.size;
        localSource.createNewRep(DEFAULT_HASH_TABLE_SIZE);
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void add(K key, V value) {
        assert key != null : "Violation of: key is not null";
        assert value != null : "Violation of: value is not null";
        assert !this.hasKey(key) : "Violation of: key is not in DOMAIN(this)";
        /*
         * Variables Initialization
         */
        String hashStr = key.toString();
        int sum = 0;
        /*
         * Calculate the sum of the int representation of (hashStr) characters
         * and stores it in (sum).
         */
        for (int idx = 0; idx < hashStr.length(); idx++) {
            sum += hashStr.charAt(0);
        }
        /*
         * Calculate the bucket number for the added pairs and stores it to
         * (bucketNum).
         */
        int bucketNum = mod(sum, this.hashTable.length);
        /*
         * Adding a pair with (key) key and (value) value to (bucketNum) bucket
         * in (this.hashTable).
         */
        this.hashTable[bucketNum].add(key, value);
    }

    @Override
    public final Pair<K, V> remove(K key) {
        assert key != null : "Violation of: key is not null";
        assert this.hasKey(key) : "Violation of: key is in DOMAIN(this)";
        /*
         * Variables Initialization
         */
        String hashStr = key.toString();
        int sum = 0;
        /*
         * Calculate the sum of the int representation of (hashStr) characters
         * and stores it in (sum).
         */
        for (int idx = 0; idx < hashStr.length(); idx++) {
            sum += hashStr.charAt(0);
        }
        /*
         * Calculate the bucket number for the added pairs and stores it to
         * (bucketNum).
         */
        int bucketNum = mod(sum, this.hashTable.length);
        /*
         * Return and removes the pair with (key) key from (bucketNum) bucket in
         * (this.hashTable).
         */
        return this.hashTable[bucketNum].remove(key);
    }

    @Override
    public final Pair<K, V> removeAny() {
        assert this.size() > 0 : "Violation of: this /= empty_set";
        /*
         * Returns and removes any pair in the middle bucket of the
         * (this.hashTable) array.
         */
        return this.hashTable[this.hashTable.length / 2].removeAny();
    }

    @Override
    public final V value(K key) {
        assert key != null : "Violation of: key is not null";
        assert this.hasKey(key) : "Violation of: key is in DOMAIN(this)";
        /*
         * Variables Initialization
         */
        String hashStr = key.toString();
        int sum = 0;
        /*
         * Calculate the sum of the int representation of (hashStr) characters
         * and stores it in (sum).
         */
        for (int idx = 0; idx < hashStr.length(); idx++) {
            sum += hashStr.charAt(0);
        }
        /*
         * Calculate the bucket number for the added pairs and stores it to
         * (bucketNum).
         */
        int bucketNum = mod(sum, this.hashTable.length);
        /*
         * Get the value of the pair with (key) key from the (bucketNum) bucket
         * of (this.hashTable).
         */
        return this.hashTable[bucketNum].value(key);
    }

    @Override
    public final boolean hasKey(K key) {
        assert key != null : "Violation of: key is not null";
        /*
         * Variables Initialization
         */
        String hashStr = key.toString();
        boolean found = false;
        int sum = 0;
        /*
         * Calculate the sum of the int representation of (hashStr) characters
         * and stores it in (sum).
         */
        for (int idx = 0; idx < hashStr.length(); idx++) {
            sum += hashStr.charAt(0);
        }
        /*
         * Calculate the bucket number for the added pairs and stores it to
         * (bucketNum).
         */
        int bucketNum = mod(sum, this.hashTable.length);
        /*
         * Checks whether (bucketNum) bucket has a pair with (key) key. If it
         * is, found will be set to true. Otherwise, set to false.
         */
        found = this.hashTable[bucketNum].hasKey(key);
        /*
         * RETURN Statement: Returns (found).
         */
        return found;
    }

    @Override
    public final int size() {
    	//int mapSize = this.size;
        /*
         * Variables Initialization
         */
        int mapSize = 0;
        /*
         * Calculate the size of the whole map by getting the size for each map
         * in each element, adding all them up, and then assign the result to
         * (mapSize).
         */
        for (int idx = 0; idx < this.hashTable.length; idx++) {
            mapSize += this.hashTable[idx].size();
        }
        /*
         * RETURN Statement: Return (mapSize).
         */
        return mapSize;
    }

    @Override
    public final Iterator<Pair<K, V>> iterator() {
        return new Map4Iterator();
    }

    /**
     * Implementation of {@code Iterator} interface for {@code Map4}.
     */
    private final class Map4Iterator implements Iterator<Pair<K, V>> {

        /**
         * Number of elements seen already (i.e., |~this.seen|).
         */
        private int numberSeen;

        /**
         * Bucket from which current bucket iterator comes.
         */
        private int currentBucket;

        /**
         * Bucket iterator from which next element will come.
         */
        private Iterator<Pair<K, V>> bucketIterator;

        /**
         * No-argument constructor.
         */
        Map4Iterator() {
            this.numberSeen = 0;
            this.currentBucket = 0;
            this.bucketIterator = Map4.this.hashTable[0].iterator();
        }

        @Override
        public boolean hasNext() {
            return this.numberSeen < Map4.this.size;
        }

        @Override
        public Pair<K, V> next() {
            assert this.hasNext() : "Violation of: ~this.unseen /= <>";
            if (!this.hasNext()) {
                /*
                 * Exception is supposed to be thrown in this case, but with
                 * assertion-checking enabled it cannot happen because of assert
                 * above.
                 */
                throw new NoSuchElementException();
            }
            this.numberSeen++;
            while (!this.bucketIterator.hasNext()) {
                this.currentBucket++;
                this.bucketIterator = Map4.this.hashTable[this.currentBucket]
                        .iterator();
            }
            return this.bucketIterator.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                    "remove operation not supported");
        }

    }

}
