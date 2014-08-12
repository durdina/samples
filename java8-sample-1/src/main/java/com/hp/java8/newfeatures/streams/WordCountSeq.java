package com.hp.java8.newfeatures.streams;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import com.hp.java8.util.StopWatch;
import com.hp.java8.util.ThreadMonitor;

public class WordCountSeq {

    private Reader reader;

    private boolean parsed = false;

    public void initialize(Reader reader) {
        this.reader = reader;
        parsed = false;
    }

    public long wordCount(String string) {
        if (!parsed) {
            doParse();
        }

        return 1;
    }

    private void doParse() {

        Stream<String> lines = new BufferedReader(reader).lines();
        Stream<String> words = lines.flatMap(line -> {
            return Stream.of(line.trim().split("\\s+"));
        });

        long count = words.count();
        System.out.println(count);

    }

    public static void main(String[] args) {
        tutSeq();
        tutSeq();
        tutPar();
        tutPar();
    }

    public static void tutSeq() {
        StopWatch s = new StopWatch();
        s.start();

        int[] population = new int[10000000];
        for (int i = 0; i < population.length; i++) {
            population[i] = i;
        }

        IntStream populationStream = Arrays.stream(population);
        OptionalDouble average = populationStream.filter(WordCountSeq::isPrime).average();

        System.out.println(average.orElse(0));

        new ThreadMonitor().threadDump();

        s.stop();

        System.out.println("*********************************");
        System.out.println("Seq Time elapsed: " + s);
        System.out.println("*********************************");
    }

    public static void tutPar() {
        StopWatch s = new StopWatch();
        s.start();

        int[] population = new int[10000000];
        for (int i = 0; i < population.length; i++) {
            population[i] = i;
        }

        IntStream populationStream = Arrays.stream(population).parallel();
        OptionalDouble average = populationStream.filter(WordCountSeq::isPrime).average();

        System.out.println(average.orElse(0));

        new ThreadMonitor().threadDump();

        s.stop();

        System.out.println("*********************************");
        System.out.println("Par Time elapsed: " + s);
        System.out.println("*********************************");
    }

    private static boolean isPrime(long n) {
        return n > 1 && LongStream.rangeClosed(2, (long) Math.sqrt(n)).noneMatch(divisor -> n % divisor == 0);
    }
}
