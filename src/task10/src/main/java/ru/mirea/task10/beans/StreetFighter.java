package ru.mirea.task10.beans;


import org.springframework.stereotype.Component;

@Component
public class StreetFighter implements Fighter {
    @Override
    public void doFight() {
        System.out.println("Street fighter`s punch!");
    }
}
