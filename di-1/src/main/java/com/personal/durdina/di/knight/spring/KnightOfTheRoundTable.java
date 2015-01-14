package com.personal.durdina.di.knight.spring;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class KnightOfTheRoundTable implements Knight {

    private String name;

    private Quest quest;
    
    @Inject
    private Set<Knight> knightCompany;
    
    @Inject
    private Comparator<Knight> nobilityComparator;

    /** Standard injections of instances (by type) - constructor injection (with qualifier) */
    @Inject
    public KnightOfTheRoundTable(@Name String name) {
        this.name = name;
    }

    @Override
    @MinstrelIntercepted
    public Object embarkOnQuest() throws QuestFailedException {

        Knight noblestKnight = Collections.max(knightCompany, nobilityComparator);
        if (this == noblestKnight) {
            return quest.embark();
        } else {
            System.out.println("I " + name + " bow before my noble knight Sir " + noblestKnight.getName() + ", thou shall start the quest");
            return null;
        }
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

    @Inject
    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public String getName() {
        return name;
    }
}
