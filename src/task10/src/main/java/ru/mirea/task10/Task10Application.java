package ru.mirea.task10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mirea.task10.beans.*;

@SpringBootApplication
public class Task10Application {

    public static void main(String[] args) {
        SpringApplication.run(Task10Application.class, args);

        ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfig.class);

        Fighter fighter = ctx.getBean("getStreetFighter", StreetFighter.class);
        fighter.doFight();

        fighter = ctx.getBean("getBoxer", Boxer.class);
        fighter.doFight();

        fighter = ctx.getBean("getJudoka", Judoka.class);
        fighter.doFight();
    }

}
