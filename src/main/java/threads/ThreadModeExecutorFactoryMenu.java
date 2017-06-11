package threads;

import utilities.Menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/**
 * Created by Maor on 6/7/2017.
 */
public class ThreadModeExecutorFactoryMenu extends Menu<Supplier<ExecutorService>> {
    public ThreadModeExecutorFactoryMenu() {
        super(new HashMap<>(), "Enter Thread Mode:", "Thread Mode is invalid.");
    }

    @Override
    public void intializeMap() {
        map.put("sync", () -> Executors.newSingleThreadExecutor());
        map.put("async", () -> {
            System.out.println("Enter number of threads:");
            int numberOfThreads = new Scanner(System.in).nextInt();
            return Executors.newFixedThreadPool(numberOfThreads);
        });
    }
}
