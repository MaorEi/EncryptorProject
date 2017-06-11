package utilities;

import java.io.Serializable;

/**
 * Created by Maor on 5/29/2017.
 */
public class KeySaver<T> implements Serializable{
    private T value;

    public KeySaver(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
