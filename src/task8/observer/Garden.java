package task8.observer;

import java.util.ArrayList;

public class Garden implements Observable {

    ArrayList<Observer> plants = null;

    public Garden() {
        this.plants = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer plant) {
        plants.add(plant);
    }

    @Override
    public void removeObserver(Observer plant) {
        plants.remove(plant);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : plants) {
            System.out.println("Watering the " + ((Plant)o).getName());
            o.update();
        }
        System.out.println();
    }
}
