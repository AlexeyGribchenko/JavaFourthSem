package ru.mirea.task16.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name="user_table")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @SequenceGenerator(name = "user_table_seq", allocationSize = 1)
    @GeneratedValue(generator = "user_table_seq", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "first_name")
    private final String firstName;

    @Column(name = "last_name")
    private final String lastName;

    @Column(name = "middle_name")
    private final String middleName;

    @Column(name = "birth_date")
    private final String birthDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Post> posts;

    public User(String firstName, String lastName, String middleName, String birthDate) {
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
}
