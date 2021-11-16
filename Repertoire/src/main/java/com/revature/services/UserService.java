package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserRepo;

public class UserService {

    UserRepo userRepo = new UserRepo();

    //make int to return globalUserId
    //public boolean login(String username, String password) {
    public User login(String username, String password) {
        //int returnId = 0;
        // in order to log in a user, we will need username and password
        // that information is stored in our database
        // the repository layer needs to take care of this

        User u = userRepo.getByUsername(username); //more of the Sole Responsibility Principle at work

        // check o make sure User object is not null
        if (u != null) {
            // now check to make sure it matches
            if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                return u;

            }
        }


        return null;
    }



}
