package com.personal.durdina.di.knight.naive;

import java.util.Set;

public class HolyGrailQuest implements Quest {

    private Set<String> kings;

    public HolyGrailQuest() {
    }

    public Object embark() throws GrailNotFoundException {
        System.out.println("Embarking on quest in the name of " + kings);
        return new HolyGrail();
    }

}
