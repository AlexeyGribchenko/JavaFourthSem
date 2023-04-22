package ru.mirea.task15_2.entities;


import javax.persistence.*;

@Entity
@Table(name="post_table")
public class Post {
    @Id
    @SequenceGenerator(name = "post_table_seq", allocationSize = 1)
    @GeneratedValue(generator = "post_table_seq", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "text")
    private final String text;

    @Column(name = "creation_date")
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
