package task6.abstractFabric;

public class ElfArmyFactory implements ArmyFactory {
    @Override
    public Archers createArchers() {
        return new ElfArchers();
    }

    @Override
    public Infantry createInfantry() {
        return new ElfInfantry();
    }
}
