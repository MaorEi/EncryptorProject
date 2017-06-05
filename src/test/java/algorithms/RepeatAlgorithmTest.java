package algorithms;

import org.junit.Test;
import suppliers.KeySupplier;
import suppliers.RepeatKeySupplier;
import utilities.Tuple;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Maor on 6/5/2017.
 */
public class RepeatAlgorithmTest {
    private KeySupplier<Integer> keySupplier = mock(KeySupplier.class);
    private RepeatKeySupplier<Integer> repeatKeySupplier = mock(RepeatKeySupplier.class);
    private RepeatAlgorithm<Integer> repeatAlgorithm = new RepeatAlgorithm<>(new CaesarAlgorithm(keySupplier));

    @Test
    public void testEncryptByte() throws Exception {
        Integer byteToEncrypt = 2;
        Integer key = 2;
        Integer repeats = 2;
        Tuple<Integer, Integer> repeatKey = new Tuple<>(key, repeats);
        Integer expectedResult = 6;
        when(repeatKeySupplier.supplyKey()).thenReturn(repeatKey);
        Integer encryptedByte = repeatAlgorithm.encryptByte(byteToEncrypt, repeatKeySupplier.supplyKey());
        assertThat(encryptedByte, is(equalTo(expectedResult)));
    }

    @Test
    public void testDecryptByte() throws Exception {
        Integer byteToDecrypt = 4;
        Integer key = 2;
        Integer repeats = 2;
        Tuple<Integer, Integer> repeatKey = new Tuple<>(key, repeats);
        Integer expectedResult = 0;
        when(repeatKeySupplier.supplyKey()).thenReturn(repeatKey);
        Integer decryptedByte = repeatAlgorithm.decryptByte(byteToDecrypt, repeatKeySupplier.supplyKey());
        assertThat(decryptedByte, is(equalTo(expectedResult)));
    }
}