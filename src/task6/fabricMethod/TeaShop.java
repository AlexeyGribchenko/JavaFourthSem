package task6.fabricMethod;

public class TeaShop {

    public Tea createTea(TeaType type) {

        Tea tea;

        switch (type) {
            case BLACK  -> tea = new BlackTea();
            case GREEN  -> tea = new GreenTea();
            case OOLONG -> tea = new OolongTea();
            default -> tea = new Tea();
        }

        return tea;
    }
}
