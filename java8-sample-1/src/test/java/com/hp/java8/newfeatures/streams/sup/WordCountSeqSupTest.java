package com.hp.java8.newfeatures.streams.sup;

import static org.junit.Assert.*;

import java.io.StringReader;
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

import com.hp.java8.newfeatures.streams.WordCountSeq;
import com.hp.java8.newfeatures.streams.sup.WordCountSeqSup.ReaderSupplier;
import com.hp.java8.util.StopWatch;
import com.hp.java8.util.ThreadMonitor;

public class WordCountSeqSupTest {

    private WordCountSeqSup wc = new WordCountSeqSup();

    private StopWatch s = new StopWatch();

    private ThreadMonitor t = new ThreadMonitor();

    @BeforeClass
    public static void beforeClass() {
        // warm-up
        System.out.println("Starting");
        IntStream.range(0, 10).parallel().forEach(e -> System.out.print('.'));
    }

    @Test
    public void testWordCount() {
        StringReader sr = new StringReader("  Ahoj odkial ides\nA \n ty?");
        wc.initialize(sr);

        assertEquals(1, wc.wordCount("Ahoj"));
    }

    @Test
    public void testSequential() throws Exception {

        s.start();

        Stream<Double> randoms = Stream.generate(Math::random).limit(100000000);
        OptionalDouble average =
                randoms.filter(d -> d < 0.5).map(Math::sqrt).mapToDouble(Double::doubleValue).average();

        System.out.println("RESULT = " + average);

        t.threadDump();

        s.stop();

        System.out.println("*********************************");
        System.out.println("Time elapsed: " + s);
        System.out.println("*********************************");

    }

    @Test
    public void testParallelSlow() throws Exception {

        s.start();

        Stream<Double> randoms = Stream.generate(Math::random).parallel().limit(100000000);
        OptionalDouble average =
                randoms.filter(d -> d < 0.5).map(Math::sqrt).mapToDouble(Double::doubleValue).average();

        System.out.println("RESULT = " + average);

        t.threadDump();

        s.stop();

        System.out.println("*********************************");
        System.out.println("Time elapsed: " + s);
        System.out.println("*********************************");

    }

    @Test
    public void testParallelFastTemp() throws Exception {

        s.start();

        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < 10000000; i++) {
            list.add(i);
        }
        //Here creating a parallel stream
        Stream<Integer> stream = list.parallelStream();
        ConcurrentMap<Integer, Integer> collect = stream.filter(i -> i % 2 == 0).collect(Collectors.toConcurrentMap(i -> i, i -> i));

        s.stop();

        System.out.println("*********************************");
        System.out.println("Time elapsed: " + s);
        System.out.println("*********************************");
    }

    @Test
    public void testParallelFast() throws Exception {

        s.start();

        Stream<Double> randoms = Stream.generate(Math::random).parallel().limit(100000000);

        ConcurrentLinkedQueue<Double> collect =
                randoms.filter(d -> d < 0.5)
                       .map(Math::sqrt)
                       .collect(ConcurrentLinkedQueue<Double>::new, ConcurrentLinkedQueue<Double>::add,
                                ConcurrentLinkedQueue::addAll);

//        System.out.println("RESULT = " + average);


        s.stop();

        t.threadDump();
        
        System.out.println("*********************************");
        System.out.println("Time elapsed: " + s);
        System.out.println("*********************************");

    }

    @Test
    public void testParallelTtut1() throws Exception {
        WordCountSeq.tutSeq();
    }

    @Test
    public void testReader() throws Exception {

        ReaderSupplier rs = new ReaderSupplier(new StringReader("  Ahoj odkial ides\nA \n ty?"));
        String string;
        while ((string = rs.get()) != null) {
            System.out.println("S=" + string);
        }
    }

    @Test
    public void testLambda() throws Exception {
        List<Integer> strLengths =
                Stream.of("1", "22", "333")
                      .map(s -> s.length())
                      .collect(ArrayList::new, (List<Integer> t, Integer u) -> t.add(u), List::addAll);

        System.out.println(strLengths);
    }

}
