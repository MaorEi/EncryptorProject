package suppliers;

import utilities.Tuple;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Maor on 6/5/2017.
 */
public class RepeatKeySupplier<T> implements KeySupplier<Tuple<T,Integer>>{

    private KeySupplier<T> keySupplier;

    public RepeatKeySupplier(KeySupplier<T> keySupplier) {
        this.keySupplier = keySupplier;
    }

    @Override
    public Tuple<T, Integer> supplyKey() throws IOException, ClassNotFoundException {
        T key = keySupplier.supplyKey();
        System.out.println("Enter number of repeats:");
        return new Tuple<>(key, new Scanner(System.in).nextInt());
    }
}
