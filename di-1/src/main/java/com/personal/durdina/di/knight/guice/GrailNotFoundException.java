package com.personal.durdina.di.knight.guice;

@SuppressWarnings("serial")
public class GrailNotFoundException extends QuestFailedException {
    public GrailNotFoundException() {
    }

    public GrailNotFoundException(String message) {
        super(message);
    }
}
