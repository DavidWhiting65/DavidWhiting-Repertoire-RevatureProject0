package com.revature.views;

import com.revature.models.User;
import com.revature.repositories.UserRepo;
import com.revature.services.*;

import java.util.Scanner;

public class MainMenuView {

    static UserService userServices = new UserService();
    static UserRepo userRepo = new UserRepo();
    // for passing back User Id upon login.
    static int globalUserId = 0;
    // for passing back isFan upon login.
    static boolean globalIsFan = false;

    static boolean globalIsAdmin = false;

    static User globalUser = new User();

    public static void display(){

        //loop the following
        Scanner scanner = new Scanner(System.in);
        Boolean running = true;
        Boolean loggedIn = false;

        while(running) {

            /// need to display a menu
            System.out.println("1) Create account");
            System.out.println("2) Login");
            System.out.println("3) Musician Submenu");
            System.out.println("4) Fan Submenu");
            System.out.println("5) Print All Users (Admin Only)");
            System.out.println("6) Logout");
            System.out.println("0) Exit");

            ///receive input from the user
            String result = scanner.nextLine();

            switch (result){
                case "1": {
                    // create account
                    System.out.println("Create Account");

                    if (globalIsAdmin) {
                        System.out.println("For users only, not admin.");
                        break;
                    }

                    if (loggedIn) {
                        System.out.println("You may not create an account while logged in.");
                        break;
                    }

                    System.out.println("Enter 'M' for musician or 'F' for Fan:");
                    String userType = scanner.nextLine();

                    // input validation for user type
                    if (!(userType.equals("F") || userType.equals("M"))) {
                        System.out.println("You must enter either an 'M' or an 'F'.");
                        System.out.println();
                        break;
                    }

                    if (userType.equals("F")) {
                        globalIsFan = true;
                    }

                    if (userType.equals("M")) {
                        globalIsFan = false;
                    }

                    System.out.println("userType: " + userType);

                    System.out.println("First Name:");
                    String FirstName = scanner.nextLine();

                    System.out.println("Last Name:");
                    String LastName = scanner.nextLine();

                    System.out.println("Username:");
                    String UserName = scanner.nextLine();

                    System.out.println("Password:");
                    String Password = scanner.nextLine();

                    userRepo.add(new User(globalIsFan, FirstName, LastName, UserName, Password));
                    System.out.println(userRepo.getAll().toString());
                    break;
                }
                case "2": {
                    // login
                    System.out.println("Login");

                    if (globalIsAdmin) {
                        System.out.println("For users only, not admin.");
                        break;
                    }

                    if (loggedIn) {
                        System.out.println("You are already logged in.");
                        break;
                    }

                    System.out.println("Please enter your username: ");
                    String username = scanner.nextLine();

                    System.out.println("Please enter your password: ");
                    String password = scanner.nextLine();

                    // We need a login service to check if username and password
                    // match credentials stored in the database

                    globalUser = userServices.login(username, password);

                    if (globalUser == null) {
                        System.out.println("Invalid username or password.");
                        break;
                    }

                    globalUserId = globalUser.id;
                    globalIsFan = globalUser.isFan;

                    System.out.println("globalUser.id: " + globalUserId);
                    System.out.println("globalUser.isFan: " + globalIsFan);

                    if (globalUserId != 0) {
                        System.out.println("Succesfully logged in as: " + globalUserId);
                        if (globalIsFan) {
                            System.out.println("Your are logged in as a fan.");
                        } else {
                            System.out.println("Your are logged in as a musician.");
                        }
                        loggedIn = true;
                    } else {
                        System.out.println("Credentials do not match.");
                    }
                    break;
                }
                case "3": {
                    // Go to musician submenu
                    System.out.println("Musician Submenu");

                    if (globalIsAdmin) {
                        System.out.println("For users only, not admin.");
                        break;
                    }

                    if (loggedIn){
                        if (globalIsFan) {
                            System.out.println("Must be a musician.");
                            System.out.println();
                                break;
                        }
                        MusicianSubMenuView.display(globalUserId);
                    }
                    else { System.out.println("Must log in first.");}
                    break;
                }
                case "4": {
                    // Go to fan submenu
                    System.out.println("Fan Submenu");

                    if (globalIsAdmin) {
                        System.out.println("For users only, not admin.");
                        break;
                    }

                    if (loggedIn){
                        if (!globalIsFan) {
                            System.out.println("Must be a fan.");
                            System.out.println();
                            break;
                        }
                        FanSubMenuView.display(globalUserId);
                    }
                    else { System.out.println("Must log in first.");}
                    break;
                }
                case "5": {
                    // print all users
                    if (!globalIsAdmin) {
                        System.out.println("For admin only.");
                        break;
                    }
                    System.out.println("Print All Users");
                    System.out.println(userRepo.getAll().toString());
                    break;
                }
                case "6": {
                    // Logout
                    System.out.println("Logout");
                    System.out.println();
                    System.out.println();
                    globalUserId = 0;
                    globalIsAdmin = false;
                    globalIsFan = false;
                    running = false;
                    MainMenuView.display();
                    break;
                }
                case "A": {
                    // admin login
                    System.out.println("Admin Login");
                    globalIsAdmin = true;
                    break;
                }
                case "0": {
                    // quit
                    System.out.println("Quit");
                    running = false;
                    break;
                }
                default: {
                    // error
                    System.out.println("Invalid Input");
                }
            }
        }
    }
}
