package com.personal.durdina.di.knight.guice;

import java.util.Set;

import com.google.inject.Inject;

public class KnightOfTheRoundTable implements Knight {

    private String name;
    private Quest<?> quest;

    @Inject
    private Blacksmith<Iron> armor;

    @Inject
    private Set<Knight> knightCompany;

    /** Standard injections - constructor injection (with qualifier) */
    @Inject
    public KnightOfTheRoundTable(@Name String name) {
        this.name = name;
    }

    /** Standard injections - setter injection */
    @Inject
    public void setQuest(Quest<?> quest) {
        this.quest = quest;
    }

    public String getName() {
        return name;
    }

    @Override
    @MinstrelIntercepted
    public Quest<?> embarkOnQuest() throws QuestFailedException {
        quest.embark();
        return quest;
    }

    @Override
    @MinstrelIntercepted
    public void celebrate() {
        System.out.println("Celebration!");

        for (Knight knight : knightCompany) {
            if (knight != this) {
                System.out.println("Sir " + knight.getName() + ", cheers my friend");
            }
        }
    }

}
