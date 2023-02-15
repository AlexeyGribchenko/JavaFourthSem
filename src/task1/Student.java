package task1;

public class Student implements Comparable<Student> {
    int rating;

    Student(int rating) {
        this.rating = rating;
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(this.rating, o.rating);
    }
}
