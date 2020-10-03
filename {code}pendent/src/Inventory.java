import java.time.LocalDate;
import java.util.UUID;

public class Inventory {
    private String id;
    private String title;
    private double dailyRent;
    private LocalDate rentedDate;
    private boolean rentStatus;

    // Constructor
    public Inventory(){
    }

    public Inventory(String title, double dailyRent) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.dailyRent = dailyRent;
        this.rentStatus = false;
        this.rentedDate = null;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public double getDailyRent() { return dailyRent; }

    public void setDailyRent(double dailyRent) { this.dailyRent = dailyRent; }

    public LocalDate getRentedDate() { return rentedDate; }

    public void setRentedDate(LocalDate rentedDate) { this.rentedDate = rentedDate; }

    public boolean isRentStatus() { return rentStatus; }

    public void setRentStatus(boolean rentStatus) { this.rentStatus = rentStatus; }
}
