import java.util.ArrayList;
import java.util.UUID;

public class Album {
    private String albumID;
    private String title;
    private String artist;
    private int year;
    private double dailyRent;
    private boolean rentStatus;
    private int rating;


    private Helper helper = new Helper();

    public Album(){
    };

    public Album(String title, String artist, int year, double dailyRent){
        this.albumID = genAlbumID();
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.dailyRent = dailyRent;
        this.rentStatus = false;
    }

 public String toString( ){
        return this.getID() + " : " + this.getTitle() + " - by " + this.getArtist() + ". " + "Released in " + this.getYear() + ". " + " Price: " + this.getDailyRent() + " SEK. Status: " + this.getRentStatus();
 }

 public String getID(){ return albumID; }

    public String getTitle(){
        return title;
    }

    public String getArtist(){
        return artist;
    }

    public int getYear(){
        return year;
    }

    public double getDailyRent(){
        return dailyRent;
    }

    public String getRentStatus(){
        if(this.rentStatus){
            return "Not available";
        }else return "available";
    }
    public void setRentStatus(Boolean rentStatus){
        this.rentStatus = rentStatus;
    }

    public Album addAlbum(){
        String addTitle = helper.getInput("Title: ");
        String addArtist = helper.getInput("Artist: ");
        int addYear = helper.getInt("Year: ");
        double addDailyRent = helper.getDouble("Daily Rent amount: ");
        Album album = new Album(addTitle, addArtist, addYear, addDailyRent);
        return album;
    }

    private String genAlbumID() {
        String generatedID = UUID.randomUUID().toString();
        return generatedID;
    }

}
