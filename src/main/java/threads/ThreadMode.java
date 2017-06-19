package threads;

import utilities.Audible;
import utilities.Listner;
import utilities.Tuple;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Maor on 6/6/2017.
 */
public class ThreadMode {

    private Consumer<Tuple<Path, Path>> algorithmConsumerTask;
    private List<Tuple<Path, Path>> pathArrayList;
    private ExecutorService executorService;

    public ThreadMode(Consumer<Tuple<Path, Path>> consumerTask, List<Tuple<Path, Path>> pathArrayList, ExecutorService executorService) {
        this.algorithmConsumerTask = consumerTask;
        this.pathArrayList = pathArrayList;
        this.executorService = executorService;
    }

    public void activate() throws InterruptedException {
        pathArrayList.forEach((tuplePath) -> executorService.submit(() -> algorithmConsumerTask.accept(tuplePath)));
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }
}