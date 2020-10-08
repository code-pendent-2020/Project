import java.time.LocalDate;
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

    private Input input = Input.getInstance();
    private List<Game> games;

    // Default Constructor
    public Rental() {
    }

    public void totalProfit() {
        System.out.println("Total profit: " + rentalIncome);
    }

    public double getRentalIncome() {
        return rentalIncome;
    }

    public void rentGame(List<Game> games) {
        String rentId = input.getInput("Enter the ID of the game you would like to rent: ");
        if (games.contains(rentId)) {
            for (Game rentGame : games) {
                if (rentGame.getId().equals(rentId) && !rentGame.getRentStatus()) {
                    rentGame.setRentStatus(true);
                    rentGame.setRentedDate(LocalDate.now());
                    System.out.println("Game has been rented. Enjoy!");
                } else if (rentGame.getId().equals(rentId) && rentGame.getRentStatus()) {
                    int choice = input.getInt("Sorry, that game is being rented at the moment "+input.EOL+" 1) Try a different game"+input.EOL+" 2) Back to Customer menu");
                    if (choice == 1) {
                        rentGame(games);
                    } else if (choice == 2) {
                        Menus menus = new Menus();
                        menus.customerMenu(); // Just print statements
                    } else {
                        System.out.println("Wrong entry");
                        Menus menus = new Menus();
                        menus.mainMenu(); // Just print statements
                    }
                }
            }
        } else {
            System.out.println("Soz, wrong ID, try again");
            rentGame(games);
        }
    }


    public void returnGame(List<Game> games) {
        String rentId = input.getInput("Enter the ID of the game would you like to return: ");
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
                } else if (!rentedGame.getRentStatus()) {
                    System.out.println("This Game hasn't been rented. Please try again");
                    returnGame(games);
                }
            }
        }
        if (!contains) {
            System.out.println("Game with this ID not found.");
        }
    }

    public void showRentalIncome() {
        System.out.println("Rental income to-date is: " + getRentalIncome() + " SEK"+input.EOL);

    }
}
