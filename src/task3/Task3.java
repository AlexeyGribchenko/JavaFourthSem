package task3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Task3 {
    public static void main(String[] args) {

        MySet<Integer> mySet = new MySet<>(new HashSet<>());
        Set<Integer> set = new HashSet<>();

        MyMap<Integer, Integer> myMap = new MyMap<>(new HashMap<>());
        Map<Integer, Integer> map = new HashMap<>();

        Thread thread1 = new Thread(() -> {

            for (int i = 0; i < 10000; i++) {
                mySet.add(i);
                set.add(i);
            }

            for (int i = 0; i < 10000; i++) {
                myMap.put(i, i);
                map.put(i, i);
            }

        });
        Thread thread2 = new Thread(() -> {

            for (int i = 0; i < 10000; i++) {
                mySet.add(i);
                set.add(i);
            }

            for (int i = 0; i < 10000; i++) {
                myMap.put(i, i);
                map.put(i, i);
            }

        });
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("MyMap size: " + myMap.size());
        System.out.println("HashMap size: " + map.size());

        System.out.println("MySet size: " + mySet.size());
        System.out.println("Set size: " + set.size());
    }
}
