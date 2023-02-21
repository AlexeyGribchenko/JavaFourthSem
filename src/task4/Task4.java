package task4;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Task4 {
    public static void main(String[] args) {
        int threadsNumber;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of threads:\n> ");
        threadsNumber = scanner.nextInt();
        MyExecutorService service = new MyExecutorService(threadsNumber);
        for (int i = 0; i < threadsNumber*5; i++) {
            service.execute(() -> System.out.println(Thread.currentThread().getName()));
        }
        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("thread 'main' is ended");
    }
}
