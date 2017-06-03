package validators;

/**
 * Created by Maor on 5/23/2017.
 */
public interface KeyValidator<T> {
    boolean isValidKey(T key);
    String getInvalidKeyMessage();
}
