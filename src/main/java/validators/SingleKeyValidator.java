package validators;

/**
 * Created by Maor on 5/23/2017.
 */
public class SingleKeyValidator implements KeyValidator<Integer> {
    public boolean isValidKey(Integer key) {
        System.out.println(key);
        return key > 0 && key < 256;
    }

    @Override
    public String getInvalidKeyMessage() {
        return "Key must be a number between 1 to 255";
    }
}
