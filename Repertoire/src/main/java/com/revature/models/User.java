package com.revature.models;

public class User {

    public int id;
    public boolean isFan;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(int id, boolean isFan, String firstName, String lastName) {
        this.id = id;
        this.isFan = isFan;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(boolean isFan, String firstName, String lastName, String username, String password) {
        this.isFan = isFan;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public User(int id, boolean isFan, String firstName, String lastName, String username, String password) {
        this.id = id;
        this.isFan = isFan;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsFan() {
        return isFan;
    }

    public void setIsFan(boolean fan) {
        isFan = fan;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        //return "\n" + id + ": " + isFan + " - " + firstName + " " + lastName;
        return  "\n" + "id=" + id + ", isFan=" + isFan + ", firstName='" + firstName + ", lastName='"
                + lastName + ", username='" + username + ", password='" + password;
    }
}
