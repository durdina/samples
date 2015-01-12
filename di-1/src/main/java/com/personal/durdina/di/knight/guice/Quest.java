package com.personal.durdina.di.knight.guice;

import com.google.inject.ImplementedBy;

@ImplementedBy(HolyGrailQuest.class)
public interface Quest {
    public Object embark() throws QuestFailedException;
}
