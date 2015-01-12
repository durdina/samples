package com.personal.durdina.di.knight.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.personal.durdina.di.knight.spring")
@EnableAspectJAutoProxy
public class KnightConfig {

    @Bean
    @Name
    public String nameConstant() {
        return "Bedivere";
    }

    public static void main(String[] args) throws Exception {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(KnightConfig.class)) {
            Knight knight = ctx.getBean(Knight.class);
            knight.embarkOnQuest();
        }
    }
}
