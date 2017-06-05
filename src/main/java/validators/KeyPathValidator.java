package validators;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Maor on 6/4/2017.
 */
public class KeyPathValidator implements PathValidator {

    private String message;

    @Override
    public boolean isValidPath(Path path) {
        boolean flag = true;

        if (!path.getFileName().toString().equals("key.bin")) {
            flag = false;
            message = "Key path is invalid!.";
        }
        if (!Files.isRegularFile(path)) {
            flag = false;
            message += " Key path isn't a file or doesn't exists.";
        }
        return flag;
    }

    @Override
    public String getInvalidPathMessage() {
        return message;
    }
}
