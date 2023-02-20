package task6.abstractFabric;

public class Army {
    private final Infantry infantry;
    private final Archers archers;

    public Army(ArmyFactory armyFactory) {
        infantry = armyFactory.createInfantry();
        archers = armyFactory.createArchers();
    }

    public void startBattle() {
        infantry.attack();
        archers.shoot();
    }
}
