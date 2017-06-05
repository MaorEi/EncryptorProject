package algorithms;

import org.junit.Test;
import suppliers.KeySupplier;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Maor on 6/5/2017.
 */
public class XorAlgorithmTest {
    private KeySupplier<Integer> keySupplier = mock(KeySupplier.class);
    private XorAlgorithm xorAlgorithm = new XorAlgorithm(keySupplier);

    @Test
    public void testEncryptByte() throws Exception {
        Integer byteToEncrypt = 2;
        Integer key = 2;
        Integer expectedResult = 0;
        when(keySupplier.supplyKey()).thenReturn(key);
        Integer encryptedByte = xorAlgorithm.encryptByte(byteToEncrypt, keySupplier.supplyKey());
        assertThat(encryptedByte, is(equalTo(expectedResult)));
    }

    @Test
    public void testDecryptByte() throws Exception {
        Integer byteToDecrypt = 2;
        Integer key = 2;
        Integer expectedResult = 0;
        when(keySupplier.supplyKey()).thenReturn(key);
        Integer decryptedByte = xorAlgorithm.decryptByte(byteToDecrypt, keySupplier.supplyKey());
        assertThat(decryptedByte, is(equalTo(expectedResult)));
    }
}