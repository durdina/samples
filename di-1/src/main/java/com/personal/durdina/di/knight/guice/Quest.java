package com.personal.durdina.di.knight.guice;

import com.google.inject.ImplementedBy;

@ImplementedBy(HolyGrailQuest.class)
public interface Quest<TROPHY> {

    TROPHY embark() throws QuestFailedException;

    Knight fight(Knight challenger, Knight... challenged);

}
