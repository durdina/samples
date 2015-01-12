package com.personal.durdina.di.knight.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class KnightConfig {
    @Bean
    public Knight knight() {
        KnightOfTheRoundTable knight = new KnightOfTheRoundTable("Bedivere");
        knight.setQuest(quest());
        return knight;
    }

    @Bean
    public Quest quest() {
        return new HolyGrailQuest();
    }
    
    @Bean
    public MinstrelAspect minstrelAspect() {
        return new MinstrelAspect();
    }

    public static void main(String[] args) throws Exception {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(KnightConfig.class)) {
            Knight knight = ctx.getBean(Knight.class);
            knight.embarkOnQuest();
        }
    }
}
