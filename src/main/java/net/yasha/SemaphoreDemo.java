package net.yasha;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        SemaphoreDemo m = new SemaphoreDemo();
        System.out.println("available: " + semaphore.availablePermits());
        var numThreads = 100;

        var executor = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < numThreads; i++) {
            executor.submit(() -> {
                m.tryAcquire(semaphore);
            });
        }
    }

    private boolean tryAcquire(Semaphore semaphore) {
        return semaphore.tryAcquire();
    }
}
