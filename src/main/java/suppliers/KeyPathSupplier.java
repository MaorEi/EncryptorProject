package suppliers;

import utilities.KeySaver;
import validators.KeyPathValidator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;

/**
 * Created by Maor on 5/29/2017.
 */
public class KeyPathSupplier<T> implements KeySupplier<T> {

    @Override
    public T supplyKey() throws IOException, ClassNotFoundException {

        String headMessage = "Enter Path of the key.bin File:";
        PathSupplier keyPathSupplier = new PathSupplier(new KeyPathValidator(), headMessage);
        Path keyPath = keyPathSupplier.supplyPath();
        FileInputStream fileInputStream = new FileInputStream(keyPath.toFile());
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        @SuppressWarnings("unchecked")
        KeySaver<T> key = (KeySaver<T>) objectInputStream.readObject();
        return key.getValue();
    }
}
