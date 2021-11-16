package com.revature.views;


import com.revature.models.Repertoire;
import com.revature.models.Song;
import com.revature.repositories.FangroupRepo;
import com.revature.repositories.RepertoireRepo;
import com.revature.repositories.SongRepo;

import java.util.Scanner;

public class MusicianSubMenuView {

    static SongRepo songRepo = new SongRepo();
    static RepertoireRepo repertoireRepo = new RepertoireRepo();
    static FangroupRepo fangroupRepo = new FangroupRepo();

    public static void display(int globalUserId) {

        Scanner scanner = new Scanner(System.in);
        Boolean running = true;

        while (running) {
            //display
            System.out.println("1) Add A Song To The SongBase");
            System.out.println("2) Add A Song To Your Repertoire");
            System.out.println("3) Print All Songs From Songbase");
            System.out.println("4) Print All Songs From Your Repertoire");
            System.out.println("5) Print A List Of Your Fans");
            System.out.println("6) Delete A Song From Your Repertoire");
            System.out.println("0) Back");

            //inputs
            String result = scanner.nextLine();

            //do something with inputs
            switch (result){
                case "1":
                    // add song to Songbase
                    System.out.println("Add A Song To The SongBase");

                    System.out.println("Song title:");
                    String songtitle = scanner.nextLine();

                    System.out.println("Author:");
                    String songauthor = scanner.nextLine();

                    songRepo.add(new Song(songtitle, songauthor));
                    System.out.println(songRepo.getAll().toString());

                    System.out.println();
                    System.out.println();

                    break;
                case "2":
                    // add song to your repertoire
                    System.out.println("Add A Song To Your Repertoire");
                    System.out.println(songRepo.getAll());

                    System.out.println(("Musician id: " + globalUserId));
                    Integer musicianId = globalUserId;

                    System.out.println("Song id: ");
                    Integer songId = Integer.valueOf(scanner.nextLine());

                    repertoireRepo.add(new Repertoire(musicianId, songId));
                    //System.out.println(repertoireRepo.getByMusicianId(musicianId).toString());
                    System.out.println(repertoireRepo.getByMusicianId(musicianId));

                    break;
                case "3":
                    // print all songs from the Songbase
                    System.out.println("Print All Songs From Songbase");
                    System.out.println(songRepo.getAll());
                    break;
                case "4":
                    // Print all songs from your repertoire
                    System.out.println("Print All Songs From Your Repertoire");
                    int musiciansId = globalUserId;
                    //System.out.println(repertoireRepo.getByMusicianId(musicianId).toString());
                    System.out.println(repertoireRepo.getByMusicianId(musiciansId));
                    break;
                case "5":
                    // Print all fans from fangroups
                    System.out.println("Print A List Of Your Fans");
                    int musiciansId2 = globalUserId;
                    System.out.println(fangroupRepo.getByMusicianId(musiciansId2).toString());
                    break;
                case "6":
                    // delete a song from your repertoire
                    System.out.println("Delete A Song From Your Repertoire");
                    System.out.println("Enter repertoire Id to delete:");
                    Integer deleteRepertoireId = scanner.nextInt();
                    scanner.nextLine();
                    repertoireRepo.delete(deleteRepertoireId);
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
