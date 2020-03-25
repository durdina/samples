package com.personal.durdina.di.knight.spring;

public interface Quest<TROPHY> {

    TROPHY embark() throws QuestFailedException;

    Knight fight(Knight challenger, Knight... challenged);

}
