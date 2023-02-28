package task9.app.src.main.java.task9;

public class App {
    public String getGreeting() {
        return "Hello, it`s a gradle application!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
