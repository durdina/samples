package com.personal.durdina.di.knight.spring;

public interface Knight {

    Object embarkOnQuest() throws QuestFailedException;

    String getName();

    void celebrate();

}
