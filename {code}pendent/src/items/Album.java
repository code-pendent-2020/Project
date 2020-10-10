package items;

import tools.Input;

import java.time.LocalDate;
import java.util.UUID;

public class Album extends Inventory {
    private String artist;


    private final Input input = Input.getInstance();

    public Album(){}

    public Album(String title, String artist, int year, double dailyRent, boolean rentStatus, LocalDate date) {
        super(title, dailyRent, year, rentStatus, date);
        this.artist = artist;
    }

    public Album(String title, String artist, int year, double dailyRent) {
        super(title, dailyRent, year);
        this.artist = artist;
    }

    public String toString() {
        return input.DIVIDER + input.EOL + "ID: " + this.getID() + input.EOL + "Album: " + this.getTitle()
                + input.EOL + "Artist: " + this.getArtist() + ". " + input.EOL + "Released year: " +
                this.getYear() + ". " + input.EOL + "Daily Price: " + this.getDailyRent() + " SEK."
                + input.EOL +"Status: " + this.getRentStatus() + input.EOL + "Rating: " + this.averageRating();
    }

    public String getID() {
        return super.getId();
    }

    public String getTitle() {
        return super.getTitle();
    }

    public String getArtist() {
        return artist;
    }

    public int getYear() {
        return super.getYear();
    }

    public double getRating() {
        return super.averageRating();
    }

    public double getDailyRent() {
        return super.getDailyRent();
    }

    public LocalDate getRentedDate() {
        return super.getRentedDate();
    }

    public void setRentedDate(LocalDate rentedDate) {
        super.setRentedDate(rentedDate);
    }

    public String getRentStatus() {
        if (super.isRentStatus()) {
            return "unavailable";
        }
        return "available";
    }

    public void setRentStatus(Boolean rentStatus) {
        super.setRentStatus(rentStatus);
    }

    public Album addAlbum() {
        String addTitle = input.getInput("Title: ");
        String addArtist = input.getInput("Artist: ");
        int addYear = input.getInt("Year: ");
        double addDailyRent = input.getDouble("Daily Rent amount: ");
        return new Album(addTitle, addArtist, addYear, addDailyRent);
    }

}
