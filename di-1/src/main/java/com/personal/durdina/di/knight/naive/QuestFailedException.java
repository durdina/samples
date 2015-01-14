package com.personal.durdina.di.knight.naive;

@SuppressWarnings("serial")
public class QuestFailedException extends Exception {
    public QuestFailedException() {
    }

    public QuestFailedException(String message) {
        super(message);
    }
}
