package com.hp.java8.hi.streamfile;

import static org.junit.Assert.*;

import java.io.StringReader;

import org.junit.Test;

import com.hp.java8.hi.streamfile.WordCountSeq;

public class WordCountSeqTest {

    private WordCountSeq wc = new WordCountSeq();

    @Test
    public void testWordCount() throws Exception {
        StringReader sr = new StringReader("  Ahoj odkial ides\nA \n ty?");
        wc.initialize(sr);
        
        long wordCount = wc.wordCount();
		assertEquals(5, wordCount);
        Thread.sleep(10);
    }

}
