package com.kienast.interThreadCommunication;

public class SimpleCountDownLatch {

    private int count;

    public SimpleCountDownLatch(int count) {
        this.count = count;
        if (count < 0) {
            throw new IllegalArgumentException("Count cannot be negative!");
        }
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            while (count > 0) {
                this.wait();
            }
        }
    }

    public void countDown() {
        synchronized (this) {
            if (count > 0) {
                count--;
                if (count == 0) {
                    this.notifyAll();
                }
            }
        }
    }

    public int getCount() {
        return this.count;
    }

}
