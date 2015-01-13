package com.personal.durdina.di.knight.guice;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;

@SuppressWarnings("serial")
public class KnightModule extends AbstractModule {
    public void configure() {

        bindConstant().annotatedWith(Name.class).to("Bedivere");

        bind(new TypeLiteral<Comparator<Knight>>() {
        }).toInstance(new Comparator<Knight>() {
            @Override
            public int compare(Knight o1, Knight o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });

        bind(Knight.class).to(KnightOfTheRoundTable.class).in(Scopes.SINGLETON);

        bindInterceptor(any(), annotatedWith(MinstrelIntercepted.class), new MinstrelInterceptor());
    }

    @Inject
    @Provides
    Set<Knight> friendlyKnights(final Knight knight) {
        return new HashSet<Knight>() {{
            add(knight);
            add(new KnightOfTheRoundTable("Lancelot"));
        }};
    };

    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new KnightModule());
        Knight knight = injector.getInstance(Knight.class);
        knight.embarkOnQuest();
    }
}
