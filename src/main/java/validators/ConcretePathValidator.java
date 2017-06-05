package validators;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Maor on 6/4/2017.
 */
public class ConcretePathValidator implements PathValidator {
    @Override
    public boolean isValidPath(Path path) {
        return Files.exists(path);
    }

    @Override
    public String getInvalidPathMessage() {
        return "File path isn't exist!";
    }
}
