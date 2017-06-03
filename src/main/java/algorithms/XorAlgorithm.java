package algorithms;

import suppliers.KeySupplier;
import validators.SingleKeyValidator;

/**
 * Created by Maor on 5/23/2017.
 */
public class XorAlgorithm extends Algorithm<Integer> {

    public XorAlgorithm(KeySupplier<Integer> keySupplier) {
        super(new SingleKeyValidator(), keySupplier);
    }

    @Override
    public Integer encryptByte(Integer byteToEncrypt, Integer key) {
        return byteToEncrypt ^ key;
    }

    @Override
    public Integer decryptByte(Integer byteToDecrypt, Integer key) {
        return encryptByte(byteToDecrypt, key);
    }
}
