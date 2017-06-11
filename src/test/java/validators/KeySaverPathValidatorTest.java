package validators;

import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Maor on 6/5/2017.
 */
public class KeySaverPathValidatorTest {
    KeyPathValidator keyPathValidator = new KeyPathValidator();

    @Test
    public void testIsValidKey_validKey() throws Exception {
        Path directoryPath = Files.createTempDirectory("");
        Path validKeyPath = Files.createFile(Paths.get(directoryPath.toString(),"key.bin"));
        assertThat(keyPathValidator.isValidPath(validKeyPath),is(true));
    }

    @Test
    public void testIsValidKey_invalidKey() throws Exception {
        Path invalidKeyPath = Files.createTempFile("","");
        assertThat(keyPathValidator.isValidPath(invalidKeyPath),is(false));
    }
}