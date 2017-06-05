package validators;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Maor on 6/5/2017.
 */
public class ConcretePathValidatorTest {
    ConcretePathValidator concretePathValidator = new ConcretePathValidator();

    @Test
    public void testIsValidKey_validKey() throws Exception {
        Path validPath = Files.createTempFile("","");
        assertThat(concretePathValidator.isValidPath(validPath),is(true));
    }

    @Test
    public void testIsValidKey_invalidKey() throws Exception {
        Path invalidPath = Paths.get("test.txt");
        assertThat(concretePathValidator.isValidPath(invalidPath),is(false));
    }

}