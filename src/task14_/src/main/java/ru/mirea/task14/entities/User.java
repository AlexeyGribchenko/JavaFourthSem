package ru.mirea.task14.entities;

public class User {

    private final String firstName;
    private final String lastName;
    private final String middleName;
    private final String birthDate;

    public User(String firstName, String lastName, String middleName, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object obj) {
        return this.firstName.equalsIgnoreCase(((User)obj).firstName) &&
                this.lastName.equalsIgnoreCase(((User)obj).lastName) &&
                this.middleName.equalsIgnoreCase(((User)obj).middleName) &&
                this.birthDate.equalsIgnoreCase(((User)obj).birthDate);
    }
}
