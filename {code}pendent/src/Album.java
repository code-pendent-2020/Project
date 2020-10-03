import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public class Album {
    private String albumID;
    private String title;
    private String artist;
    private int year;
    private double dailyRent;
    private LocalDate rentedDate;
    private boolean rentStatus;
    private String divider = "-----";


    private Input helper = new Input();

    public Album(){
    };

    public Album(String title, String artist, int year, double dailyRent){
        this.albumID = genAlbumID();
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.dailyRent = dailyRent;
        this.rentStatus = false;
        this.rentedDate = null;
    }

        public String toString( ){
        return divider + "\nID: " +this.getID() + "\nAlbum: " + this.getTitle() + "\nArtist: " + this.getArtist() + ". " + "\nReleased year: " + this.getYear() + ". " + "\nDaily Price: " + this.getDailyRent() + " SEK. \nStatus: " + this.getRentStatus();
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

    public LocalDate getRentedDate() { return rentedDate; }

    public String getRentStatus(){
        if(this.rentStatus){
            return "Not available";
        }else return "available";
    }

    public void setRentStatus(Boolean rentStatus){
        this.rentStatus = rentStatus;
    }

    public void setRentedDate(LocalDate rentedDate){ this.rentedDate = rentedDate; }

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
