import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Game {

    private String id;
    private String title;
    private String genre;
    private double rentCost;
    private boolean isRented;
    private LocalDate rentedDate;

    private final Input helper = new Input();

    Game(){
    }

    Game(String gameTitle, String gameGenre, double gameRentCost) {
        this.id = UUID.randomUUID().toString();
        this.genre = gameGenre;
        this.title = gameTitle;
        this.rentCost = gameRentCost;
        this.isRented = false;
        this.rentedDate = null;
    }


    Game(String gameTitle, String gameGenre, double gameRentCost, boolean gameIsRented) {
        this.id = UUID.randomUUID().toString();
        this.title = gameTitle;
        this.genre = gameGenre;
        this.rentCost = gameRentCost;
        this.isRented = gameIsRented;
        if (gameIsRented) {
            this.rentedDate =  LocalDate.of( 2020 , 8 , 23 );
        }else this.rentedDate = null;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public double getRentCost(){
        return rentCost;
    }

    public void setRentCost(double rentCost){
        this.rentCost= rentCost;
    }

    public boolean getIsRented(){
        return isRented;
    }

    public void setIsRented(boolean isRented){
        this.isRented = isRented;
    }
    public LocalDate getRentedDate(){
        return rentedDate;
    }

    public void setRentedDate(LocalDate rentedDate){
        this.rentedDate = rentedDate;
    }

    public String toString(){
        String outOnRent;
        if (this.isRented){
            outOnRent = "\033[31mOut on rent  \033[0m";
        } else outOnRent = "Available";
        String outputString = this.getId() + " : " + this.getTitle() + " (" + this.getGenre() + "). " + this.getRentCost()
                + "kr. " + "Status: " + outOnRent + "\n";
        return outputString;
    }
}