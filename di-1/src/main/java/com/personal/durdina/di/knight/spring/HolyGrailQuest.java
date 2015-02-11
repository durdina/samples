package com.personal.durdina.di.knight.spring;

import java.util.Set;

import javax.inject.Named;

@Named
public class HolyGrailQuest implements Quest {

    private Set<String> kings;

    public HolyGrailQuest() {
    }

    public Object embark() throws GrailNotFoundException {
        System.out.println("Embarking on quest in the name of " + kings);
        return new HolyGrail();
    }

}
