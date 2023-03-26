package ru.mirea.task10.beans;


import org.springframework.stereotype.Component;

@Component
public class Judoka implements Fighter {
    @Override
    public void doFight() {
        System.out.println("Judoka punch!");
    }
}
