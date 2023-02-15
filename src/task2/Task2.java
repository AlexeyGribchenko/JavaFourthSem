package task2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Task2 {
    public static void main(String[] args) {

        Human h1 = new Human(15, "Islam", "Kushkov", LocalDate.of(1995, 12, 12), 70);
        Human h2 = new Human(20, "Alexey", "Gribchenko", LocalDate.of(2004, 6, 28), 69);
        Human h3 = new Human(25, "Grigory", "Reznikov", LocalDate.of(1899, 2, 6), 85);
        Human h4 = new Human(30, "Anatoly", "Shulik", LocalDate.of(2000, 5, 1), 50);

        ArrayList<Human> humanList = new ArrayList<>();

        humanList.add(h1);
        humanList.add(h2);
        humanList.add(h3);
        humanList.add(h4);

        Stream<Human> humanStream;

        humanStream = humanList.stream();
        humanStream.filter(human -> human.birthDate.compareTo(LocalDate.of(1999, 2, 3)) < 0)
                .forEach(human -> System.out.println(human.firstName + ' ' + human.birthDate));

        System.out.println();
        humanStream = humanList.stream();
        humanStream.forEach(human -> {
            human.weight -= 5;
            System.out.println(human.firstName + ' ' + human.weight);
        });

        humanStream = humanList.stream();
        humanStream.map(human -> human.weight - 5).forEach(System.out::println);

        humanStream = humanList.stream();
        String result =  humanStream.reduce("", (hum1, hum2) -> {
            return hum1 + hum2.lastName + ' ';
        }, (hum1, hum2) -> hum1 + hum2);
        System.out.println("\nConcatenated last names: " + result);

    }
}
