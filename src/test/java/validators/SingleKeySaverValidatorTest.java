package validators;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

/**
 * Created by Maor on 6/5/2017.
 */
public class SingleKeySaverValidatorTest {

    private SingleKeyValidator singleKeyValidator = new SingleKeyValidator();

    @Test
    public void testIsValidKey_validKey() throws Exception {
        Integer validKey = 1;
        assertThat(singleKeyValidator.isValidKey(validKey), is(true));
    }

    @Test
    public void testIsValidKey_invalidKey() throws Exception {
        Integer invalidKey = 1090;
        assertThat(singleKeyValidator.isValidKey(invalidKey), is(not(true)));
    }
}