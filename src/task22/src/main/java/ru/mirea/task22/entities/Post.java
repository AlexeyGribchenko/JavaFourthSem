package ru.mirea.task22.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
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

    @Override
    public String toString() {
        return String.format("Post{id= %d, text= %s, user_id= %d, creation_date= %s}",
                id, text, user.getId(), creationDate);
    }
}
