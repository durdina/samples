package com.personal.durdina.di.knight.guice;

@SuppressWarnings("serial")
public class QuestFailedException extends Exception {

    public QuestFailedException() {
    }

    public QuestFailedException(String message) {
        super(message);
    }
}
