import java.util.Iterator;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.set.Set;
import components.set.SetSecondary;

/**
 * {@code Set} represented as a {@code BinaryTree} (maintained as a binary
 * search tree) of elements with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Set} elements
 * @mathdefinitions <pre>
 * IS_BST(
 *   tree: binary tree of T
 *  ): boolean satisfies
 *  [tree satisfies the binary search tree properties as described in the
 *   slides with the ordering reported by compareTo for T, including that
 *   it has no duplicate labels]
 * </pre>
 * @convention IS_BST($this.tree)
 * @correspondence this = labels($this.tree)
 *
 * @author Put your name here
 *
 */
public class Set3a<T extends Comparable<T>> extends SetSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Elements included in {@code this}.
     */
    private BinaryTree<T> tree;

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    private static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        /*
         * Declaration/Initialization of variables.
         */
        boolean inTree = false;
        BinaryTree<T> leftTree = t.newInstance();
        BinaryTree<T> rightTree = t.newInstance();
        
        /*
         * Recursive loop to return false if tree is empty before finding 
         * equivalent entry to {@code x}.
         */
        if (t.size() == 0) {
            inTree = false;
        } else {
            T root = t.disassemble(leftTree, rightTree);
            int compareToRoot = root.compareTo(x);
            if (compareToRoot == 0) {
                inTree = true;
            } else if (compareToRoot < 0) {
                inTree = isInTree(leftTree, x);
            } else {
                inTree = isInTree(rightTree, x);
            }
            t.assemble(root, leftTree, rightTree);
        }
        return inTree;
        
        
    }

    /**
     * Inserts {@code x} in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be inserted
     * @aliases reference {@code x}
     * @updates t
     * @requires IS_BST(t) and x is not in labels(t)
     * @ensures IS_BST(t) and labels(t) = labels(#t) union {x}
     */
    private static <T extends Comparable<T>> void insertInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        /*
         * Declaration/Initialization of variables.
         */
        BinaryTree<T> leftTree = t.newInstance();
        BinaryTree<T> rightTree = t.newInstance();
        
        /*
         * Recursive loop to replace root if tree is empty before finding 
         * equivalent entry to {@code x}.
         */
        if (t.size() == 0) {
            t.assemble(x, leftTree, rightTree);
        } else {
            T root = t.disassemble(leftTree, rightTree);
            int compareToRoot = root.compareTo(x);
            if (compareToRoot < 0) {
                insertInTree(leftTree, x);
            } else {
                insertInTree(rightTree, x);
            }
            t.assemble(root, leftTree, rightTree);
        }

    }

    /**
     * Removes and returns the smallest (left-most) label in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove the label
     * @return the smallest label in the given {@code BinaryTree}
     * @updates t
     * @requires IS_BST(t) and |t| > 0
     * @ensures <pre>
     * IS_BST(t)  and  removeSmallest = [the smallest label in #t]  and
     *  labels(t) = labels(#t) \ {removeSmallest}
     * </pre>
     */
    private static <T> T removeSmallest(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";
        assert t.size() > 0 : "Violation of: |t| > 0";
        /*
         * Variables Declarations/Initialization
         */
        BinaryTree<T> leftTree = t.newInstance();
        BinaryTree<T> rightTree = t.newInstance();
        T smallest;
        /*
         * Disassemble {@code t} and storing its root, left, and right tree to
         * {@code root}, {@code leftTree}, and {@code rightTree}.
         */
        T root = t.disassemble(leftTree, rightTree);
        /*
         * Removes the smallest root of the tree.
         */
        if (leftTree.size() == 0) {
            smallest = root;
            if (rightTree.size() > 0) {
                t.transferFrom(rightTree);
            } else {
                t.clear();
            }
        } else {
            smallest = removeSmallest(leftTree);
            /*
             * Assemble {@code t} with {@code root}, {@code leftTree}, and
             * {@code rightTree}.
             */
            t.assemble(root, leftTree, rightTree);
        }
        /*
         * RETURN Statement: Returning {@code smallest}.
         */
        return smallest;
    }

    /**
     * Finds label {@code x} in {@code t}, removes it from {@code t}, and
     * returns it.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove label {@code x}
     * @param x
     *            the label to be removed
     * @return the removed label
     * @updates t
     * @requires IS_BST(t) and x is in labels(t)
     * @ensures <pre>
     * IS_BST(t)  and  removeFromTree = x  and
     *  labels(t) = labels(#t) \ {x}
     * </pre>
     */
    private static <T extends Comparable<T>> T removeFromTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        assert t.size() > 0 : "Violation of: x is in labels(t)";
        /*
         * Variable Declaration/Initialization
         */
        T removedEntry = null;
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();
        /*
         * Remove {@code x} from {@code t} and store the removed element to
         * {@code removedEntry}.
         */
        if (t.root().equals(x)) {
            removedEntry = t.disassemble(left, right);
            T rightSmallestEntry;

            if (right.size() > 0) {
                rightSmallestEntry = removeSmallest(right);
                t.assemble(rightSmallestEntry, left, right);
            } else if (left.size() > 0) {
                t.transferFrom(left);
            }

        } else {
            T root = t.disassemble(left, right);

            if (isInTree(left, x)) {
                removedEntry = removeFromTree(left, x);
            } else if (isInTree(right, x)) {
                removedEntry = removeFromTree(right, x);
            }

            t.assemble(root, left, right);
        }
        /*
         * RETURN Statement: Returning {@code removedEntry} which the is the
         * removed entry.
         */
        return removedEntry;
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {


    	this.tree = new BinaryTree1<T>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Set3a() {

        this.createNewRep();

    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final Set<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Set<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Set3a<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3a<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Set3a<T> localSource = (Set3a<T>) source;
        this.tree = localSource.tree;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void add(T x) {
        assert x != null : "Violation of: x is not null";
        assert !this.contains(x) : "Violation of: x is not in this";

        /*
         * Calls insertInTree().
         */
        insertInTree(this.tree, x);

    }

    @Override
    public final T remove(T x) {
        assert x != null : "Violation of: x is not null";
        assert this.contains(x) : "Violation of: x is in this";

        /*
         * RETURN Statement: Calls removeFromTree().
         */
        return removeFromTree(this.tree, x);
    }

    @Override
    public final T removeAny() {
        assert this.size() > 0 : "Violation of: this /= empty_set";
        /*
         * Variable Initialization
         */
        T removedEntry;
        /*
         * This will remove the right root if there is a right tree. If there
         * isn't, the root node of {@code this.tree} will be removed and
         * assigned to {@code removedEntry}.
         */
        if (this.tree.size() > 1) {
            BinaryTree<T> left = this.tree.newInstance();
            BinaryTree<T> right = this.tree.newInstance();

            T root = this.tree.disassemble(left, right);

            removedEntry = removeFromTree(right, right.root());

            this.tree.assemble(root, left, right);
        } else {
            removedEntry = removeFromTree(this.tree, this.tree.root());
        }
        /*
         * RETURN Statement: Returning {@code removedEntry} which is the removed
         * entry from {@code this.tree}.
         */
        return removedEntry;
    }

    @Override
    public final boolean contains(T x) {
        assert x != null : "Violation of: x is not null";
        /*
         * RETURN Statement: Returning whether {@code x} is in the {@code
         * this.tree}. If it is, it will return true. Otherwise, false.
         */
        return isInTree(this.tree, x);
    }

    @Override
    public final int size() {
        /*
         * RETURN Statement: Returning size() of {code@ this.tree}.
         */
        return this.tree.size();
    }

    @Override
    public final Iterator<T> iterator() {
        return this.tree.iterator();
    }

}
