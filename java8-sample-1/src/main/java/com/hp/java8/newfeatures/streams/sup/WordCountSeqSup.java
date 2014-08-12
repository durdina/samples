package com.hp.java8.newfeatures.streams.sup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class WordCountSeqSup {

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
        
        long count = Stream.generate(new ReaderSupplier(this.reader)).limit(10).count();
        System.out.println(count);
        
    }

    static class ReaderSupplier implements Supplier<String> {

        private Reader r;

        private char[] cbuf = new char[10];

        private int read = 0, start = 0, i = 0;

        private String leftOver = "";

        public ReaderSupplier(Reader r) {
            this.r = new BufferedReader(r);
        }

        @Override
        public String get() {

            if (read == -1) {
                return null;
            }

            try {

                while (i < read || (read = this.r.read(cbuf)) != -1) {

                    while (i < read) {
                        if (!leftOver.isEmpty() && i < read && Character.isWhitespace(cbuf[i])) {
                            String string = leftOver;
                            leftOver = "";
                            System.out.println(string);
                            return string;
                        }
                        while (i < read && Character.isWhitespace(cbuf[i])) {
                            start++;
                            i++;
                        }
                        while (i < read && !Character.isWhitespace(cbuf[i])) {
                            i++;
                        }
                        if (i - start > 0) {
                            String string = new String(cbuf, start, i - start);
                            if (i < read) {
                                string = leftOver + string;
                                start = i;
                                leftOver = "";
                                System.out.println(string);
                                return string;
                            } else {
                                // save because some other might come
                                leftOver += string;
                            }
                        }
                    }
                    
                    i = 0; start = 0; read = 0;
                }
                System.out.println(leftOver);
                return leftOver;

            } catch (IOException e) {
                throw new RuntimeException("Unexpected error occurred when parsing input stream", e);
            }
        }

    };

}
