package task8.state;

public interface HeroState {
    void attack();
    void move();
    HeroState heal();
    HeroState takeDamage();
}
