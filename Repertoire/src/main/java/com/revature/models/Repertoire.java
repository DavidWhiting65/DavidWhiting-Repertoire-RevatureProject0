package com.revature.models;

public class Repertoire {

    // reference variables
    private Integer musicianId;
    private Integer songId;
    private Integer likes;
    private Integer id;

    // constructors
    public Repertoire() {
    }

    public Repertoire(Integer musicianId, Integer songId) {
        this.musicianId = musicianId;
        this.songId = songId;
    }

    public Repertoire(Integer likes, Integer musicianId, Integer songId) {
        this.likes = likes;
        this.musicianId = musicianId;
        this.songId = songId;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getMusicianId() {
        return musicianId;
    }

    public void setMusicianId(Integer musicianId) {
        this.musicianId = musicianId;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer songId) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\n" + id + ": " + musicianId + " - " + songId;

//        return "Repertoire{" +
//                "Id=" + Id +
//                "songId=" + songId +
//                ", musicianId='" + musicianId + '\'' +
//                ", likes=" + likes +
//                '}';
    }
}
