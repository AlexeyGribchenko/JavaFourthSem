package ru.mirea.task16.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name="post_table")
public class Post {
    @Id
    @SequenceGenerator(name = "post_table_seq", allocationSize = 1)
    @GeneratedValue(generator = "post_table_seq", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "text")
    private final String text;

    @Column(name = "creation_date")
    private final String creationDate;

    public Post(String text, String creationDate, User user) {
        this.text = text;
        this.creationDate = creationDate;
        this.user = user;
    }

    public Post() {
        this.text = "";
        this.creationDate = "";
        this.user = null;
    }
}
