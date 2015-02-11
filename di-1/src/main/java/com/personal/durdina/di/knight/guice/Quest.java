package com.personal.durdina.di.knight.guice;

import com.google.inject.ImplementedBy;

@ImplementedBy(HolyGrailQuest.class)
public interface Quest<TROPHY> {

    public TROPHY embark() throws QuestFailedException;

    public Knight fight(Knight challenger, Knight challenged);

}
