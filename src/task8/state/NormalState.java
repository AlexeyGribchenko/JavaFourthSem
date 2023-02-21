package task8.state;

public class NormalState implements HeroState {
    @Override
    public void attack() {
        System.out.println("Strong attack.");
    }

    @Override
    public void move() {
        System.out.println("Running.");
    }

    @Override
    public HeroState heal() {
        System.out.println("Hero is healthy, he doesn`t need any medicine.");
        return new NormalState();
    }

    @Override
    public HeroState takeDamage() {
        System.out.println("Hero takes some damage. New wounds are bleeding..");
        return new WoundedState();
    }
}
