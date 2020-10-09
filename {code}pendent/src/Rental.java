import java.time.LocalDate;
import java.util.List;

import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

public class Rental {

    private String customerId;
    private String itemId;
    private double rentExpense;
    private Rating rating;
    private static double rentalIncome = 25.00;
    private Input input = Input.getInstance();

    // Default Constructor
    public Rental(){
    }

    public Rental(String  customerId, String itemId, double rentExpense) {
        this.customerId = customerId;
        this.itemId = itemId;
        this.rentExpense = rentExpense;
    }

    public Rental(String  customerId, String itemId, double rentExpense, Rating rating) {
        this.customerId = customerId;
        this.itemId = itemId;
        this.rentExpense = rentExpense;
        this.rating = rating;
    }

    public Rating getRating() {
        return rating;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getRentExpense() {
        return rentExpense;
    }

    public void setRentExpense(double rentExpense) {
        this.rentExpense = rentExpense;
    }

    public void totalProfit() {
        System.out.println("Total profit: " + rentalIncome);
    }

    public double getRentalIncome() {
        return rentalIncome;
    }

    public String toString(){
        return "Customer ID: " + getCustomerId() + "\nRental Item: " + getItemId() + "\nTransaction Cost: " + getRentExpense() + "\n" +  getRating() +"\n";
    }

    public void rentGame(List<Game> games) {
        boolean contains = false;
        String rentId = input.getInput("Enter the ID of the game would you like to rent: ");
            for (Game rentGame : games) {
                if (rentGame.getId().equals(rentId) && !rentGame.getRentStatus()) {
                    contains = true;
                    rentGame.setRentStatus(true);
                    rentGame.setRentedDate(LocalDate.now());
                    System.out.println("Game has been rented. Enjoy!");
                } else if (rentGame.getId().equals(rentId) && rentGame.getRentStatus()) {
                    int choice = input.getInt("Sorry, that game is being rented at the moment "+input.EOL+" 1) Try a different game"+input.EOL+" 2) Back to Customer menu\n");
                    if (choice == 1) {
                        rentGame(games);
                    } else if (choice == 2) {
                        return;
                    } else {
                        System.out.println("Wrong entry");
                    }
                }
            }
        if (!contains) {
            System.out.println("Soz, wrong ID, try again");
            rentGame(games);
        }
    }


     public Rental returnGame(String customerId, List<Game> games) {
        String rentId = input.getInput("\nEnter the ID of the game would you like to return: ");
        long daysRented = 0;
        double userBill = 0;
        boolean contains = false;
        for (Game rentedGame : games) {
            if (rentedGame.getId().equals(rentId)) {
                contains = true;
                if (rentedGame.getRentStatus()) {
                    daysRented = DAYS.between(rentedGame.getRentedDate(), LocalDate.now());
                    rentedGame.setRentStatus(false);
                    userBill = daysRented * rentedGame.getDailyRent();
                    rentalIncome = rentalIncome + userBill;
                    System.out.println(input.EOL+"You rented " + rentedGame.getTitle() + " for " + daysRented + " days. "+input.EOL+"Your total is " + userBill + " SEK \n");
                    System.out.println("The Game has now been returned.");

                    String feedback = null;
                    int rating = 0;
                    Rating customerRating = null;
                    Rental rentTransaction = null;
                    String ratingQuestion = input.getInput("We hope you enjoyed playing this classic. Would you like to rate it? Y/N ");

                    if (ratingQuestion.equalsIgnoreCase("n")) {
                        rentTransaction = new Rental(customerId, rentId, userBill);
                        return rentTransaction;
                    } else if (ratingQuestion.equalsIgnoreCase("y")){
                        rating = input.getInt("How would you rate it on a scale of 0-5? ");
                        String feedbackQuestion = input.getInput("Would you like to leave a review? Y/N ");
                        if (feedbackQuestion.equalsIgnoreCase("y")){
                            feedback = input.getInput("How did you experience the game? Do you have any advice for other players?");
                            System.out.println("Thank you for your feedback!");
                            customerRating = new Rating(rating, feedback);
                        } else {
                            System.out.println("Thank you for your feedback!");
                            customerRating = new Rating(rating);
                        }
                        rentTransaction = new Rental(customerId, rentId, userBill, customerRating);
                    }
                    rentedGame.getRatingSet().add(customerRating);
                    return rentTransaction;
                } else if (!rentedGame.getRentStatus()){
                    System.out.println("This game hasn't been rented. Please try again");
                    returnGame(customerId, games);
                }
            }
        }
        if (!contains) {
            System.out.println("Game with this ID doesn't exist in this here dimension.");
            returnGame(customerId, games);
        }
        return null;
    }

    public void showRentalIncome() {
        System.out.println("Rental income to-date is: " + getRentalIncome() + " SEK"+input.EOL);

    }
}
