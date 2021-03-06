package com.personal.durdina.di.knight.guice;

import java.util.Set;

import com.google.inject.Inject;

public class KnightOfTheRoundTable implements Knight {

    private String name;
    
    @Inject
    @Principality
    private String principality;

    @Inject
    private Set<Knight> knightCompany;

    private Quest<?> quest;
    
    private Weapon weapon;

    /** Standard injections - constructor injection (with qualifier) */
    @Inject
    public KnightOfTheRoundTable(String name) {
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
    public void arm(Weapon weapon) {
        this.weapon = weapon;
    }
    
    @Override
    @MinstrelIntercepted
    public Quest<?> embarkOnQuest() throws QuestFailedException {
        quest.embark();
        return quest;
    }

    @Override
    public boolean attack(Knight challenged) {
        Weapon myWeapon = this.weapon;
        boolean successfullyDefended = challenged.defendAttackBy(myWeapon);
        return !successfullyDefended;
    }

    @Override
    public boolean defendAttackBy(Weapon weapon) {
        Weapon myWeapon = this.weapon;
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
