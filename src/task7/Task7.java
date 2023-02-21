package task7;

import task7.flyweight.Being;
import task7.flyweight.FlyweightObjectFactory;
import task7.proxy.DatabaseInterface;
import task7.proxy.ProductDatabaseProxy;

public class Task7 {
    public static void main(String[] args) {
        FlyweightObjectFactory factory = new FlyweightObjectFactory();
        Being[] beings = {
                new Being("human", "Alliance", "Victor", 20, factory),
                new Being("human", "Alliance", "Alexey", 19, factory),
                new Being("orc", "Horde", "Shurguldur", 43, factory),
                new Being("elf", "Horde", "Lafael", 392, factory)
        };
        System.out.println("All flyweights:");
        for (Being b : beings) {
            System.out.println(b);
        }
        factory.printAllFlyweights();

        System.out.println();
        DatabaseInterface database = new ProductDatabaseProxy();
        System.out.println("First product: " + database.getProduct(1));
        System.out.println("Second product: " + database.getProduct(3));
        System.out.println("Third product: " + database.getProduct(5));
        System.out.println("All products:");
        for (String p : database.getProductTable()) {
            System.out.println("\t" + p);
        }
    }
}
