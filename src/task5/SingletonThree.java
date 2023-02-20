package task5;

public class SingletonThree {

    private SingletonThree() {
    }

    private static class SingletonHolder {
        public static final SingletonThree HOLDER_INSTANCE = new SingletonThree();
    }

    public static SingletonThree getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }
}
