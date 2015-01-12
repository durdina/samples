package com.personal.durdina.di.knight.spring;

import javax.inject.Named;

@Named
public class HolyGrailQuest implements Quest {
    public HolyGrailQuest() {
    }

    public Object embark() throws GrailNotFoundException {
        System.out.println("Embarking on quest");
        return new HolyGrail();
    }
}
