package algorithms;

import com.google.inject.Inject;
import suppliers.DoubleKeySupplier;
import utilities.Tuple;
import validators.DoubleKeyValidator;


public class DoubleAlgorithm<T, K> extends Algorithm<Tuple<T, K>> {
    private Algorithm<T> firstAlgorithm;
    private Algorithm<K> secondAlgorithm;

    @Inject
    public DoubleAlgorithm(Algorithm<T> firstAlgorithm, Algorithm<K> secondAlgorithm) {
        super(new DoubleKeyValidator<>(firstAlgorithm.getKeyValidator(), secondAlgorithm.getKeyValidator()),new DoubleKeySupplier<>(firstAlgorithm.getKeySupplier(),secondAlgorithm.getKeySupplier()));
        this.firstAlgorithm = firstAlgorithm;
        this.secondAlgorithm = secondAlgorithm;
    }

    @Override
    public Integer encryptByte(Integer byteToEncrypt, Tuple<T, K> key) {
        return secondAlgorithm.encryptByte(firstAlgorithm.encryptByte(byteToEncrypt, key.getFirst()), key.getSecond());
    }

    @Override
    public Integer decryptByte(Integer byteToDecrypt, Tuple<T, K> key) {
        return firstAlgorithm.decryptByte(secondAlgorithm.decryptByte(byteToDecrypt, key.getSecond()), key.getFirst());
    }
}
