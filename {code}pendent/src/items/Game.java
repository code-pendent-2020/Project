package items;

import tools.Input;
import java.time.LocalDate;

public class Game extends Inventory {

    private String genre;
    private Input input = Input.getInstance();

    public Game(String title, String genre, double dailyRent, int year) {
        super(title, dailyRent, year);
        this.genre = genre;
    }

    public Game(String title, String gameGenre, double dailyRent, int year, boolean rentStatus, LocalDate date) {
        super(title, dailyRent, year, rentStatus, date);
        this.genre = gameGenre;
    }

    public String getId() {
        return super.getId();
    }

    public void setId(String id) {
        super.setId(id);
    }

    public String getTitle() {
        return super.getTitle();
    }

    public String getGenre() {
        return genre;
    }

    public double getRentCost() {
        return super.getDailyRent();
    }

    public boolean getRentStatus() {
        return super.isRentStatus();
    }

    public void setRentStatus(boolean isRented) {
        super.setRentStatus(isRented);
    }

    public LocalDate getRentedDate() {
        return super.getRentedDate();
    }

    public void setRentedDate(LocalDate rentedDate) {
        super.setRentedDate(rentedDate);
    }

    public int getYear() {
        return super.getYear();
    }

    public double getRating() {
        return super.averageRating();
    }

    @Override
    public String toString() {
        String outOnRent;
        if (this.getRentStatus()) {
            outOnRent = "\033[31mOut on rent  \033[0m";
        } else outOnRent = "Available";

        String outputString = this.getId() + " : " + this.getTitle() + " (" + this.getGenre() + "). " + "Released in " + super.getYear() + ". " + this.getRentCost()
                + "kr. " + "Status: " + outOnRent + input.EOL;
        return outputString;
    }
}