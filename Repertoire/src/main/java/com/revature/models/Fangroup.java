package com.revature.models;

public class Fangroup {

    // reference variables
    private Integer Id;
    private Integer fanId;
    private Integer musicianId;

    // constructors
    public Fangroup() {
    }

    public Fangroup(Integer fanId, Integer musicianIdId) {
        this.fanId = fanId;
        this.musicianId = musicianIdId;
    }

    public Fangroup(Integer Id, Integer fanId, Integer musicianId) {
        this.Id = Id;
        this.fanId = fanId;
        this.musicianId = musicianId;
    }

    public Integer getId() {
        return Id;
    }
    public void setId(Integer Id) { this.Id = Id; }

    public Integer getFanId() {
        return fanId;
    }

    public void setFanId(Integer fanId) { this.fanId = fanId; }

    public Integer getMusicianId() {
        return musicianId;
    }

    public void setMusicianId(Integer musicianId) {
        this.musicianId = musicianId;
    }

    @Override
    public String toString() {
        return "Fangroup{" +
                "Id=" + Id +
                "fanId=" + fanId +
                ", musicianId='" + musicianId + '\'' +
                '}';
    }
}
