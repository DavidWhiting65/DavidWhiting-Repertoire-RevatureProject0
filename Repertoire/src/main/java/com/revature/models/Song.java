package com.revature.models;

public class Song {

    // reference variables
    private Integer id;
    private String title;
    private String author;

    // constructors
    public Song() {
    }

    public Song(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Song(Integer id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "\n" + id + ": " + title + " - " + author;
//        return "Song{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", author=" + author +
//                "}";
    }
}
