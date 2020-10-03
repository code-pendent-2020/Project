import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static java.time.temporal.ChronoUnit.DAYS;

public class Rental {

    private UUID id;
    private String title;
    private String genre;
    private double rentCost;
    private boolean isRented;
    private LocalDate rentedDate;

    private static double rentalIncome = 25.00;

    private static Helper getInput = new Helper();

    // Default Constructor
    public Rental() {
    }

    public double getRentalIncome() {
        return rentalIncome;
    }

    public double totalProfit;

    public void rentGame() {
        DartController dartController = new DartController();
        List<Game> rental = dartController.storage.getGames();
        //for (Game rentGame : rental) {
        System.out.println(rental.toString());
        //  }

        String rentId = getInput.getInput("Enter the ID of the game would you like to rent: ");
        for (Game rentGame : rental) {
            if (!rental.contains(rentId)) {
                System.out.println("Soz, wrong ID, try again");
                rentGame();
            } else if (rentGame.getId() == rentId && rentGame.getIsRented() == false) {
                rentGame.setIsRented(true);
                rentGame.setRentedDate(LocalDate.now());
                System.out.println("Game is rented. Enjoy!");
            } else if (rentGame.getId() == rentId && rentGame.getIsRented() == true) {
                int choice = getInput.getInt("Sorry, that game is being rented at the moment + \n + 1) Try a different game + \n + 2) Back to Customer menu");

                if (choice == 1) {
                    rentGame();
                } else if (choice == 2) {
                    Menus menus = new Menus();
                    menus.customerMenu();
                } else {
                    System.out.println("Wrong entry");
                    Menus menus = new Menus();
                    menus.mainMenu();
                }
            } //else
        }
    }

/*
     public void returnGame() {
        DartController dartController = new DartController();
        List<Game> rental = dartController.storage.getGames();
        String rentId = getInput.getInput("Enter the ID of the game would you like to return: ");
        int idMatch = -1;
        long daysRented = 0;
        Boolean rentalStatus = false;
        Boolean isFound = false;
        double dailyRate = 0;
        double userBill = 0;

        for (int i = 0; i < rental.size();i++) {
            if (rental[i].getId().equals(rentId)) {
                daysRented = DAYS.between( rental[i].getRentedDate(), LocalDate.now());
                isFound = true;
                idMatch = i;
                rentalStatus = rental[i].getIsRented();
                dailyRate = rental[i].getRentCost();
            }
        }

        if (isFound == true && rentalStatus == true){
            rental[idMatch].setIsRented(false);
            userBill = daysRented * dailyRate;
            rentalIncome = rentalIncome + userBill;
            System.out.println("Your total is " + userBill + " SEK");
            System.out.println("Game is now returned.");
        } else if (isFound == true && rentalStatus == false){
            System.out.println("Game is not rented.");
        } else {
            System.out.println("Game with this ID not found.");
        }
        menus.customerMenu();
    }

    public void showRentalIncome() {
        System.out.println("Rental income to-date is: " + getRentalIncome() + " SEK\n");

        menus.employeeMenu();
    }

*/
}