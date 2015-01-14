package com.personal.durdina.di.knight.naive;

public interface Knight {
    public Object embarkOnQuest() throws QuestFailedException;

    public String getName();

    public void celebrate();
}
