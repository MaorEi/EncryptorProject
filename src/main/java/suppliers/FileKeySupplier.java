package suppliers;

import utilities.Key;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Created by Maor on 5/29/2017.
 */
public class FileKeySupplier<T> implements KeySupplier<T> {

    @Override
    public T supplyKey() throws IOException, ClassNotFoundException {
        Key<T> key;
        Scanner scanner = new Scanner(System.in);
        FilePathSupplier filePathSupplier = new FilePathSupplier();
        Path keyPath = filePathSupplier.supplyFilePath();
        FileInputStream fileInputStream = new FileInputStream(keyPath.toFile());
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        key = (Key<T>) objectInputStream.readObject();
        return key.getValue();
    }
}
