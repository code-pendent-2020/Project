import java.time.LocalDate;
import java.util.HashSet;
import java.util.UUID;

public class Inventory {
    private String id;
    private String title;
    private double dailyRent;
    private LocalDate rentedDate;
    private boolean rentStatus;
    private HashSet<Rating> ratingSet;
    private Rating rating;

    // Constructor
    public Inventory() {
    }

    public Inventory(String title, double dailyRent, boolean rentStatus) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.dailyRent = dailyRent;
        this.rentStatus = rentStatus;
        this.rentedDate = null;
        this.ratingSet = new HashSet<Rating>();
    }

    public Inventory(String title, double dailyRent) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.dailyRent = dailyRent;
        this.rentStatus = false;
        this.rentedDate = null;
        this.ratingSet = new HashSet<Rating>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getDailyRent() {
        return dailyRent;
    }

    public void setDailyRent(double dailyRent) {
        this.dailyRent = dailyRent;
    }

    public LocalDate getRentedDate() {
        return rentedDate;
    }

    public void setRentedDate(LocalDate rentedDate) {
        this.rentedDate = rentedDate;
    }

    public boolean isRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(boolean rentStatus) {
        this.rentStatus = rentStatus;
    }

    public HashSet getRatingSet() {
        return ratingSet;
    }

    public void setRatingSet(Rating rating) {
        this.rating = rating;
    }

    public double averageRating() {
        if (ratingSet.size() != 0) {
            int sum = 0;
            for (Rating rating : ratingSet) {
                sum = sum + rating.getRating();
            }
            return Math.round((sum / ratingSet.size())*100)/100;
        } else {
            return 0;
        }
    }
}
