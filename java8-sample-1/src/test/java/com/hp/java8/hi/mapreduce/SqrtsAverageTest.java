package com.hp.java8.hi.mapreduce;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.BeforeClass;
import org.junit.Test;

public class SqrtsAverageTest {

	@BeforeClass
	public static void beforeClass() {
		// warm-up
		System.out.println("Starting");
		IntStream.range(0, 10).parallel().forEach(e -> System.out.print('.'));
	}

	@Test
	public void testSequential() throws Exception {

		Stream<Double> randoms = Stream.generate(Math::random).limit(1000000);
		OptionalDouble average = randoms.filter(d -> d < 0.5).map(Math::sqrt)
				.mapToDouble(Double::doubleValue).average();

		System.out.println("RESULT = " + average);
	}

	@Test
	public void testParallelSlow() throws Exception {

		Stream<Double> randoms = Stream.generate(Math::random).parallel()
				.limit(1000000);
		OptionalDouble average = randoms.filter(d -> d < 0.5).map(Math::sqrt)
				.mapToDouble(Double::doubleValue).average();

		System.out.println("RESULT = " + average);
	}

	@Test
	public void testParallelFastTemp() throws Exception {

		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i < 1000000; i++) {
			list.add(i);
		}
		// Here creating a parallel stream
		Stream<Integer> stream = list.parallelStream();
		ConcurrentMap<Integer, Integer> collect = stream
				.filter(i -> i % 2 == 0).collect(
						Collectors.toConcurrentMap(i -> i, i -> i));
	}

	@Test
	public void testParallelFast() throws Exception {

		Stream<Double> randoms = Stream.generate(Math::random).parallel()
				.limit(10000000);

		ConcurrentLinkedQueue<Double> collect = randoms
				.filter(d -> d < 0.5)
				.map(Math::sqrt)
				.collect(ConcurrentLinkedQueue<Double>::new,
						ConcurrentLinkedQueue<Double>::add,
						ConcurrentLinkedQueue::addAll);

		// System.out.println("RESULT = " + average);
	}

	// HINT see also WordCountSeq main method

}
