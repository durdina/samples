package com.personal.durdina.di.knight.spring;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.personal.durdina.di.knight.spring")
@EnableAspectJAutoProxy
public class KnightConfig {

    // TODO: 2017-01-10 Use Qualifier (both Config and @Inject) vs Bean(name) on config + Named on @Inject

    @Bean
    @Name
    public String nameConstant() {
        return "Bedivere";
    }

    @SuppressWarnings("serial")
    @Bean
    public Set<Knight> knightCompany(/* final Knight knight */) {
        return new HashSet<Knight>() {
            {
                // add(knight);
                add(new KnightOfTheRoundTable("Lancelot"));
            }
        };
    };

    @Bean
    public Comparator<Knight> knightComparator() {
        return new Comparator<Knight>() {
            @Override
            public int compare(Knight o1, Knight o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        };
    }

    public static void main(String[] args) throws Exception {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(KnightConfig.class)) {
            Knight knight = ctx.getBean(Knight.class);
            knight.embarkOnQuest();
            knight.celebrate();
        }
    }
}
