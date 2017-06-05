package validators;

import org.junit.Test;
import utilities.Tuple;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Maor on 6/5/2017.
 */
public class RepeatKeyValidatorTest {
    RepeatKeyValidator<Integer> repeatKeyValidator = new RepeatKeyValidator<>(new SingleKeyValidator());

    @Test
    public void testIsValidKey_validRepeatKey() throws Exception {
        Integer validKey = 1;
        Integer validRepeatsNumber = 2;
        Tuple<Integer, Integer> validRepeatKey = new Tuple<>(validKey, validRepeatsNumber);
        assertThat(repeatKeyValidator.isValidKey(validRepeatKey), is(true));
    }

    @Test
    public void testIsValidKey_invalidKey() throws Exception {
        Integer invalidKey = 1040;
        Integer validRepeatsNumber = 2;
        Tuple<Integer, Integer> invalidRepeatKey = new Tuple<>(invalidKey, validRepeatsNumber);
        assertThat(repeatKeyValidator.isValidKey(invalidRepeatKey), is(false));
    }

    @Test
    public void testIsValidKey_invalidRepeatsNumber() throws Exception {
        Integer validKey = 1;
        Integer invalidRepeatsNumber = -4;
        Tuple<Integer, Integer> invalidRepeatKey = new Tuple<>(validKey, invalidRepeatsNumber);
        assertThat(repeatKeyValidator.isValidKey(invalidRepeatKey), is(false));
    }
}