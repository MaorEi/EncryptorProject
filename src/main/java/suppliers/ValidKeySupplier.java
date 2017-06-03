package suppliers;

import validators.KeyValidator;

import java.io.IOException;

/**
 * Created by Maor on 5/24/2017.
 */
public class ValidKeySupplier<T> implements KeySupplier<T> {
    private KeySupplier<T> keySupplier;
    private KeyValidator<T> keyValidator;

    public ValidKeySupplier(KeySupplier<T> keySupplier, KeyValidator<T> keyValidator) {
        this.keySupplier = keySupplier;
        this.keyValidator = keyValidator;
    }

    public T supplyKey() throws IOException, ClassNotFoundException {
        T key;
        while (!keyValidator.isValidKey(key = keySupplier.supplyKey())) {
            System.out.println(keyValidator.getInvalidKeyMessage());
        }
        return key;
    }
}
