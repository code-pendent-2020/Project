import java.time.LocalDate;
import java.util.UUID;

public class Game extends Inventory{

    private String genre;
    private Input input = Input.getInstance();

    Game(){
    }

    Game(String title, String genre, double dailyRent, int year) {
        super(title, dailyRent,year);
        this.genre = genre;
    }

    Game(String title, String gameGenre, double dailyRent, int year, boolean rentStatus, LocalDate date) {
        super(title, dailyRent, year, rentStatus, date);
        this.genre = gameGenre;
       /* if (gameIsRented) {
            this.rentedDate =  LocalDate.of( 2020 , 8 , 23 );
     } else */
    }

    public String getId(){
        return super.getId();
    }

    public void setId(String id){
        super.setId(id);
    }

    public String getTitle(){
        return super.getTitle();
    }

    public String getGenre(){
        return genre;
    }

    public double getRentCost(){
        return super.getDailyRent();
    }

    public boolean getRentStatus(){
        return super.isRentStatus();
    }

    public void setRentStatus(boolean isRented){
       super.setRentStatus(isRented);
    }

    public LocalDate getRentedDate(){
        return super.getRentedDate();
    }

    public void setRentedDate(LocalDate rentedDate){
       super.setRentedDate(rentedDate);
    }

    public int getYear(){
        return super.getYear();
    }
    public double getRating(){
        return super.averageRating();
    }

    public String toString(){
        String outOnRent;
        if (this.getRentStatus()){
            outOnRent = "\033[31mOut on rent  \033[0m";
        } else outOnRent = "Available";
        String outputString = this.getId() + "\n" + this.getTitle() + " (" + this.getGenre() + ") - " + " Released in " + super.getYear()+ " \nDaily Rent: " + this.getRentCost()
                + "kr. " + "Status: " + outOnRent + "\nRating: " + this.averageRating() + "\n" ;
        return outputString;
    }
}