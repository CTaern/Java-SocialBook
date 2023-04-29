package Midterm;

import java.time.LocalDate;
import java.util.ArrayList;

public class Posts {
    User user0 = new User();


    private ArrayList<String> textcontents = new ArrayList<>();
    private ArrayList<String> imagetextcontents = new ArrayList<>();
    private ArrayList<String> videotextcontents = new ArrayList<>();
    private ArrayList<Double> tlongitudes = new ArrayList<>();
    private ArrayList<Double> ilongitudes = new ArrayList<>();
    private ArrayList<Double> vlongitudes = new ArrayList<>();                  //Necessary lists to run system
    private ArrayList<Double> tlatitudes = new ArrayList<>();
    private ArrayList<Double> ilatitudes = new ArrayList<>();
    private ArrayList<Double> vlatitudes = new ArrayList<>();
    private ArrayList<String> ttaggedusernames = new ArrayList<>();
    private ArrayList<String> itaggedusernames = new ArrayList<>();
    private ArrayList<String> vtaggedusernames = new ArrayList<>();
    private ArrayList<String> filepaths = new ArrayList<>();
    private ArrayList<String> vfilepaths = new ArrayList<>();
    private ArrayList<String> resolutions = new ArrayList<>();
    private ArrayList<String> durations = new ArrayList<>();
    private ArrayList<LocalDate> tdatesofposts = new ArrayList<java.time.LocalDate>();
    private ArrayList<LocalDate> idatesofposts = new ArrayList<java.time.LocalDate>();
    private ArrayList<LocalDate> vdatesofposts = new ArrayList<java.time.LocalDate>();



    int numberoftextposts;
    int numberofimageposts;         //To see how many posts created
    int numberofvideoposts;

    public void ADDTEXTPOST(String text, String taggedfriends) {

        textcontents.add(text);
        ttaggedusernames.add(taggedfriends + ":");              //This method creates a text post assign necessary inputs
        LocalDate tempDate = java.time.LocalDate.now();         //to the their lists
        tdatesofposts.add(tempDate);                            //this creates current date when method runs
        numberoftextposts++;
    }

    public void ADDIMAGEPOST(String text, String taggedfriends) {

        imagetextcontents.add(text);
        itaggedusernames.add(taggedfriends);                    //This method creates image post and assign inputs to their lists
        LocalDate tempDate = java.time.LocalDate.now();
        idatesofposts.add(tempDate);
        numberofimageposts++;
    }

    public void ADDVIDEOPOST(String text, String taggedfriends) {

        videotextcontents.add(text);                            //This assign videopost's text to list
        vtaggedusernames.add(taggedfriends);                    //This assign videopost's taggedfriends to list
        LocalDate tempDate = java.time.LocalDate.now();         //This creates current date when method runs
        vdatesofposts.add(tempDate);
        numberofvideoposts++;                                   //This adds 1 when method runs
    }

    public void gettLatitude(double latitude) {
                                                            //These methods are for get inputs and assign them to each lists individually
        tlatitudes.add(latitude);
    }

    public void getiLatitude(double latitude) {

        ilatitudes.add(latitude);
    }

    public void getvLatitude(double latitude) {

        vlatitudes.add(latitude);
    }

    public void gettLongitude(double longitude) {

        tlongitudes.add(longitude);
    }

    public void getiLongitude(double longitude) {

        ilongitudes.add(longitude);
    }

    public void getvLongitude(double longitude) {

        vlongitudes.add(longitude);
    }

    public void getFilepath(String filepath) {

        filepaths.add(filepath);
    }

    public void getvFilepath(String filepath) {

        vfilepaths.add(filepath);
    }

    public void getResolution(String resolution) {

        resolutions.add(resolution);
    }

    public void getDuration(String duration) {

        int intduration = Integer.parseInt(duration);
        if (intduration < 10 && intduration > 0)
            durations.add(duration);
        else
            System.out.println("Duration can not be higher than 10 min.");
    }

    public void SHOWPOSTS(String name) {
        if (textcontents.size() == 0)
            System.out.println(name + " does not have any posts yet.");         //This method first checks if any posts exist
        else if (user0.getifuserexis(name)) {
            System.out.println("Please sign in and try again.");
        }else {
            System.out.println("****************");                            //Then if exist checks each list get variables
            System.out.println(name + "'s Posts");                             //prints post's informations
            System.out.println("****************");
            if (textcontents.size() > 0) {
                for (int i = 0; i < numberoftextposts; i++) {
                    System.out.println(textcontents.get(i));
                    System.out.println(tdatesofposts.get(i));
                    System.out.println(tlongitudes.get(i) + " " + tlatitudes.get(i));
                    System.out.println("Tagged friends: " + ttaggedusernames.get(i));
                    System.out.println("-------------------");
                }
            }
            if (imagetextcontents.size() > 0) {
                for (int i = 0; i < numberofvideoposts; i++) {
                    System.out.println(imagetextcontents.get(i));
                    System.out.println(idatesofposts.get(i));
                    System.out.println(ilongitudes.get(i) + " " + ilatitudes.get(i));
                    System.out.println("Tagged friends: " + itaggedusernames.get(i));
                    System.out.println("Image: " + filepaths.get(i));
                    System.out.println("Image resolution: " + resolutions.get(i));
                    System.out.println("-------------------");
                }
            }
            if (videotextcontents.size() > 0) {
                for (int i = 0; i < numberofvideoposts; i++) {
                    System.out.println(videotextcontents.get(i));
                    System.out.println(vdatesofposts.get(i));
                    System.out.println(vlongitudes.get(i) + " " + vlatitudes.get(i));
                    System.out.println("Tagged friends: " + vtaggedusernames.get(i));
                    System.out.println("Video: " + vfilepaths.get(i));
                    System.out.println("Video duration: " + durations.get(i));
                    System.out.println("-------------------");
                }
            }
        }
    }
}