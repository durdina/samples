package com.durdina.samples.threadinterruption;

import java.util.Random;

public class ThreadInterruption {

    private static final int NUM = 10;

    private long[] data;

    private Runnable dataPrinter = new Runnable() {

        Random rand = new Random();

        @Override
        public void run() {
            data = randomFill(new long[NUM]);

            try {
                int c = -1;
                while (++c < data.length) {
                    System.out.println(c + ": " + data[c]);
                    Thread.sleep(1000L);
                }
            } catch (InterruptedException e) {
                System.err.println("Interrupted");
            } finally {
                System.out.println("releasing resources");
                data = new long[1];
                System.out.println("resources released");
            }
        }

        private long[] randomFill(long[] anArray) {
            for (int i = 0; i < anArray.length; i++) {
                anArray[i] = rand.nextLong();
            }
            return anArray;
        }

    };

    public static void main(String[] args) {
        ThreadInterruption tt = new ThreadInterruption();
        tt.run();
        tt.printStatistics();
    }

    public void run() {
        Thread dataPrinterThread = new Thread(dataPrinter);
        dataPrinterThread.start();
        try {
            dataPrinterThread.join(3000);
            dataPrinterThread.interrupt();
            dataPrinterThread.join();
        } catch (InterruptedException e) {
            // ignore
            e.printStackTrace();
        }
    }

    public void printStatistics() {
        System.out.println("Status of resources " + data.length);
    }

}
