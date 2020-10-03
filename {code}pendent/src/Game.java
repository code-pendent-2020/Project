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

    private final Helper helper = new Helper();
//    private DartController dartController = new DartController();

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

    public List<Game> addNewGame(List<Game> games) {

        int countArray = games.size();


        System.out.print("Title:  ");
        String newGameTitle = helper.input.nextLine();

        System.out.print("Genre:  ");
        String newGameGenre = helper.input.nextLine();

        System.out.print("Daily Rent Fee:  ");
        double newGameRentCost = helper.input.nextDouble();
        helper.input.nextLine();

        games.add( new Game(newGameTitle, newGameGenre, newGameRentCost));
        System.out.println("Game Added Successfully : " + games.toString());

        System.out.println("1) Add another game" + "\n" + "2) View all games" + "\n" + "3) Employee Menu");
        int userChoice = helper.input.nextInt();
        if (userChoice == 1) {
            addNewGame(games);
        } else if (userChoice == 2) {
            viewAll(games);
        } // else employeeMenu();

        return games;
    }

    public List<Game> removeGame(List<Game> games) {
        System.out.println("Which game should be removed? ID:");
        String gameId = helper.input.nextLine();
        boolean contains = false;
        if (games.contains(gameId)) {
            contains = true;
            System.out.println("Are you sure you want to remove this game from the directory?" + "\n" + games.toString() + "\n" + "(Y/N)");
            String doubleCheck = helper.input.nextLine();
            if (doubleCheck.equalsIgnoreCase("y")) {
                games.remove(gameId);
                System.out.println("Game removed");
            } else {
                System.out.println("Okay, no problem. ");
//                dartController.menus.employeeMenu();
            }
            viewAll(games);
        } else {
            System.out.println("That game doesn't seem to be in the directory.");
            viewAll(games);
        }
        return games;
      //  System.out.println("Game has to be returned before it can be removed from the system.\n");
           // if (!contains) System.out.println("Couldn't find that game. Please make sure you enter the correct ID.\n");
            //  menus.employeeMenu();
    }

    public void viewAll(List<Game> games){
        System.out.println("Games:" + "\n");
        for (Game game : games) {
                System.out.println(game.toString());

        }
        System.out.println("1) Back to Employee Menu " + "\n" + "2) Back to Main Menu");
        Scanner userChoice = new Scanner(System.in);
        int whereTo = userChoice.nextInt();

        if (whereTo == 1){
           // menus.employeeMenu();
        } // else menus.mainMenu();
    }
}