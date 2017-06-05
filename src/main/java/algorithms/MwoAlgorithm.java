package algorithms;

import com.google.inject.Inject;
import suppliers.KeySupplier;
import validators.OddKeyValidator;

/**
 * Created by Maor on 5/23/2017.
 */
public class MwoAlgorithm extends Algorithm<Integer> {

    @Inject
    public MwoAlgorithm(KeySupplier<Integer> keySupplier) {
        super(new OddKeyValidator(), keySupplier);
    }

    @Override
    public Integer encryptByte(Integer byteToEncrypt, Integer key) {
        Byte mwoResult = (byte) (byteToEncrypt * key);
        return mwoResult.intValue();
    }

    @Override
    public Integer decryptByte(Integer byteToDecrypt, Integer key) {
        int i = 1;
        while ((byte) (key * i) != 1) {
            i++;
        }
        Byte mwoResult = (byte) (byteToDecrypt * i);
        return mwoResult.intValue();
    }
}
