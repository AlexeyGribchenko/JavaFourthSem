package ru.mirea.task15.entities;


import jakarta.persistence.*;

@Entity
@Table(name="post_table")
public class Post {
    @Id
    @SequenceGenerator(name = "post_table_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "post_table_seq", strategy = GenerationType.AUTO)
    private Integer id;
    private final String text;
    private final String creationDate;

    public Post(String text, String creationDate) {
        this.text = text;
        this.creationDate = creationDate;
    }

    public Post() {
        this.text = "";
        this.creationDate = "";
    }
}
