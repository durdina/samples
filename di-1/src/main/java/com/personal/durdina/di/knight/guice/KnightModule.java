package com.personal.durdina.di.knight.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;

// TODO mdurdina Feb 11, 2015: add sample case for expressions
// TODO mdurdina Feb 11, 2015: add sample case for having plug-ins
// TODO: add dependencies between modules (Spring can do that, Guice too, but not inside configure())
// TODO mdurdina Mar 28, 2018: for constructor injection the annotations for interceptors (i.e. metrics) have no effect since annotations are not processes
public class KnightModule extends AbstractModule {

    @Override
    public void configure() {

        bindConstant().annotatedWith(Principality.class).to("Kingdom of King Arthur");

        bind(new TypeLiteral<Set<String>>() {}).toInstance(Collections.singleton("Ds"));

        bind(Knight.class).annotatedWith(Bedivere.class).toInstance(new KnightOfTheRoundTable("Bedivere"));
        bind(Knight.class).annotatedWith(Lancelot.class).toInstance(new KnightOfTheRoundTable("Lancelot"));

        bindInterceptor(any(), annotatedWith(MinstrelIntercepted.class), new MinstrelInterceptor());

        // Providers - create new instance for each request using "supplied provider - blacksmith"
        bind(Axe.class).toProvider(new Blacksmith<Axe>(new Axe()));
        bind(Sword.class).toProvider(new Blacksmith<Sword>(new Sword()));

        // Assisted injection // TODO: Clean up payment and others
        install(new FactoryModuleBuilder()
                .implement(Payment.class, RealPayment.class)
                .build(PaymentFactory.class));
    }

    @Provides
    Set<Knight> knightCompany(@Bedivere Knight knight1, @Lancelot Knight knight2) {
        // TODO mdurdina Apr 13, 2015: add guava
        return Collections.unmodifiableSet(new HashSet<Knight>(Arrays.asList(knight1, knight2)));
    };

    public static void main(String[] args) throws Exception {

        Injector injector = Guice.createInjector(new KnightModule());
        Knight knight = injector.getInstance(Key.get(Knight.class, Bedivere.class));
        Knight enemy = injector.getInstance(Key.get(Knight.class, Lancelot.class));

        PaymentFactory paymentFactory = injector.getInstance(Key.get(PaymentFactory.class));
        Payment payment = paymentFactory.create(new Date(), BigDecimal.TEN);
        System.out.println(payment.toString());

        // arm knights
        knight.arm(injector.getInstance(Key.get(Axe.class)));
        enemy.arm(injector.getInstance(Key.get(Sword.class)));
        
        // make them fight
        Quest<?> quest = knight.embarkOnQuest();
        Knight winner = quest.fight(knight, enemy);
        winner.celebrate();
    }
}
