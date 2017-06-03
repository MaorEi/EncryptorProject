package algorithms;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import suppliers.KeySupplier;
import validators.EvenKeyValidator;
import validators.KeyValidator;

/**
 * Created by Maor on 5/23/2017.
 */
public class MwoAlgorithm extends Algorithm<Integer> {

    @Inject
    public MwoAlgorithm(KeySupplier<Integer> keySupplier) {
        super(new EvenKeyValidator(), keySupplier);
    }

    @Override
    public Integer encryptByte(Integer byteToEncrypt, Integer key) {
        Byte mwoResult = (byte) (byteToEncrypt * key);
        return mwoResult.intValue();
    }

    @Override
    public Integer decryptByte(Integer byteToDecrypt, Integer key) {
        int i = 1;
        Byte mwoResult = 1;
        while (i < 256 && (mwoResult = (byte) (key * i)) != 1) {
            i++;
        }
        return mwoResult.intValue();
    }
}
