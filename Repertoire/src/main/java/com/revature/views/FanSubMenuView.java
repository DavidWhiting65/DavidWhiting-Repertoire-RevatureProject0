package com.revature.views;

import com.revature.models.Fangroup;
import com.revature.models.User;
import com.revature.repositories.FangroupRepo;
import com.revature.repositories.UserRepo;

import java.util.Scanner;

public class FanSubMenuView {

    static FangroupRepo fangroupRepo = new FangroupRepo();
    static UserRepo userRepo = new UserRepo();


    public static void display(int globalUserId) {

        Scanner scanner = new Scanner(System.in);
        Boolean running = true;

        while (running) {
            //display
            System.out.println("1) Become A Fan Of A Musician");
            System.out.println("2) Print All Musicians");
            System.out.println("3) Print Your List Of Fanned Musicians");
            System.out.println("0) back");

            //inputs
            String result = scanner.nextLine();

            //do something with inputs
            switch (result){
                case "1":

                    // become a fan of a musician
                    System.out.println("Become A Fan Of A Musician");

                    Integer fanId = globalUserId;
                    System.out.println("Fan ID: " + fanId);

                    System.out.println("Musician ID:");
                    Integer musicianId = Integer.valueOf(scanner.nextLine());

                    // code here to check if input musicianId is not a fan
                    Boolean isFanCheck;
                    User checkUserTypeUser = new User();
                    checkUserTypeUser = userRepo.getById(musicianId);
                    System.out.println("checkUserTypeUser: " + checkUserTypeUser);
                    isFanCheck = checkUserTypeUser.isFan;
                    System.out.println("isFanCheck: " + isFanCheck);
                    if (isFanCheck) {
                        System.out.println("Must input a legitimate Musician User ID.");
                        System.out.println();
                        break;
                    }

                    fangroupRepo.add(new Fangroup(fanId, musicianId));
                    System.out.println("fan added");

                    System.out.println(fangroupRepo.getByFanId(fanId).toString());

                    break;
                case "2":
                    // Print all musicians
                    System.out.println("Print All Musicians");

                    //System.out.println(userRepo.getAllMusicians().toString());
                    System.out.println(userRepo.getAllMusicians());

                    break;
                case "3":
                    // Print all fanned musicians
                    System.out.println("Print Your List Of Fanned Musicians");

                    Integer fanId2 = globalUserId;

                    System.out.println(fangroupRepo.getByFanId(fanId2).toString());

                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Input");
            }

            System.out.println();
            System.out.println();
        }
    }
}
