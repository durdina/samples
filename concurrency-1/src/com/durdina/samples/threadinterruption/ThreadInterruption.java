package com.durdina.samples.threadinterruption;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Example for thread interruption, graceful thread shutdown and resource cleanup.
 *
 * @author Michal Durdina
 */
public class ThreadInterruption {

    // expensive resources that need proper cleanup on shutdown
    private static final int NUM = 100000;
    private long[] data;
    private DataPrinter dataPrinter = new DataPrinter();

    public static void main(String[] args) {
        ThreadInterruption tt = new ThreadInterruption();
        try {
            tt.start();
            tt.printStatistics();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws InterruptedException {
        Thread dataPrinterThread = new Thread(dataPrinter);
        dataPrinterThread.start();
        // let's interrupt it after 500ms
        Thread.sleep(500);
        dataPrinterThread.interrupt();
        dataPrinter.awaitCompletion();
    }

    public void printStatistics() {
        System.out.println("Status of resources " + data.length);
    }

    class DataPrinter implements Runnable {

        private Random rand = new Random();

        private CountDownLatch executionComplete = new CountDownLatch(1);

//        private Object executionComplete = new Object();

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            System.out.println("starting the thread");

            data = randomFill(new long[NUM]);

            try {
                int c = -1;
                while (++c < data.length && !Thread.currentThread().isInterrupted()) {
                    System.out.println(c + ": " + data[c]);
                }
            } finally {
                System.out.println("releasing resources");
                data = new long[1];
                System.out.println("resources released");
                executionComplete.countDown();
//                synchronized (executionComplete) {
//                    executionComplete.notify();
//                }
                System.out.println("Thread finishing - time elapsed: " + (System.currentTimeMillis() - start) / 1000F + " s");
            }
        }

        public void awaitCompletion() {
            try {
                executionComplete.await();
//                synchronized(executionComplete) {
//                    executionComplete.wait();
//                }
                System.out.println("Shutdown complete");
            } catch (InterruptedException e) {
                System.out.println("Interrupt during shutdown: " + e.getMessage());
            }
        }

        private long[] randomFill(long[] anArray) {
            for (int i = 0; i < anArray.length; i++) {
                anArray[i] = rand.nextLong();
            }
            return anArray;
        }

    }
}
