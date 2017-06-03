package suppliers;

import utilities.Tuple;

/**
 * Created by Maor on 5/24/2017.
 */
public class DoubleKeySupplier<T,K> implements KeySupplier<Tuple<T,K>> {

    private KeySupplier<T> firstKeySupplier;
    private KeySupplier<K> secondKeySupplier;

    public DoubleKeySupplier(KeySupplier<T> firstKeySupplier, KeySupplier<K> secondKeySupplier) {
        this.firstKeySupplier = firstKeySupplier;
        this.secondKeySupplier = secondKeySupplier;
    }

    public Tuple<T, K> supplyKey() {
        return new Tuple<>(firstKeySupplier.supplyKey(),secondKeySupplier.supplyKey());
    }
}
