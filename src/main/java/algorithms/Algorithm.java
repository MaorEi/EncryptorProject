package algorithms;

import suppliers.KeySupplier;
import suppliers.ValidKeySupplier;
import utilities.Key;
import validators.KeyValidator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Maor on 5/23/2017.
 */
public abstract class Algorithm<T> {
    private KeyValidator<T> keyValidator;
    private KeySupplier<T> keySupplier;

    private T key;
    public void writeKey(Path path) throws IOException {
        String keyOutput = "key/s: " + System.getProperty("line.separator") + key.toString();
        Files.newOutputStream(path).write(keyOutput.getBytes());
    }

    public Algorithm(KeyValidator<T> keyValidator, KeySupplier<T> keySupplier) {
        this.keyValidator = keyValidator;
        this.keySupplier = keySupplier;
    }

    /*public void supplyValidKeyToAlgorithm() {
        this.key = new ValidKeySupplier<>(keySupplier,keyValidator).supplyKey();
        Key<T> serializableKey =
    }*/

    public void setKey(T key) {
        this.key = key;
    }

    public KeyValidator<T> getKeyValidator() {
        return keyValidator;
    }

    public abstract Integer encryptByte(Integer byteToEncrypt, T key);
    public abstract Integer decryptByte(Integer byteToDecrypt, T key);

    public void encrypt(InputStream inputStreamToEncrypt, OutputStream encryptedOutputStream, T key) throws IOException {
        Integer inputByteToEncrypt;

        while ((inputByteToEncrypt = inputStreamToEncrypt.read()) != -1){
            encryptedOutputStream.write(encryptByte(inputByteToEncrypt,key));
        }
    }

    public void decrypt(InputStream inputStreamToDecrypt, OutputStream decryptedOutputStream) throws IOException {
        Integer inputByteToDecrypt;

        while ((inputByteToDecrypt = inputStreamToDecrypt.read()) != -1){
            decryptedOutputStream.write(decryptByte(inputByteToDecrypt,key));
        }
    }

    public KeySupplier<T> getKeySupplier() {
        return keySupplier;
    }

}
