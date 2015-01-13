package com.personal.durdina.di.knight.guice;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

import com.google.inject.Inject;

public class KnightOfTheRoundTable implements Knight {

    private String name;
    private Quest quest;
    
    @Inject
    private Set<Knight> friendlyKnights;
    
    @Inject
    private Comparator<Knight> nobilityComparator;
    
    /** Standard injections - constructor injection (with qualifier) */
    @Inject
    public KnightOfTheRoundTable(@Name String name) {
        this.name = name;
    }

    @MinstrelIntercepted
    public Object embarkOnQuest() throws QuestFailedException {
        
        Knight noblestKnight = Collections.max(friendlyKnights, nobilityComparator);
        if (this == noblestKnight) {
            return quest.embark();
        } else {
            System.out.println("I " + name + " bow before my noble knight " + noblestKnight.getName());
            return null;
        }
    }

    /** Standard injections - setter injection */
    @Inject
    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public String getName() {
        return name;
    }
}
