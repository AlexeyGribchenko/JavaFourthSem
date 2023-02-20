package task5;

public class SingletonFour {
    private static volatile SingletonFour INSTANCE;

    public static SingletonFour getInstance() {
        SingletonFour localInstance = INSTANCE;
        if (localInstance == null) {
            synchronized (SingletonFour.class) {
                localInstance = INSTANCE;
                if (localInstance == null) {
                    INSTANCE = localInstance = new SingletonFour();
                }
            }
        }
        return localInstance;
    }
}