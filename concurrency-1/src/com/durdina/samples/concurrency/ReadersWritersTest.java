package com.durdina.samples.concurrency;

import static org.junit.Assert.assertTrue;
import java.util.concurrent.CountDownLatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class ReadersWritersTest {

    @Test
    public void test() throws Exception {

        Resource resource = new Resource();
        ExecutorService pool = Executors.newFixedThreadPool(50);

        int N = 1000;
        CountDownLatch doneSignal = new CountDownLatch(N);

        for (int i = 0; i < N; i++) {
            pool.execute(new Worker(resource, doneSignal));
        }

        doneSignal.await();

        Integer[] all = resource.readAll();
        System.out.println("Written " + all.length + " items");

        for (int i = 1; i < all.length; i++) {
            assertTrue(all[i] > all[i - 1]);
        }

    }

    static class Worker implements Runnable {

        private Resource resource;
        private CountDownLatch doneSignal;

        public Worker(Resource resource, CountDownLatch doneSignal) {
            this.resource = resource;
            this.doneSignal = doneSignal;
        }

        @Override
        public void run() {

            int l = resource.readHighest();
            int l2 = resource.readHighest();

            if (l2 == l) {
                System.out.print((l + 1) + ", ");
                resource.write(l + 1);
            } else {
                System.out.println("NOT INCREMENTING at " + l);
            }

            doneSignal.countDown();

        }
    };

}
