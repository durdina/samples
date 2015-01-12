package com.personal.durdina.di.knight.guice;

import com.personal.durdina.di.knight.guice.GrailNotFoundException;

public class HolyGrailQuest implements Quest {
    public HolyGrailQuest() {
    }

    public Object embark() throws GrailNotFoundException {
        System.out.println("Embarking on quest");
        return new HolyGrail();
    }
}
