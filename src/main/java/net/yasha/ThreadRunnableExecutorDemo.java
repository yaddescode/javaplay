package net.yasha;

import lombok.Data;
import lombok.Getter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data
public class ThreadRunnableExecutorDemo implements Runnable {

    int id;
    String name;
    DataHolder dataHolder;

    public ThreadRunnableExecutorDemo(String name, DataHolder dh) {
        this.name = name;
        this.dataHolder = dh;
    }

    public String getName() {
        return name;
    }

    synchronized public int getCount() {
        return dataHolder.getvalue();
    }


    synchronized public int addCount() {
        return dataHolder.increment();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            addCount();
            if (i % 100 == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println(String.format("THREAD DONE. [%s] COUNT %s", name, getCount()));
    }
}
