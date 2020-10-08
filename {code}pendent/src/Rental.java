import java.time.LocalDate;
import java.util.List;
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

    public void totalProfit(){
        System.out.println("Our total profit is: " + rentalIncome);
    }

    public double getRentalIncome() {
        return rentalIncome;
    }

    public void rentGame(List<Game> games) {
        boolean contains = false;
        String rentId = input.getInput("Enter the ID of the game would you like to rent: ");
            for (Game rentGame : games) {
                if (rentGame.getId().equals(rentId) && !rentGame.getRentStatus()) {
                    contains = true;
                    rentGame.setRentStatus(true);
                    rentGame.setRentedDate(LocalDate.now());
                    System.out.println("Game is rented. Enjoy!");
                } else if (rentGame.getId().equals(rentId) && rentGame.getRentStatus()) {
                    contains = true;
                    int choice = input.getInt("Sorry, that game is being rented at the moment \n1) Try a different game \n2) Back to Customer menu \n");
                    if (choice == 1) {
                        rentGame(games);
                    } else if (choice == 2) {
                        Menus menus = new Menus();
                        menus.customerMenu();
                    } else {
                        System.out.println("Wrong entry");
                        Menus menus = new Menus();
                        menus.mainMenu();
                    }
                }
            }
        if (!contains) {
            System.out.println("Soz, wrong ID, try again");
            rentGame(games);
        }
    }


     public void returnGame(String customerId, List<Game> games) {
        String rentId = input.getInput("Enter the ID of the game would you like to return: ");
        long daysRented = 0;
        double userBill = 0;
        boolean contains = false;
         for (Game rentedGame : games) {
            if (rentedGame.getId().equals(rentId)) {
                contains = true;
                if (rentedGame.getRentStatus()){
                    daysRented = DAYS.between( rentedGame.getRentedDate(), LocalDate.now());
                    rentedGame.setRentStatus(false);
                    userBill = daysRented * rentedGame.getDailyRent();
                    rentalIncome = rentalIncome + userBill;
                    System.out.println("\nYou rented " + rentedGame.getTitle() + " for " + daysRented + " days. \nYour total is " + userBill + " SEK \n");
                    System.out.println("The Game has now been returned. \n");
                    int rating = input.getInt("We hope you enjoyed playing this classic. How would you rate it on a scale of 0-5? ");
                    String feedbackQuestion = input.getInput("Would you like to leave a review? Y/N ");
                    String feedback = null;
                    if (feedbackQuestion.equalsIgnoreCase("y")){
                        feedback = input.getInput("How did you experience the game? Do you have any advice for other players?");
                    }
                    System.out.println("Thank you for your feedback! ");
                    Rating customerRating = new Rating(rating, feedback);

                } else if (!rentedGame.getRentStatus()){
                    System.out.println("This game hasn't been rented. Please try again");
                    returnGame(customerId, games);
                }
            }
        }
        if (!contains) {
             System.out.println("Game with this ID not found.");
         }
    }

    public void showRentalIncome() {
        System.out.println("Rental income to-date is: " + getRentalIncome() + " SEK\n");

    }
}
