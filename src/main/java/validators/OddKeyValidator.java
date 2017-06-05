package validators;

/**
 * Created by Maor on 5/23/2017.
 */
public class OddKeyValidator implements KeyValidator<Integer> {

    SingleKeyValidator singleKeyValidator = new SingleKeyValidator();

    public boolean isValidKey(Integer key) {
        return key % 2 != 0 && singleKeyValidator.isValidKey(key);
    }

    @Override
    public String getInvalidKeyMessage() {
        return "Key must be a number between 1 to 255 and odd";
    }
}
