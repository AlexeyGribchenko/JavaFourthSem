package task8.observer;

public class Plant implements Observer {

    private String name;
    private int height;

    public Plant(String name) {
        this.name = name;
        this.height = 0;
    }

    public Plant(String name, Observable garden) {
        this.name = name;
        this.height = 0;
        garden.registerObserver(this);
    }

    @Override
    public void update() {
        height++;
        System.out.println("Growing by 1 sm, now is " + height);
    }

    public String getName() {
        return name;
    }
}
