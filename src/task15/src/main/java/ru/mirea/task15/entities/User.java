package ru.mirea.task15.entities;


import jakarta.persistence.*;

@Entity
@Table(name="user_table")
public class User {
    @Id
    @SequenceGenerator(name = "user_table_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "user_table_seq", strategy = GenerationType.SEQUENCE)
    private Integer id;

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

    public User() {
        this.firstName = "";
        this.lastName = "";
        this.middleName = "";
        this.birthDate = "";
    }
}
