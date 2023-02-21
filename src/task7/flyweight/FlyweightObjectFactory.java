package task7.flyweight;

import java.util.Hashtable;

public class FlyweightObjectFactory {
    private final Hashtable<String, Flyweight> flyweightHashtable;

    public FlyweightObjectFactory() {
        this.flyweightHashtable = new Hashtable<>();
    }

    public Flyweight getFlyweight(String race, String fraction) {
        Flyweight flyweight;
        String key = race + " " + fraction;
        if (flyweightHashtable.containsKey(key)) {
            flyweight = flyweightHashtable.get(key);
        } else {
            flyweight = new Flyweight(race, fraction);
            flyweightHashtable.put(key, flyweight);
        }
        return flyweight;
    }

    public void printAllFlyweights() {
        for (Flyweight f : flyweightHashtable.values()) {
            System.out.println(f.getRace() + " " + f.getFraction());
        }
    }
}
