package utilities;

import java.io.Serializable;

/**
 * Created by Maor on 5/29/2017.
 */
public class Key<T> implements Serializable{
    private T value;

    public T getValue() {
        return value;
    }
}
