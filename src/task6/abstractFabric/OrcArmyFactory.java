package task6.abstractFabric;

public class OrcArmyFactory implements ArmyFactory {
    @Override
    public Archers createArchers() {
        return new OrcArchers();
    }

    @Override
    public Infantry createInfantry() {
        return new OrcInfantry();
    }
}
