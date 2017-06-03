package ciphercommands;

import suppliers.KeySupplier;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by Maor on 5/27/2017.
 */
public abstract class CipherCommand {
    protected KeySupplier<Integer> keySupplier;

    protected CipherCommand(KeySupplier<Integer> keySupplier) {
        this.keySupplier = keySupplier;
    }

    public abstract void execute(Path path) throws IOException;
}
