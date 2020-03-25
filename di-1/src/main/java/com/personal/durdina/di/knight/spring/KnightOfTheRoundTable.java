package com.personal.durdina.di.knight.spring;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

public class KnightOfTheRoundTable implements Knight {

    private String name;

    private Quest<?> quest;
    
    @Inject
    private Set<Knight> knightCompany;
    
    @Inject
    private Provider<Weapon> weaponProvider;

    public KnightOfTheRoundTable(String name) {
        this.name = name;
    }

    @Override
    @MinstrelIntercepted
    public Quest<?> embarkOnQuest() throws QuestFailedException {
        quest.embark();
        return quest;
    }

    @Inject
    public void setQuest(Quest<?> quest) {
        this.quest = quest;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean attack(Knight challenged) {
        Weapon myWeapon = weaponProvider.get();
        boolean successfullyDefended = challenged.defendAttackBy(myWeapon);
        return !successfullyDefended;
    }

    @Override
    public boolean defendAttackBy(Weapon weapon) {
        Weapon myWeapon = weaponProvider.get();
        return myWeapon.weight() * Math.random() > weapon.weight() * Math.random();
    }

    @Override
    public void celebrate() {
        System.out.println("Celebration held for our winner " + this.getName() + "!");
        System.out.println("Your company is going to celebrate you:");

        for (Knight knight : knightCompany) {
            if (knight != this) {
                System.out.println("'I " + knight.getName() + " bow before your bravery " + this.getName() + "'");
            }
        }
    }

}
