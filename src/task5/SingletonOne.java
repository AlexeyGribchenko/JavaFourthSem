package task5;

public class SingletonOne {

    private static SingletonOne INSTANCE;

    private SingletonOne() {
    }

    public static synchronized SingletonOne getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonOne();
        }
        return INSTANCE;
    }

    public static void sayHello() {
        System.out.println();
    }
}
