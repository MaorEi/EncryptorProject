package ciphercommands;

import suppliers.KeySupplier;
import utilities.Audible;
import utilities.Listner;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * Created by Maor on 5/27/2017.
 */
public abstract class CipherCommand extends Audible{
    protected KeySupplier<Integer> keySupplier;

    protected CipherCommand(KeySupplier<Integer> keySupplier) {
        super(new ArrayList<>());
        this.keySupplier = keySupplier;
    }

    public abstract void execute(Path path, ExecutorService executorService) throws Exception;

    protected Object doEvent(Callable callable, String string) throws Exception {

        notifyAllEventStarted(string);
        Object returnedObject = callable.call();
        notifyAllEventEnded(string);

        return returnedObject;
    }
}
