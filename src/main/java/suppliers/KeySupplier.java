package suppliers;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Maor on 5/23/2017.
 */
public interface KeySupplier<T> {
    T supplyKey() throws IOException, ClassNotFoundException;
}
