package ciphercommands;

import com.google.common.base.Stopwatch;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import suppliers.KeySupplier;
import utilities.EventNotifier;

import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * Created by Maor on 5/27/2017.
 */
public abstract class CipherCommand{
    protected final Logger logger = Logger.getLogger(getClass());

    protected KeySupplier<Integer> keySupplier;

    protected EventNotifier eventNotifier = new EventNotifier(logger);

    protected CipherCommand(KeySupplier<Integer> keySupplier) {
        this.keySupplier = keySupplier;
        PropertyConfigurator.configure("log4j.properties");
    }

    public abstract void execute(Path path, ExecutorService executorService) throws Exception;

    protected Object doEvent(Callable callable, String eventName) throws Exception {
        eventNotifier.notifyEventIsStarted(eventName);
        Stopwatch timer = Stopwatch.createStarted();
        Object returnedObject = callable.call();
        eventNotifier.notifyEventIsEnded(eventName,timer.stop());
        return returnedObject;
    }

    protected void doEvent(Runnable runnable, String eventName){
        eventNotifier.notifyEventIsStarted(eventName);
        Stopwatch timer = Stopwatch.createStarted();
        runnable.run();
        eventNotifier.notifyEventIsEnded(eventName,timer.stop());
    }
}
