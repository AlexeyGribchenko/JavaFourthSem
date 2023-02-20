package task6.abstractFabric;

public class Battle {
    Army army;

    public void  prepareArmy(ArmyType type) {
        ArmyFactory armyFactory;

        switch (type) {
            case ELF -> armyFactory = new ElfArmyFactory();
            case HUMAN -> armyFactory = new HumanArmyFactory();
            case ORC -> armyFactory = new OrcArmyFactory();
            default -> {
                System.out.println("Wrong value of race!");
                return;
            }
        }
        army = new Army(armyFactory);
        army.startBattle();
    }
}
