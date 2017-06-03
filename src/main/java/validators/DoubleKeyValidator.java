package validators;

import utilities.Tuple;

/**
 * Created by Maor on 5/23/2017.
 */
public class DoubleKeyValidator<T,K> implements KeyValidator<Tuple<T,K>> {
    private String message = "";

    private KeyValidator<T> firstKeyValidator;
    private KeyValidator<K> secondKeyValidator;

    public DoubleKeyValidator(KeyValidator<T> firstKeyValidator, KeyValidator<K> secondKeyValidator) {
        this.firstKeyValidator = firstKeyValidator;
        this.secondKeyValidator = secondKeyValidator;
    }

    @Override
    public boolean isValidKey(Tuple<T, K> key) {
        boolean flag = true;
        if (!firstKeyValidator.isValidKey(key.getFirst())){
            message += "first " + firstKeyValidator.getInvalidKeyMessage() + ". ";
            flag = false;
        }
        if (!secondKeyValidator.isValidKey(key.getSecond())){
            message += "second " + secondKeyValidator.getInvalidKeyMessage() + ". ";
            flag = false;
        }
        return flag;
    }

    @Override
    public String getInvalidKeyMessage() {
        return message;
    }
}
