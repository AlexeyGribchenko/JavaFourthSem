package task5;

public class SingletonTwo {

    private static final SingletonTwo INSTANCE = new SingletonTwo();

    private SingletonTwo() {
    }

    public static SingletonTwo getInstance() {
        return INSTANCE;
    }

    public static void sayHello() {
        System.out.println("Hello i`m the second singleton.");
    }
}
