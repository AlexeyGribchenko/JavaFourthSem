package task6.abstractFabric;

public class HumanArmyFactory implements ArmyFactory {
    @Override
    public Archers createArchers() {
        return new HumanArchers();
    }

    @Override
    public Infantry createInfantry() {
        return new HumanInfantry();
    }
}
