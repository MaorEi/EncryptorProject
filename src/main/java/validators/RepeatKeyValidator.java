package validators;

import utilities.Tuple;

/**
 * Created by Maor on 6/5/2017.
 */
public class RepeatKeyValidator<T> implements KeyValidator<Tuple<T, Integer>> {
    private String message = "";

    private KeyValidator<T> keyValidator;

    public RepeatKeyValidator(KeyValidator<T> keyValidator) {
        this.keyValidator = keyValidator;
    }

    @Override
    public boolean isValidKey(Tuple<T, Integer> key) {
        boolean flag = true;
        if (!keyValidator.isValidKey(key.getFirst())) {
            message += keyValidator.getInvalidKeyMessage() + ". ";
            flag = false;
        }
        if (key.getSecond() <= 0) {
            message += "number of repeats should be at least 1";
            flag = false;
        }
        return flag;
    }

    @Override
    public String getInvalidKeyMessage() {
        return message;
    }
}
