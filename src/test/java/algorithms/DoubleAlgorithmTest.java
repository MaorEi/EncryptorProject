package algorithms;

import org.junit.Test;
import suppliers.DoubleKeySupplier;
import suppliers.KeySupplier;
import utilities.Tuple;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Maor on 6/5/2017.
 */
public class DoubleAlgorithmTest {
    private KeySupplier<Integer> keySupplier = mock(KeySupplier.class);
    private DoubleKeySupplier<Integer, Integer> doubleKeySupplier = mock(DoubleKeySupplier.class);
    private DoubleAlgorithm<Integer, Integer> doubleAlgorithm = new DoubleAlgorithm<>(new CaesarAlgorithm(keySupplier), new MwoAlgorithm(keySupplier));

    @Test
    public void testEncryptByte() throws Exception {
        Integer byteToEncrypt = 2;
        Integer firstKey = 2;
        Integer secondKey = 3;
        Tuple<Integer, Integer> doubleKey = new Tuple<>(firstKey, secondKey);
        Integer expectedResult = 12;
        when(doubleKeySupplier.supplyKey()).thenReturn(doubleKey);
        Integer encryptedByte = doubleAlgorithm.encryptByte(byteToEncrypt, doubleKeySupplier.supplyKey());
        assertThat(encryptedByte, is(equalTo(expectedResult)));
    }

    @Test
    public void testDecryptByte() throws Exception {
        Integer byteToEncrypt = 12;
        Integer firstKey = 2;
        Integer secondKey = 3;
        Tuple<Integer, Integer> doubleKey = new Tuple<>(firstKey, secondKey);
        Integer expectedResult = 2;
        when(doubleKeySupplier.supplyKey()).thenReturn(doubleKey);
        Integer decryptedByte = doubleAlgorithm.decryptByte(byteToEncrypt, doubleKeySupplier.supplyKey());
        assertThat(decryptedByte, is(equalTo(expectedResult)));
    }
}