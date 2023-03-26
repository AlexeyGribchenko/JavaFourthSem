package ru.mirea.task10.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class BeanConfig {

    @Bean("getStreetFighter")
    public StreetFighter getStreetFighter() {
        return new StreetFighter();
    }

    @Bean("getBoxer")
    public Boxer getBoxer() {
        return new Boxer();
    }

    @Bean("getJudoka")
    public Judoka getJudoka() {
        return new Judoka();
    }
}
