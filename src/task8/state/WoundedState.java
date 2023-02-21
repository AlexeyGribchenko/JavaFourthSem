package task8.state;

public class WoundedState implements HeroState {
    @Override
    public void attack() {
        System.out.println("Weak attack..");
    }

    @Override
    public void move() {
        System.out.println("Hobbling..");
    }

    @Override
    public HeroState heal() {
        System.out.println("Healing wounds, hero feels good!");
        return new NormalState();
    }

    @Override
    public HeroState takeDamage() {
        System.out.println("Hero dies...");
        return new DeadState();
    }
}
