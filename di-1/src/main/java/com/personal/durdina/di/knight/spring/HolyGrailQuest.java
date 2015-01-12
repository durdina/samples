package com.personal.durdina.di.knight.spring;

import com.personal.durdina.di.knight.spring.GrailNotFoundException;

public class HolyGrailQuest implements Quest {
    public HolyGrailQuest() {
    }

    public Object embark() throws GrailNotFoundException {
        // do whatever it means to embark on a quest
        System.out.println("Embarking on quest");
        return new HolyGrail();
    }
}
