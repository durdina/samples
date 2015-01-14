package com.personal.durdina.di.knight.spring;

public interface Knight {
    public Object embarkOnQuest() throws QuestFailedException;

    public String getName();

    public void celebrate();
}
