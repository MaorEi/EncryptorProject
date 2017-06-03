package algorithms;

import suppliers.KeySupplier;

/**
 * Created by Maor on 5/23/2017.
 */
public class ReverseAlgorithm<T> extends Algorithm<T>{
    private Algorithm<T> algorithm;

    public ReverseAlgorithm(Algorithm<T> algorithm) {
        super(algorithm.getKeyValidator(), algorithm.getKeySupplier());
        this.algorithm = algorithm;
    }


    @Override
    public Integer encryptByte(Integer byteToEncrypt, T key) {
        return null;
    }

    @Override
    public Integer decryptByte(Integer byteToDecrypt, T key) {
        return null;
    }
}
