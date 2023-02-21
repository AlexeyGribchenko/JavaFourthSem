package task8.state;

public class Hero implements HeroState {
    private HeroState state;

    public Hero() {
        state = new NormalState();
    }
    @Override
    public void attack() {
        state.attack();
    }

    @Override
    public void move() {
        state.move();
    }

    @Override
    public HeroState heal() {
        state = state.heal();
        return state;
    }

    @Override
    public HeroState takeDamage() {
        state = state.takeDamage();
        return state;
    }
}
