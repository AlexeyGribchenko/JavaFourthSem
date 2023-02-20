package task6;

import task6.abstractFabric.ArmyType;
import task6.abstractFabric.Battle;
import task6.builder.Student;
import task6.fabricMethod.Tea;
import task6.fabricMethod.TeaShop;
import task6.fabricMethod.TeaType;
import task6.prototype.Ball;

public class Task6 {
    public static void main(String[] args) {
        System.out.println("Testing fabric method:");
        TeaShop teaShop = new TeaShop();
        Tea black_tea = teaShop.createTea(TeaType.BLACK);
        Tea oolong_tea = teaShop.createTea(TeaType.OOLONG);
        black_tea.brew();
        oolong_tea.brew();

        System.out.println();
        new Battle().prepareArmy(ArmyType.ELF);
        new Battle().prepareArmy(ArmyType.HUMAN);
        new Battle().prepareArmy(ArmyType.ORC);

        System.out.println();
        Student student1 = new Student.StudentBuilder("Alexey", 19).setHasScholarship(true).setSecondName("Gribchenko").build();
        Student student2 = new Student.StudentBuilder("Dmitry", 18).build();
        System.out.println(student1);
        System.out.println(student2);

        System.out.println();
        Ball originalBall = new Ball("Red", 20);
        Ball copyBall = (Ball)originalBall.copy();
        System.out.println(originalBall);
        System.out.println(copyBall);
    }
}
