package com.personal.durdina.di.knight.naive;

import java.util.Comparator;
import java.util.HashSet;

public class KnightNaiveDi {

    private Knight knight;

    private Quest quest;

    public Knight knight() {
        return this.knight;
    }
    
    public Quest quest() {
        return this.quest;
    }

    @SuppressWarnings("serial")
    private void wireComponents() {
        this.quest = new HolyGrailQuest();
        final KnightOfTheRoundTable knight = new KnightOfTheRoundTable("Bedivere");
        knight.setQuest(quest);
        knight.setNobilityComparator(new Comparator<Knight>() {
            public int compare(Knight o1, Knight o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            };
        });
        knight.setKnightCompany(new HashSet<Knight>() {{
            add(knight);
            add(new KnightOfTheRoundTable("Lancelot"));
        }});
        this.knight = knight;
    }

    public static void main(String[] args) throws Exception {

        KnightNaiveDi handCraftedDi = new KnightNaiveDi();
        handCraftedDi.wireComponents();

        Knight knight = handCraftedDi.knight();
        knight.embarkOnQuest();
        knight.celebrate();
    }
    
}
