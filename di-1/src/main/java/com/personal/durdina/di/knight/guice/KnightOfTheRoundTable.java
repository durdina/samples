package com.personal.durdina.di.knight.guice;

import java.util.Set;

import com.google.inject.Inject;

public class KnightOfTheRoundTable implements Knight {

    private String name;

    private Quest<?> quest;

    @Inject
    private Set<Knight> knightCompany;

    private Sword sword;

    /** Standard injections - constructor injection (with qualifier) */
    @Inject
    public KnightOfTheRoundTable(@Name String name, Blacksmith<Sword> blacksmith) {
        this.name = name;
        this.sword = blacksmith.make(name);
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
    public boolean attack(Knight challenged) {
        boolean sucessfullyDefended = challenged.defendAttackBy(sword);
        return !sucessfullyDefended;
    }

    @Override
    public boolean defendAttackBy(Weapon weapon) {
        return this.sword.weight() * Math.random() > weapon.weight() * Math.random();
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
