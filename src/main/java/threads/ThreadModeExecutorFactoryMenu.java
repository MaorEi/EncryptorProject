package threads;

import utilities.Menu;

import java.util.HashMap;
import java.util.InputMismatchException;
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
        String defaultNumberOfThreadsMessage = "Enter number of threads:";
        map.put("sync", () -> Executors.newSingleThreadExecutor());
        map.put("async", () -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println(defaultNumberOfThreadsMessage);

            while (!scanner.hasNextInt()) {
                System.out.println("Input isn't a number");
                System.out.println(defaultNumberOfThreadsMessage);
                scanner.next();
            }
            int numberOfThreads = scanner.nextInt();
            return Executors.newFixedThreadPool(numberOfThreads);
        });
    }
}
