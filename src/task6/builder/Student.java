package task6.builder;

public class Student {

    private String firstName;

    private String secondName;

    private int age;

    private boolean hasScholarship;

    public Student(StudentBuilder studentBuilder) {
        this.firstName = studentBuilder.firstName;
        this.secondName = studentBuilder.secondName;
        this.age = studentBuilder.age;
        this.hasScholarship = studentBuilder.hasScholarship;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return age;
    }

    public boolean isHasScholarship() {
        return hasScholarship;
    }

    @Override
    public String toString() {
        String scholarship = (hasScholarship) ? " has scholarship" : " hasn`t scholarship";
        return firstName + " " + secondName + " age: " + age + scholarship;
    }

    public static class StudentBuilder {

        private String firstName;

        private String secondName;

        private int age;

        private boolean hasScholarship;

        public StudentBuilder(String firstName, int age) {
            this.firstName = firstName;
            this.age = age;
        }

        public StudentBuilder setSecondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public StudentBuilder setHasScholarship(boolean hasScholarship) {
            this.hasScholarship = hasScholarship;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
