package com.personal.durdina.di.knight.naive;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

public class KnightOfTheRoundTable implements Knight {

    private String name;
    
    private Quest quest;
    
    private Set<Knight> knightCompany;
    
    private Comparator<Knight> nobilityComparator;
    
    /** Standard injections - constructor injection (with qualifier) */
    public KnightOfTheRoundTable(String name) {
        this.name = name;
    }

    @Override
    public Object embarkOnQuest() throws QuestFailedException {
        
        System.out.println("La la la, Sir " + this.getName() + " is so brave!");
        
        Knight noblestKnight = Collections.max(knightCompany, nobilityComparator);
        if (this == noblestKnight) {
            return quest.embark();
        } else {
            System.out.println("I " + name + " bow before my noble knight Sir " + noblestKnight.getName() + ", thou shall start the quest");
            return null;
        }
    }
    
    @Override
    public void celebrate() {
        System.out.println("La la la, Sir " + this.getName() + " is so brave!");
        
        System.out.println("Celebration!");
        
        for (Knight knight : knightCompany) {
            if (knight != this) {
                System.out.println(knight.getName() + ", cheers my friend");
            }
        }
    }

    /** Standard injections - setter injection */
    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public String getName() {
        return name;
    }

    public void setKnightCompany(Set<Knight> knightCompany) {
        this.knightCompany = knightCompany;
    }

    public void setNobilityComparator(Comparator<Knight> nobilityComparator) {
        this.nobilityComparator = nobilityComparator;
    }
}
