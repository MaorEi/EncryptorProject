package algorithms;

/**
 * Created by Maor on 5/26/2017.
 */
public class RepeatAlgorithm<T> extends Algorithm<T> {

    Integer repeats;
    Algorithm<T> algorithm;

    public RepeatAlgorithm(Algorithm<T> algorithm, Integer repeats) {
        super(algorithm.getKeyValidator(), algorithm.getKeySupplier());
        this.algorithm = algorithm;
        this.repeats = repeats;
    }

    @Override
    public Integer encryptByte(Integer byteToEncrypt, T key) {

        for (int i = 0; i < repeats; i++) {
            byteToEncrypt = algorithm.encryptByte(byteToEncrypt, key);
        }

        return byteToEncrypt;
    }

    @Override
    public Integer decryptByte(Integer byteToDecrypt, T key) {

        for (int i = 0; i < repeats; i++) {
            byteToDecrypt = algorithm.decryptByte(byteToDecrypt, key);
        }

        return byteToDecrypt;
    }
}
