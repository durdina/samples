package com.hp.java8.newfeatures.streams;

import static org.junit.Assert.*;

import java.io.StringReader;

import org.junit.Test;

public class WordCountSeqTest {

    private WordCountSeq wc = new WordCountSeq();

    @Test
    public void testWordCount() throws Exception {
        StringReader sr = new StringReader("  Ahoj odkial ides\nA \n ty?");
        wc.initialize(sr);
        System.out.println(Thread.currentThread().getName() + " - " + Thread.currentThread().getId());

        assertEquals(1, wc.wordCount("Ahoj"));
        Thread.sleep(10);
    }

}
