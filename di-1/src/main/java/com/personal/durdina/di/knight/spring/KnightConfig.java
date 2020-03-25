package com.personal.durdina.di.knight.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
@ComponentScan(basePackages = "com.personal.durdina.di.knight.spring")
@EnableAspectJAutoProxy
public class KnightConfig {

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public Axe blackSmithForAxe() { //TODO: Make Axe have injections
        return new Axe();
    }

    // TODO: Solve how to instruct knights which provider to use
//    @Bean
//    @Scope(SCOPE_PROTOTYPE)
//    public Sword blackSmithForSword() {
//        return new Sword();
//    }

    // TODO: 2017-01-10 Use Qualifier (both Config and @Inject) vs Bean(name) on config + Named on @Inject
    @Bean
    public Knight bedivere() {
        return new KnightOfTheRoundTable("Bedivere");
    }

    @Bean
    public Knight lancelot() {
        return new KnightOfTheRoundTable("Lancelot");
    }

    @Bean
    public Set<Knight> knightCompany(@Named("bedivere") Knight bedivere, @Named("lancelot") Knight lancelot) {
        return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(bedivere, lancelot)));
    };

    public static void main(String[] args) throws Exception {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(KnightConfig.class)) {
            Knight knight = ctx.getBean("bedivere", Knight.class);
            Knight enemy = ctx.getBean("lancelot", Knight.class);

            // arm knights
//            knight.arm(injector.getInstance(Key.get(Axe.class)));
//            enemy.arm(injector.getInstance(Key.get(Sword.class)));

            // make them fight
            Quest<?> quest = knight.embarkOnQuest();
            Knight winner = quest.fight(knight, enemy);
            winner.celebrate();
        }
    }

}
