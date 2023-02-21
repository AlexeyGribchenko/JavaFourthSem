package task8;

import task8.observer.Garden;
import task8.observer.Plant;
import task8.state.Hero;

public class Task8 {
    public static void main(String[] args) {
        Garden garden = new Garden();
        new Plant("Rose", garden);
        new Plant("Tulip", garden);
        garden.notifyObservers();
        new Plant("Dandelion", garden);
        garden.notifyObservers();
        new Plant("Chrysanthemum", garden);
        garden.notifyObservers();

        Hero hero = new Hero();
        hero.move();
        hero.attack();
        hero.takeDamage();
        hero.move();
        hero.attack();
        hero.heal();
        hero.takeDamage();
        hero.takeDamage();
        hero.move();
        hero.attack();
        hero.heal();
    }
}
