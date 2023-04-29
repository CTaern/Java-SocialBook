package Midterm;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class MySocialBook{

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        User user = new User();     //To use methods in User class
        Posts post = new Posts();   // To use methods in Post class

        String[] inparguments;      // Using this I take input from command line to take command and user input
        inparguments = args;
        user.getUserList();         // to create first lists and get user.txt

        Scanner sc = new Scanner(new File(inparguments[1]));    //Using scanner i read lines in command.txt
        while(sc.hasNext()) {

            String line = sc.nextLine();
            String[] sp = line.split("\\s+", 2);        //with splitting lines I tried to get each item
            if(sp[0].equals("ADDUSER")){
                String[] sps = sp[1].split("\\s+", 5);
                user.ADDUSER(sps[0], sps[1], sps[2], sps[3], sps[4]);
                System.out.println("-----------------------");
                System.out.println("Command: ADDUSER "+ sps[0]+" "+sps[1]+" "+sps[2]+" "+sps[3]+" "+sps[4]);
                System.out.println(sps[0] + " has been successfully added.");
            }else if (sp[0].equals("REMOVEUSER")) {
                user.REMOVEUSER(Integer.parseInt(sp[1]));
                System.out.println("-----------------------");
                System.out.println("Command: REMOVEUSER "+ sp[1]);
                System.out.println("User has been successfully removed.");
            }else if (sp[0].equals("SIGNIN")){
                String[] sps = sp[1].split("\\s+",2 );
                System.out.println("-----------------------");
                System.out.println("Command: SIGNIN " + sp[1]);
                if(user.SIGNIN(sps[0], sps[1])) {
                    System.out.println("You have successfully signed in.");
                }
                else
                    System.out.println("Invalid username or password! Please try again.");
            }else if (sp[0].equals("LISTUSERS")){
                System.out.println("-----------------------");
                System.out.println("Command: LISTUSERS");
                user.LISTUSERS();
            }else if (sp[0].equals("UPDATEPROFILE")){
                String[] sps = sp[1].split("\\s+", 3);
                System.out.println("------------------------");
                System.out.println("Command: UPDATEPROFILE " + sps[0]+" "+sps[1]+" "+sps[2]);
                user.UPDATEPROFILE(sps[0], sps[1], sps[2]);
            }else if (sp[0].equals("CHPASS")){
                String[] sps = sp[1].split("\\s+", 2);
                System.out.println("------------------------");
                System.out.println("Command: CHPASS " + sps[0] + " "+ sps[1]);
                user.CHPASS(sps[0], sps[1]);
            }else if (sp[0].equals("ADDFRIEND")){
                System.out.println("------------------------");
                System.out.println("Command: ADDFRIEND " + sp[1]);
                user.ADDFRIEND(sp[1]);
            }else if (sp[0].equals("REMOVEFRIEND")){
                System.out.println("------------------------");
                System.out.println("Command: REMOVEFRIEND " +sp[1]);
                user.REMOVEFRIEND(sp[1]);
            }else if (sp[0].equals("BLOCK")){
                System.out.println("------------------------");
                System.out.println("Command: BLOCK " + sp[1]);
                user.BLOCK(sp[1]);
            }else if (sp[0].equals("UNBLOCK")){
                System.out.println("------------------------");
                System.out.println("Command: UNBLOCK " + sp[1]);
                user.UNBLOCK(sp[1]);
            }else if (sp[0].equals("LISTFRIENDS")){
                System.out.println("------------------------");
                System.out.println("Command: LISTFRIENDS");
                user.LISTFRIENDS();
            }else if (sp[0].equals("SHOWBLOCKEDFRIENDS")){
                System.out.println("------------------------");
                System.out.println("Command: SHOWBLOCKEDFRIENDS");
                user.SHOWBLOCKEDFRIENDS();
            }else if(sp[0].equals("SHOWBLOCKEDUSERS")){
                System.out.println("------------------------");
                System.out.println("Command: SHOWBLOCKEDUSERS");
                user.SHOWBLOCKEDUSERS();
            }else if (sp[0].equals("ADDPOST-TEXT")){
                System.out.println("------------------------");
                System.out.println("Command: ADDPOST-TEXT");
                String[] sps = sp[1].split("\\s+", sp[1].length());
                post.ADDTEXTPOST(sps[0] +" "+sps[1]+" "+sps[2]+" "+sps[3]+" "+sps[4]+" "+sps[5], sps[sps.length-1]);
                post.gettLatitude(Double.parseDouble(sps[sps.length-2]));
                post.gettLongitude(Double.parseDouble(sps[sps.length-3]));
            }else if (sp[0].equals("ADDPOST-IMAGE")){
                System.out.println("------------------------");
                System.out.println("Command: ADDPOST-IMAGE");
                String[] sps = sp[1].split("\\s+", sp[1].length());
                post.ADDIMAGEPOST(sps[0]+" "+sps[1]+" "+sps[2]+" "+sps[3]+" "+sps[4]+" "+sps[5], sps[sps.length-3]);
                post.getiLongitude(Double.parseDouble(sps[sps.length-5]));
                post.getiLatitude(Double.parseDouble(sps[sps.length-4]));
                post.getFilepath(sps[(sps.length-2)]);
                post.getResolution(sps[sps.length-1]);
            }else if (sp[0].equals("ADDPOST-VIDEO")){
                System.out.println("------------------------");
                System.out.println("Command: ADDPOST-VIDEO");
                String[] sps = sp[1].split("\\s+", sp[1].length());
                post.ADDVIDEOPOST(sps[0]+" "+sps[1]+" "+sps[2]+" "+sps[3]+" "+sps[4]+" "+sps[5], sps[sps.length-3]);
                post.getDuration(sps[sps.length-1]);
                post.getvFilepath(sps[sps.length-2]);
                post.getvLatitude(Double.parseDouble(sps[sps.length-4]));
                post.getvLongitude(Double.parseDouble(sps[sps.length-5]));
            }else if (sp[0].equals("SHOWPOSTS")){
                System.out.println("------------------------");
                System.out.println("Command: SHOWPOSTS "+ sp[1]);
                post.SHOWPOSTS(sp[1]);
            }else if (sp[0].equals("SIGNOUT")){
            System.out.println("------------------------");
            System.out.println("Command: SIGNOUT");
            user.SIGNOUT();
            System.out.println("You have successfully signed out.");
            }
        }
    }
}