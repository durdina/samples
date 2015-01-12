package com.personal.durdina.di.knight.spring;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class KnightOfTheRoundTable implements Knight {

    private String name;

    private Quest quest;

    @Inject
    public KnightOfTheRoundTable(@Name String name, Quest quest) {
        this.name = name;
        this.quest = quest;
    }

    @MinstrelIntercepted
    public Object embarkOnQuest() throws QuestFailedException {
        return quest.embark();
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public String getName() {
        return name;
    }
}
