package Midterm;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class User {



    private ArrayList<Integer> UserIDs = new ArrayList<>();         //First I created lists that I will use
    private ArrayList<String> UserList = new ArrayList<>();
    private ArrayList<String> Usernames = new ArrayList<>();
    private ArrayList<String> UserNicknames = new ArrayList<>();
    private ArrayList<String> Userpasswords = new ArrayList<>();
    private ArrayList<String> UserBOD = new ArrayList<>();
    private ArrayList<Date> UserBODs = new ArrayList<>();
    private ArrayList<String> UserGSI = new ArrayList<>();
    public ArrayList<Integer> CurrentlyUser = new ArrayList<>();
    private ArrayList<String> FriendLists = new ArrayList<>();
    private ArrayList<String> BlockedLists = new ArrayList<>();



    public void getUserList() throws FileNotFoundException, ParseException {  //Using this method I get users.txt inputs

        Scanner sc = new Scanner(new File("users.txt"));    //Using this scanner I got
        while (sc.hasNext()) {                                       //necessary items
            String line = sc.nextLine();
            String[] sp = line.split("\\s+", 5);
            UserList.add(line);
            Usernames.add(sp[0]);
            UserNicknames.add(sp[1]);
            Userpasswords.add(sp[2]);
            UserBOD.add(sp[3]);
            UserGSI.add(sp[4]);
        }
        for (String date0 : UserBOD) {          // I used this to convert dates
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date0);
            UserBODs.add(date1);
        }
        for (int i = 1; i <= UserList.size(); i++)  // I used this to see how many users added system
            UserIDs.add(i);                         // whatever removed or added

    }



    public void ADDUSER(String name, String nickname, String password, String birthofdate, String schoolinfo) throws ParseException {
        UserList.add(name + "\t" + nickname + "\t" + password + "\t" + birthofdate + "\t" + schoolinfo);
        Usernames.add(name);
        UserNicknames.add(nickname);                        //Using this I got Strings
        Userpasswords.add(password);                        // then assign them to right lists
        UserBOD.add(birthofdate);
        UserBODs.add(toSimpleDate(birthofdate));
        UserGSI.add(schoolinfo);
        UserIDs.add(UserIDs.size() + 1);


    }

    public Date toSimpleDate(String date) throws ParseException {           //This method convert
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);    //dates
        return date1;
    }

    public void REMOVEUSER(Integer userID) {        //This method removes user infos using ID
        UserList.remove(userID - 1);
        Usernames.remove(userID - 1);
        UserNicknames.remove(userID - 1);
        Userpasswords.remove(userID - 1);
        UserBOD.remove(userID - 1);
        UserBODs.remove(userID - 1);
        UserGSI.remove(userID - 1);
        UserIDs.remove(userID - 1);
    }
    public boolean SIGNIN(String userName, String password) { //Using this boolean method i check if a user currently
        boolean signInStatus = false;                           //in system
        int tempID = UserNicknames.indexOf(userName);           // Using this to check and get from list I created
        if (UserNicknames.contains(userName)) {                 // temporary ID
            if (userName.equals(UserNicknames.get(tempID)) && password.equals(Userpasswords.get(tempID))) {
                signInStatus = true;                //This checks if nickname and password match

                if (CurrentlyUser.size() == 0)
                    CurrentlyUser.add(tempID);              //Adds system ID to check if currently any user exist
                else if (CurrentlyUser.size() > 0) {
                    CurrentlyUser.remove(0);
                    CurrentlyUser.add(tempID);
                }

            } else
                signInStatus = false;
        }                                               //Return status if anyone inside system Signed in
        return signInStatus;
    }

    public void LISTUSERS() {
        for (int i = 0; i < UserIDs.size(); i++) {
            System.out.println("--------------------");                 //To list users that signed up
            System.out.println("Name: " + Usernames.get(i));
            System.out.println("Username: " + UserNicknames.get(i));
            System.out.println("Date of Birth: " + UserBOD.get(i));
            System.out.println("School: " + UserGSI.get(i));
        }
    }

    public void UPDATEPROFILE(String name, String dateofbirth, String schoolinfo) throws ParseException {
        int tempID;
        tempID = CurrentlyUser.get(CurrentlyUser.size() - 1);
        UserList.set(tempID, name + " " + UserNicknames.get(tempID) + " " + Userpasswords.get(tempID) + " " + dateofbirth + " " + schoolinfo);
        Usernames.set(tempID, name);
        UserBOD.set(tempID, dateofbirth);                           //To update profiles I created temporary ID with
        UserBODs.set(tempID, toSimpleDate(dateofbirth));            // using set method i changed variables and updated
        UserGSI.set(tempID, schoolinfo);
    }

    public void CHPASS(String oldpass, String newpass) {
        int tempID;
        tempID = CurrentlyUser.get(CurrentlyUser.size() - 1);
        if (oldpass.equals(Userpasswords.get(tempID)))              // In this method using tempId
            Userpasswords.set(tempID, newpass);                     // I used equals method to check
        else if (CurrentlyUser.size() == 0)                          // if old passwords matches the password inside system
            System.out.println("Please sign in and try again.");
        else
            System.out.println("Password mismatch!");
    }

    public void ADDFRIEND(String name) {
        int tempID;                                                                 //In this method tempID used to
        tempID = CurrentlyUser.get(CurrentlyUser.size() - 1);                       //assign friends to specific sublist
        for (int i = 0; i < UserList.size(); i++)
            FriendLists.add("");
        if (UserNicknames.contains(name)) {
            if (FriendLists.subList(tempID, tempID + 1).contains(name))                         //With if else and contains method
                System.out.println(name + " This user is already in your friend list!");        //I checked if friends already exist
            else {
                FriendLists.add(tempID, name);
                System.out.println(name + " has been successfully added to your friend list.");
            }
        } else if (CurrentlyUser.size() == 0)
            System.out.println("Please sign in and try again.");
        else
            System.out.println("No such user!");
    }

    public void REMOVEFRIEND(String name) {
        int tempID;
        tempID = CurrentlyUser.get(CurrentlyUser.size() - 1);       //I used tempID to remove necessary things
        if (UserNicknames.contains(name)) {                         //Contains method checks if given input exist
            if (FriendLists.subList(tempID, tempID + 1).contains(name)) {       //If exist using tempID I removed informations
                FriendLists.subList(tempID, tempID + 1).remove(name);
                System.out.println(name + " has been successfully removed from your friend list.");
            } else
                System.out.println("No such friend!");
        } else if (CurrentlyUser.size() == 0)
            System.out.println("Please sign in and try again.");
    }

    public void BLOCK(String name) {            //Block method add user to blockedlist and does nothing else
        int tempID;
        tempID = CurrentlyUser.get(CurrentlyUser.size() - 1);
        if (BlockedLists.size() < UserList.size()) {                //This creates sublists for each user in system
            for (int i = 0; i < UserList.size(); i++)
                BlockedLists.add("");
        } else if (BlockedLists.size() == UserList.size() - 1)
            BlockedLists.add("");
        if (UserNicknames.contains(name)) {
            if (BlockedLists.subList(tempID, tempID + 1).contains(name))    //This checks first if given input
                System.out.println(name + "already blockled.");             // already exist then if user signed in
            else {                                                          // adds given input to blocked list
                BlockedLists.add(tempID, name);
                System.out.println(name + "  has been successfully blocked");
            }
        } else if (CurrentlyUser.size() == 0)
            System.out.println(" Please sign in and try again.");
        else {
            System.out.println("No such user!");
        }
    }

    public void UNBLOCK(String name) {
        int tempID;
        tempID = CurrentlyUser.get(CurrentlyUser.size() - 1);               //This removes given input from blocked list
        if (BlockedLists.subList(tempID, tempID + 1).contains(name)) {      // if given input exist in list using contains
            BlockedLists.subList(tempID, tempID + 1).remove(name);          // and remove methods
            System.out.println(name + "  has been successfully unblocked.");
        } else if (CurrentlyUser.size() == 0)
            System.out.println("Please sign in and try again.");
        else
            System.out.println("No such user in your blocked-user list!");
    }

    public void LISTFRIENDS() {
        int tempID;                                                                     //This method gets tempID from system
        tempID = CurrentlyUser.get(CurrentlyUser.size() - 1);                           //then from IDs sublists checks friends
        for (int i = 0; i < FriendLists.subList(tempID, tempID + 1).size(); i++) {      //gets their informations from lists
            String sp = FriendLists.subList(tempID, tempID + 2).toString();             //prints their informations
            String[] sps = sp.split("\\s+", FriendLists.subList(tempID, tempID + 2).size());
            for (int x = 0; x < sps.length; x++) {
                String tempFriendname = FriendLists.subList(tempID, tempID + x + 1).get(x);
                int tempFriendID = UserNicknames.indexOf(tempFriendname);
                System.out.println("------------------------");
                System.out.println("Name: " + Usernames.get(tempFriendID));
                System.out.println("Username: " + UserNicknames.get(tempFriendID));
                System.out.println("Date of birth: " + UserBOD.get(tempFriendID));
                System.out.println("School: " + UserGSI.get(tempFriendID));
            }
        }
    }

    public void SHOWBLOCKEDFRIENDS() {
                                                                       //This method checks each variable of list friends
        for (int i = 0; i < BlockedLists.size(); i++) {                //and blocked list if same variable inside both of
            String sp = BlockedLists.get(i);                           //them prints that users information
            if (sp.length() > 1) {
                if (BlockedLists.contains(sp) && FriendLists.contains(sp)) {    //this checks if given input exist inside both
                    System.out.println("------------------------");
                    int tempFriendID = UserNicknames.indexOf(sp);
                    System.out.println("Name: " + Usernames.get(tempFriendID));
                    System.out.println("Username: " + UserNicknames.get(tempFriendID));
                    System.out.println("Date of birth: " + UserBOD.get(tempFriendID));
                    System.out.println("School: " + UserGSI.get(tempFriendID));
                } else if (CurrentlyUser.get(CurrentlyUser.size() - 1) == 0)
                    System.out.println("Please sign in and try again.");
                else if (BlockedLists.contains(sp) == false || FriendLists.contains(sp) == false)
                    System.out.println("You haven't blocked friends yet.");
            }
        }
    }

    public void SHOWBLOCKEDUSERS(){
                                                                     //This method gets variable from list
        for(int i = 0; i < BlockedLists.size(); i++){                //uses it to get necessary variables
            String sp = BlockedLists.get(i);                         //then prints information
            if (sp.length() > 1){
                int tempFriendsID = UserNicknames.indexOf(sp);
                System.out.println("Name: "+Usernames.get(tempFriendsID));
                System.out.println("Username: "+UserNicknames.get(tempFriendsID));
                System.out.println("Date of birth; "+UserBOD.get(tempFriendsID));
                System.out.println("School: "+UserGSI.get(tempFriendsID));
            }
        }
    }

    public boolean getifuserexis(String name){                      //This checks if given input
        return UserNicknames.contains(name);                        //exist in users
    }
    public void SIGNOUT() {
        CurrentlyUser.remove(CurrentlyUser.size() - 1);       //This method makes currently
    }                                                               //user list empty and none of methods works
}