package net.yasha;

public class ThreadDemo extends Thread {

    private final String name;
    long count;

    public long getCount() {
        return count;
    }

    public ThreadDemo(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000001; i++) {
                count++;
        }
        System.out.println(String.format("[%s] COUNT %s", name, count));
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo td = new ThreadDemo("name1");
        ThreadDemo td2 = new ThreadDemo("name2");
        ThreadDemo td3 = new ThreadDemo("name3");
        td2.start();
        td.start();
        td3.start();
        td.join();
        td2.join();
        td3.join();
        System.out.println(String.format("done %s %s", td.getCount(), td2.getCount()));
    }
}
