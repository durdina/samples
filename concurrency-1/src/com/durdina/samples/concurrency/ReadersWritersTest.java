package com.durdina.samples.concurrency;
import static org.junit.Assert.assertTrue;
import java.util.concurrent.CountDownLatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class ReadersWritersTest {

	@Test
	public void test() throws Exception {

		Concurrency c = new Concurrency();
		ExecutorService pool = Executors.newFixedThreadPool(50);
		
		int N = 1000;
		CountDownLatch doneSignal = new CountDownLatch(N);
		
		for (int i = 0; i < N; i++) {
			pool.execute(new Worker(c, doneSignal));
		}
		
		doneSignal.await();
		
		Integer[] all = c.readAll();
		System.out.println("Written " + all.length + " items");
		
		
		
		for (int i = 1; i < all.length; i++) {
			assertTrue(all[i] > all[i-1]);
		}
		
	}

	static class Worker implements Runnable {

		private Concurrency c;
		private CountDownLatch doneSignal;

		public Worker(Concurrency c, CountDownLatch doneSignal) {
			this.c = c;
			this.doneSignal = doneSignal;
		}

		@Override
		public void run() {

			int l = c.readHighest();
			int l2 = c.readHighest();
			
			if (l2 == l) {
				System.out.print((l+1) +  ", ");
				c.write(l+1);
			} else {
				System.out.println("NOT INCREMENTING at " + l);
			}
			
			doneSignal.countDown();
			
		}
	};

}
