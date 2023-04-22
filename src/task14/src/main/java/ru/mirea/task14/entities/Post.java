package ru.mirea.task14.entities;

public class Post {

    private String text;
    private String creationDate;

    public Post(String text, String creationDate) {
        this.text = text;
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object obj) {
        return this.text.equalsIgnoreCase(((Post)obj).text) &&
                this.creationDate.equalsIgnoreCase(((Post)obj).creationDate);
    }
}
