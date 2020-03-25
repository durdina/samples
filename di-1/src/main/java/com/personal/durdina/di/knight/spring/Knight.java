package com.personal.durdina.di.knight.spring;

public interface Knight {

    Quest<?> embarkOnQuest() throws QuestFailedException;

    String getName();

    boolean attack(Knight challenged);

    boolean defendAttackBy(Weapon weapon);

    void celebrate();

}
