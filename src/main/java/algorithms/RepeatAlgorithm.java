package algorithms;

import suppliers.RepeatKeySupplier;
import utilities.Tuple;
import validators.RepeatKeyValidator;

/**
 * Created by Maor on 5/26/2017.
 */
public class RepeatAlgorithm<T> extends Algorithm<Tuple<T, Integer>> {

    Algorithm<T> algorithm;

    public RepeatAlgorithm(Algorithm<T> algorithm) {
        super(new RepeatKeyValidator<>(algorithm.getKeyValidator()), new RepeatKeySupplier<T>(algorithm.getKeySupplier()));
        this.algorithm = algorithm;
    }

    @Override
    public Integer encryptByte(Integer byteToEncrypt, Tuple<T, Integer> repeatKey) {
        T key = repeatKey.getFirst();
        for (int i = 0; i < repeatKey.getSecond(); i++) {
            byteToEncrypt = algorithm.encryptByte(byteToEncrypt, key);
        }

        return byteToEncrypt;
    }

    @Override
    public Integer decryptByte(Integer byteToDecrypt, Tuple<T, Integer> repeatKey) {
        T key = repeatKey.getFirst();
        for (int i = 0; i < repeatKey.getSecond(); i++) {
            byteToDecrypt = algorithm.decryptByte(byteToDecrypt, key);
        }

        return byteToDecrypt;
    }
}
