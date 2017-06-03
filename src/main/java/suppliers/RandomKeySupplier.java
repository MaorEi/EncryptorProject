package suppliers;

import java.util.Random;

/**
 * Created by Maor on 5/23/2017.
 */
public class RandomKeySupplier implements KeySupplier<Integer> {
    Random randomKey = new Random();
    public Integer supplyKey() {
        return randomKey.nextInt(255) + 1;
    }
}
