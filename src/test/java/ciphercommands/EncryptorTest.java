package ciphercommands;

import ciphercommands.Encryptor;
import org.junit.Test;
import suppliers.RandomKeySupplier;

import java.nio.file.Paths;

/**
 * Created by Maor on 5/27/2017.
 */
public class EncryptorTest {

    @Test
    public void testExecute() throws Exception {
        Encryptor encryptor = new Encryptor(new RandomKeySupplier());
        //encryptor.execute(Paths.get("C:/Users/Maor/Desktop/missions.txt"));
    }
}