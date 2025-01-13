package net.yasha;

public class DataHolder {
    public int data;
    Object lock = new Object();

    synchronized public int increment() {
        synchronized (lock) {
            return data++;
        }
    }

    public int  getvalue() {
        return data;
    }
}
