package task7.flyweight;

public class Being {

    private Flyweight flyweight;
    String name;
    int age;

    public Being(String race, String fraction, String name, int age, FlyweightObjectFactory factory) {
        this.name = name;
        this.age = age;
        this.flyweight = factory.getFlyweight(race, fraction);
    }

    @Override
    public String toString() {
        return "Race: " + flyweight.getRace() + " Fraction: " + flyweight.getFraction() +
                " Name: " + name + " Age: " + age;
    }
}
