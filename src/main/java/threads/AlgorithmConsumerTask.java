package threads;

import com.google.common.base.Stopwatch;
import utilities.Tuple;

import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Created by Maor on 6/11/2017.
 */
public class AlgorithmConsumerTask implements Consumer<Tuple<Path,Path>> {
    private Consumer<Tuple<Path,Path>> consumer;

    public AlgorithmConsumerTask(Consumer<Tuple<Path, Path>> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void accept(Tuple<Path, Path> tuplePath) {
        consumer.accept(tuplePath);
    }
}
