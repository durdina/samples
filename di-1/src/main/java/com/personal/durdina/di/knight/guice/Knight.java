package com.personal.durdina.di.knight.guice;

public interface Knight {

    Quest<?> embarkOnQuest() throws QuestFailedException;

    String getName();

    void celebrate();

    boolean attack(Knight challenged);

    boolean defendAttackBy(Weapon weapon);

}
