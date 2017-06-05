package validators;

import java.nio.file.Path;

/**
 * Created by Maor on 6/4/2017.
 */
public interface PathValidator {
    boolean isValidPath(Path path);
    String getInvalidPathMessage();
}
