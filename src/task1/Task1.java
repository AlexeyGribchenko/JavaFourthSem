package task1;

public class Task1 {
    public static void main(String[] args) {
        Comparator studentComparator = Student::compareTo;

        Student s1 = new Student(50);
        Student s2 = new Student(50);

        System.out.println(studentComparator.compare(s1, s2));
    }
}
