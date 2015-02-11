package com.personal.durdina.di.knight.guice;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;

// TODO mdurdina Feb 11, 2015: add sample case for expressions
// TODO mdurdina Feb 11, 2015: add sample case for having plug-ins
@SuppressWarnings("serial")
public class KnightModule extends AbstractModule {

    @Override
    public void configure() {

        bindConstant().annotatedWith(Name.class).to("Bedivere");

        bind(new TypeLiteral<Set<String>>() {}).toInstance(Collections.singleton("Ds"));

        bind(Knight.class).to(KnightOfTheRoundTable.class).in(Scopes.SINGLETON);

        bindInterceptor(any(), annotatedWith(MinstrelIntercepted.class), new MinstrelInterceptor());

    }

    @Inject
    @Provides
    Set<Knight> knightCompany(final Knight knight) {
        return new HashSet<Knight>() {

            {
                add(knight);
                add(new KnightOfTheRoundTable("Lancelot"));
            }
        };
    };

    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new KnightModule());
        Knight knight = injector.getInstance(Knight.class);
        // TODO mdurdina Feb 11, 2015: use parameter
        Knight enemy = injector.getInstance(Knight.class);

        Quest<?> quest = knight.embarkOnQuest();
        Knight winner = quest.fight(knight, enemy);
        winner.celebrate();
        
//        if (quest.isCompleted()) {
//            winner.takeTrophy(quest.getTrophy());
//        }

    }
}
