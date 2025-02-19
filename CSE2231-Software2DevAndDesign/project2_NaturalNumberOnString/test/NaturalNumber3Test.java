import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.naturalnumber.NaturalNumber3;

/**
 * Customized JUnit test fixture for {@code NaturalNumber3}.
 */
public class NaturalNumber3Test extends NaturalNumberTest {

    @Override
    protected final NaturalNumber constructorTest() {
        /*
         * RETURN Statement: Returning new object of NaturalNumber3() where its
         * value is 0.
         */
        return new NaturalNumber3();
    }

    @Override
    protected final NaturalNumber constructorTest(int i) {
        /*
         * RETURN Statement: Returning new object of NaturalNumber3() where its
         * value is (i).
         */
        return new NaturalNumber3(i);
    }

    @Override
    protected final NaturalNumber constructorTest(String s) {
        /*
         * RETURN Statement: Returning new object of NaturalNumber3() where its
         * value is (s) as a number.
         */
        return new NaturalNumber3(s);
    }

    @Override
    protected final NaturalNumber constructorTest(NaturalNumber n) {
        /*
         * RETURN Statement: Returning new object of NaturalNumber3() where its
         * value is (n).
         */
        return new NaturalNumber3(n);
    }

    @Override
    protected final NaturalNumber constructorRef() {
        /*
         * RETURN Statement: Returning new object of NaturalNumber2() where its
         * value is 0.
         */
        return new NaturalNumber2();
    }

    @Override
    protected final NaturalNumber constructorRef(int i) {
        /*
         * RETURN Statement: Returning new object of NaturalNumber2() where its
         * value is (i).
         */
        return new NaturalNumber2(i);
    }

    @Override
    protected final NaturalNumber constructorRef(String s) {
        /*
         * RETURN Statement: Returning new object of NaturalNumber2() where its
         * value is (s) as a number.
         */
        return new NaturalNumber2(s);
    }

    @Override
    protected final NaturalNumber constructorRef(NaturalNumber n) {
        /*
         * RETURN Statement: Returning new object of NaturalNumber2() where its
         * value is (n).
         */
        return new NaturalNumber2(n);
    }

}
