package com.personal.durdina.di.knight.guice;

public interface Knight {
    public Object embarkOnQuest() throws QuestFailedException;

    public String getName();
}
