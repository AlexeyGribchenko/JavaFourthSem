package task8.state;

public class DeadState implements HeroState {
    @Override
    public void attack() {
        System.out.println("Hero can`t attack, he died.");
    }

    @Override
    public void move() {
        System.out.println("Hero can`t move, he died.");
    }

    @Override
    public HeroState heal() {
        System.out.println("Hero can`t heal, he died.");
        return new DeadState();
    }

    @Override
    public HeroState takeDamage() {
        System.out.println("The dead body takes some damage.");
        return new DeadState();
    }
}
