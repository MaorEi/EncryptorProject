package validators;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by Maor on 6/5/2017.
 */
public class OddKeySaverValidatorTest {
    OddKeyValidator evenKeyValidator = new OddKeyValidator();

    @Test
    public void testIsValidKey_validKey() throws Exception {
        Integer validKey = 1;
        assertThat(evenKeyValidator.isValidKey(validKey), is(true));
    }

    @Test
    public void testIsValidKey_invalidKey() throws Exception {
        Integer invalidKey = 2;
        assertThat(evenKeyValidator.isValidKey(invalidKey), is(false));
    }

}