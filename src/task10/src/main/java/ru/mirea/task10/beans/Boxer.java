package ru.mirea.task10.beans;


import org.springframework.stereotype.Component;

@Component
public class Boxer implements Fighter {
    @Override
    public void doFight() {
        System.out.println("Boxer punch!");
    }
}
