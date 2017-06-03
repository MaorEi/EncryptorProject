package algorithms;

import suppliers.KeySupplier;
import suppliers.ValidKeySupplier;
import utilities.Key;
import validators.KeyValidator;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.newOutputStream;

/**
 * Created by Maor on 5/23/2017.
 */
public abstract class Algorithm<T> {
    private KeyValidator<T> keyValidator;
    private KeySupplier<T> keySupplier;

    private T key;

    public Algorithm(KeyValidator<T> keyValidator, KeySupplier<T> keySupplier) {
        this.keyValidator = keyValidator;
        this.keySupplier = keySupplier;
    }

    public void supplyValidKeyToAlgorithm() throws IOException, ClassNotFoundException {
        this.key = new ValidKeySupplier<>(keySupplier, keyValidator).supplyKey();
    }

    public void createKeyBinFile(Path keyBinFileFolder) throws IOException {
        Key<T> key = new Key<>(this.key);
        Path keyBinFilePath = Paths.get(keyBinFileFolder.toString(),"key.bin");
        try {
            Files.createFile(keyBinFilePath);
        } catch (FileAlreadyExistsException e) {
            Files.delete(keyBinFilePath);
            Files.createFile(keyBinFilePath);
        }
        ObjectOutputStream objectOutputStream= new ObjectOutputStream(Files.newOutputStream(keyBinFilePath));
        objectOutputStream.writeObject(key);
        System.out.println("Serialized key data is saved in " + keyBinFilePath.toString());

    }

    public void setKey(T key) {
        this.key = key;
    }

    public KeyValidator<T> getKeyValidator() {
        return keyValidator;
    }

    public abstract Integer encryptByte(Integer byteToEncrypt, T key);

    public abstract Integer decryptByte(Integer byteToDecrypt, T key);

    public void encrypt(InputStream inputStreamToEncrypt, OutputStream encryptedOutputStream) throws IOException {
        Integer inputByteToEncrypt;

        while ((inputByteToEncrypt = inputStreamToEncrypt.read()) != -1) {
            encryptedOutputStream.write(encryptByte(inputByteToEncrypt, key));
        }
    }

    public void decrypt(InputStream inputStreamToDecrypt, OutputStream decryptedOutputStream) throws IOException {
        Integer inputByteToDecrypt;

        while ((inputByteToDecrypt = inputStreamToDecrypt.read()) != -1) {
            decryptedOutputStream.write(decryptByte(inputByteToDecrypt, key));
        }
    }

    public KeySupplier<T> getKeySupplier() {
        return keySupplier;
    }

}
