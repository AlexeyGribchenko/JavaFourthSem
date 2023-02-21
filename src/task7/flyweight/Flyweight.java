package task7.flyweight;

public class Flyweight {

    private final String race;

    private final String fraction;

    public Flyweight(String race, String fraction) {
        this.race = race;
        this.fraction = fraction;
    }

    public String getRace() {
        return race;
    }

    public String getFraction() {
        return fraction;
    }

}
