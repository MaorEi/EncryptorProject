package validators;

import org.junit.Test;
import utilities.Tuple;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Maor on 6/5/2017.
 */
public class DoubleKeyValidatorTest {
    DoubleKeyValidator<Integer, Integer> doubleKeyValidator = new DoubleKeyValidator<>(new SingleKeyValidator(),new OddKeyValidator());

    @Test
    public void testIsValidKey_validKey() throws Exception {
        Integer validFirstKey = 1;
        Integer validSecondKey = 1;
        Tuple<Integer, Integer> validDoubleKey = new Tuple<>(validFirstKey,validSecondKey);
        assertThat(doubleKeyValidator.isValidKey(validDoubleKey),is(true));
    }

    @Test
    public void testIsValidKey_invalidKey() throws Exception {
        Integer validFirstKey = 1;
        Integer invalidSecondKey = 2;
        Tuple<Integer, Integer> invalidDoubleKey = new Tuple<>(validFirstKey,invalidSecondKey);
        assertThat(doubleKeyValidator.isValidKey(invalidDoubleKey),is(false));
    }

}