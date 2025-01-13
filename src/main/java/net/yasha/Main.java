package net.yasha;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        int numThreads =5  ;
        ThreadRunnableExecutorDemo[] runnables = new ThreadRunnableExecutorDemo[numThreads];
        Future[] futures = new Future[numThreads];
        DataHolder dh = new DataHolder();

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < numThreads; i++) {
            runnables[i] = new ThreadRunnableExecutorDemo("name"+i, dh);
            futures[i] = executor.submit(runnables[i]);
        }
        System.out.println("done_from_main! " + runnables.length);

        executor.shutdown();
        executor.awaitTermination(1000, TimeUnit.SECONDS);
        for (int i = 0; i < runnables.length; i++) {
            System.out.println(String.format("ALL FINISHED ---> %s  %s COMMON %s", runnables[i].getName(), runnables[i].getCount(), runnables[i].getCount()));
        }


    }
}