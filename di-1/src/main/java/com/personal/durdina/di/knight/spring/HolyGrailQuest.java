package com.personal.durdina.di.knight.spring;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class HolyGrailQuest implements Quest<HolyGrail> {

//    @Inject
    private Set<String> kings;

    public HolyGrail embark() throws GrailNotFoundException {
        System.out.println("Embarking on quest in the name of " + kings);
        return new HolyGrail();
    }

    @Override
    public Knight fight(Knight challenger, Knight... challenged) {
        for (Knight challengedKnight : challenged) {
            boolean won = challenger.attack(challengedKnight);
            if (!won) {
                return challengedKnight;
            }
        }
        return challenger;
    }

}
