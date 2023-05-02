package ru.mirea.task24.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="user_table")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @SequenceGenerator(name = "user_table_seq", allocationSize = 1)
    @GeneratedValue(generator = "user_table_seq", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birth_date")
    private String birthDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Post> posts;

    public User(String firstName, String middleName, String lastName, String birthDate) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public User() {
        this.firstName = "";
        this.lastName = "";
        this.middleName = "";
        this.birthDate = "";
    }

    @Override
    public String toString() {
        return String.format("User{id= %d, first_name= %s, last_name= %s, middle_name= %s, birth_date= %s}",
                id, firstName, lastName, middleName, birthDate);
    }
}
