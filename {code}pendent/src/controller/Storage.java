package controller;

import exceptions.InvalidInputException;
import items.*;
import items.properties.*;
import people.*;
import people.features.*;
import people.features.membership.*;
import tools.Input;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Storage<Rental> {

    private final Employee employee = new Employee();
    private final Customer customer = new Customer();
    private final Album album = new Album();
    private final RentalTransaction rental = new RentalTransaction();
    private final RentalTransaction transaction = new RentalTransaction();
    private final Game game = new Game();
    private final Rating rating = new Rating();
    private final Message message = new Message();
    private final Input input = Input.getInstance();

    public Storage() throws InvalidInputException {
    }

    // "kind of" Storage

    public void readFile(){
        BufferedReader br;
        String line;
        try {
            br = new BufferedReader(new FileReader("{code}pendent/src/db.txt"));
            while((line = br.readLine()) != null) {
                String[] token = line.split(";");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                switch (token[0].toLowerCase()){
                    case "employee":
                        employees.add(new Employee(token [1], token[2],Integer.parseInt(token[3]),token[4], Double.parseDouble(token[5])));
                        break;
                    case "customer":
                        customerList.add(new Customer(token [1], token[2]));
                        break;
                    case "game":
                        inventory.add(new Game(token [1], token[2], Double.parseDouble(token[3]), Integer.parseInt(token[4]), Boolean.parseBoolean(token[5]), LocalDate.of(Integer.parseInt(token[6]), Integer.parseInt(token[7]), Integer.parseInt(token[8]))));
                        break;
                    case "album":
                        inventory.add(new Album(token[1], token[2], Integer.parseInt(token[3]), Double.parseDouble(token[4]), Boolean.parseBoolean(token[5]), LocalDate.of(Integer.parseInt(token[6]), Integer.parseInt(token[7]), Integer.parseInt(token[8]))));
                        break;
                    default:
                        System.out.println("broken dont get here...");
                        break;

                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
       } catch (InvalidInputException exception) {
            exception.printStackTrace();
        }
    }

    public static BufferedWriter bw;

    static {
        try {
            bw = new BufferedWriter(new FileWriter("db.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private ArrayList<Album> albums = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<Customer> customerList = new ArrayList<>();
    private List<Inventory> inventory = new ArrayList<>();
    private HashMap<String, Membership> membershipRequests = new HashMap<>();
    private ArrayList<RentalTransaction> rentalHistory = new ArrayList<>();

    public ArrayList<RentalTransaction> getRentalHistory() {
        return rentalHistory;
    }

    public List<Inventory> getInventory() {
        return inventory;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Customer> getCustomers() {
        return customerList;
    }


    public void itemsByProfit() {
        rentalHistory.sort(Comparator.comparingDouble(RentalTransaction::getRentExpense));
        Collections.reverse(rentalHistory);
        rentalHistory.forEach(System.out::println);
    }

    public void bestCustomer() {
        ArrayList<Customer> customerExpenditure = new ArrayList<>();
    /*    for (Customer customer : customerList){
            double rentalExpense = 0;
            for (Rental rental : rentalHistory){
                if (customer.getId().equals(rental.getCustomerId())){
                    rentalExpense =+ ();
                }
            }
            try {
                customerExpenditure.add(new Customer("", customer.getName(), rentalExpense));
            } catch (InvalidInputException e){
                e.getMessage();
            }
        }*/
        customerList.sort(Comparator.comparingDouble(Customer::getSpentMoney));
        Collections.reverse(customerList);
        Customer bestCustomer = customerList.get(0);
        if (bestCustomer.getSpentMoney() > 0){
            System.out.println("Name: " + bestCustomer.getName() + input.EOL + "Total rental expense: " + Math.round(bestCustomer.getSpentMoney()*100)/100);
        }
    }

    public void rentalFrequency() {
        ArrayList<Inventory> rentalFrequency = new ArrayList<>();
        for (Inventory item : inventory){
            int rentalTimes = 0;
            for (RentalTransaction rental : rentalHistory) {
                if (item.getId().equals(rental.getItemId())) {
                    rentalTimes = +1;
                }
            }
            if (rentalTimes != 0){
                rentalFrequency.add(new Inventory(item.getTitle(), rentalTimes));
        }
        }
        for (Inventory rentalItem : rentalFrequency)
        System.out.println("Title: " + rentalItem.getTitle() + input.EOL + "Times rented: " + rentalItem.getRentalFrequency() + input.EOL);
    }

    private RentalTransaction askRating(String customerId, Inventory rentedItem, double userBill){
        RentalTransaction rentTransaction = null;
        String ratingQuestion = input.getInput("We hope you enjoyed playing this " + rentedItem.getTitle() + " Would you like to rate it? Y/N ");
        if (ratingQuestion.equalsIgnoreCase("n")) {
            rentTransaction = new RentalTransaction(customerId, rentedItem.getId(), rentedItem.getTitle(), userBill);
            return rentTransaction;
        } else if (ratingQuestion.equalsIgnoreCase("y")) {
            String feedback = null;
            int rating = 0;
            Rating customerRating = null;
            rating = input.getInt("How would you rate it on a scale of 0-5? ");
            if (rating > 5) {
                rating = 5;
            } else if (rating < 0) {
                rating = 0;
            }
            String feedbackQuestion = input.getInput("Would you like to leave a review? Y/N ");
            if (feedbackQuestion.equalsIgnoreCase("y")) {
                feedback = input.getInput("How did you experience the " + rentedItem.getTitle() + "?  Do you have any advice for other players? or did you kind of just suck at it...");
                System.out.println("Thank you for your feedback!");
                customerRating = new Rating(rating, feedback);
            } else {
                System.out.println("Thank you for your feedback!");
                customerRating = new Rating(rating);
            }
            rentTransaction = new RentalTransaction(customerId, rentedItem.getId(), rentedItem.getTitle(), userBill, customerRating);
            rentedItem.getRatingSet().add(customerRating);
        }
        return rentTransaction;
    }

    private Inventory retrieveItem(String rentId) {
        Inventory searchedItem = null;
        Iterator<Inventory> searching = inventory.iterator();
        while(searching.hasNext() && searchedItem == null){
            Inventory current = searching.next();
            if(current.getId().equals(rentId)){
                searchedItem = current;
                return searchedItem;
            }
        }
        System.out.println("That item doesn't exist on our database.");
        return null;
    }

    private Customer retrieveCustomer(String name) {
        Customer user = null;
        Iterator<Customer> searching = customerList.iterator();
        while(searching.hasNext() && user == null){
            Customer currentCustomer = searching.next();
            if(currentCustomer.getName().equalsIgnoreCase(name)){
                user = currentCustomer;
                return user;
            }
        }
        System.out.println("That customer doesn't exist on our database.");
        return null;
    }

    public void viewTransactions() {
        for (RentalTransaction rental : getRentalHistory()) {
            System.out.println(rental);
        }
    }

    public void totalProfit() {
        double profit = rental.getRentalIncome();
        System.out.println("The total profit is " + profit + " kr" + input.EOL);
    }

    public void addCustomer() {
        try {
            String name = input.getInput("Enter the Customers Name: ");
            customerList.add(new Customer("", name));
        } catch (InvalidInputException e){
            e.getMessage();
        }
    }

    public void removeCustomer() {
        viewCustomer();
        String removeId = input.getInput("Enter the ID of the customer you want to remove. " + input.EOL + "ID: ");
        this.customerList.removeIf(customer -> customer.getId().equals(removeId));
    }

    public void viewCustomer() {
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }
    }

    public void membershipRequestList() {
        for (String key : membershipRequests.keySet()) {
            String choice = input.getInput((key + " has requested a membership upgrade" + input.EOL + "Approve (Y/N): "));
            if (choice.equalsIgnoreCase("y")) {
                    switch (membershipRequests.get(key).membershipType()) {
                        case "Regular":
                            for (Customer customer : customerList) {
                                if (customer.getName().equalsIgnoreCase(key)) {
                                    customer.setMembership(new Silver());
                                    System.out.println(key + " has been upgraded to Silver Membership" + input.EOL);
                                }
                            }
                            break;
                        case "Silver":
                            for (Customer customer : customerList) {
                                if (customer.getName().equalsIgnoreCase(key)) {
                                    customer.setMembership(new Gold());
                                    System.out.println(key + " has been upgraded to Gold Membership" + input.EOL);
                                }
                            }
                            break;
                        case "Gold":
                            for (Customer customer : customerList) {
                                if (customer.getName().equalsIgnoreCase(key)) {
                                    customer.setMembership(new Platinum());
                                    System.out.println(key + " has been upgraded to Platinum Membership" + input.EOL);
                                }
                            }
                            break;
                        default:
                            System.out.print("Membership is not valid for upgrade");
                            break;
                    }
            } else {
                for (Customer customer : customerList) {
                    if (customer.getName().equalsIgnoreCase(key)) {
                        customer.getInbox().add(new Message("Recent Membership Request",
                                "Your membership request at this time has been reviewed and unfortunately at this time has been denied." + input.EOL,
                                "Management",
                                "DART"));
                    }
                }
            }
        }
        membershipRequests.clear();
    }

    public void requestMembership(){ // move to customer
        String name = input.getInput("Customer Name: ");
        Customer user = retrieveCustomer(name);
            if (user != null){
                if (user.getMembershipType().equals("Regular")){
                    membershipRequests.put(user.getName(), user.getMembership());
                    System.out.print("Request for Silver Membership has been submitted for review"+input.EOL);
                }
            }
    }

    public void upgradeMembership(){ // move to customer
        String name = input.getInput("Customer Name: ");
        Customer user = retrieveCustomer(name);
        if (user != null){
            if (user.getMembershipType().equals("Regular")) {
                System.out.println("This customer does not seem to have a membership try requesting one");
            } else if (user.getMembershipType().equalsIgnoreCase("Platinum")) {
                System.out.println("Platinum Members cannot upgrade further");
            } else { // needs error handling for platinum requests
                membershipRequests.put(user.getName(), customer.getMembership());
                System.out.println("Application for membership upgrade has been submitted for review");
            }
        }
    }

    public void addEmployee() {
        boolean isRunning = false;
        do {
            try {
                String name = input.getInput("Name: ");
                int birthYear = input.getInt("Birth year: ");
                String address = input.getInput("Address: ");
                double monthlySalary = input.getDouble("Monthly gross salary: ");
                double grossSalary = monthlySalary * employee.MONTHS;
                employees.add(new Employee("", name, birthYear, address, grossSalary));
                isRunning = true;
            } catch (InvalidInputException e) {
                System.out.println("That's not the correct input, be careful and please try again.");
                input.input.nextLine();

            }
        } while (!isRunning);
    }

    public void removeEmployee() {
        String removeID = input.getInput("Enter the ID of the employee you want to remove." + input.EOL + "Employee ID: ");
        this.employees.removeIf(employee -> employee.getId().equals(removeID));
        System.out.println("Employee has been deleted of the face of the earth!" + input.EOL);
    }

    public void viewEmployee() {
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    public void addAlbum() {
        String userInput = null;
        do {
            try {
                String addTitle = input.getInput( "Title: ");
                String addArtist = input.getInput("Artist: ");
                int addYear = input.getInt("Year: ");
                double addDailyRent = input.getDouble("Daily Rent amount: ");
                this.inventory.add(new Album(addTitle, addArtist, addYear, addDailyRent));
            } catch (Exception exception){
                System.out.println(input.EOL + exception.getMessage());
                userInput = input.getInput("Would you like to try again? Y/N: ");
            }
        } while (userInput.equalsIgnoreCase("y"));
    }

    public void removeAlbum() {
        String removeID = input.getInput("Remove." + input.EOL + "Album ID: ");
        Inventory toRemove = retrieveItem(removeID);
        if (toRemove != null){
            inventory.removeIf(album -> album.getId().equals(removeID));
            System.out.println("Album has been sent to the moon and is no longer retrievable!" + input.EOL);
        }
    }


    public void rentAlbum() {
        String maxRentals = "You have reached your current limit on rentals. It's great that you enjoy our products so much!";
        String user = input.getInput("Please enter your name: ");
        Customer customer = retrieveCustomer(user);
        if (user != null) {
                if (customer.canRent()) {
                    viewAlbums();
                    String rentId = input.getInput("Hi " + customer.getName() + ". Which album would you like to rent?" + input.EOL + "ID: ");
                    Inventory albumToRent = retrieveItem(rentId);
                    if (albumToRent != null){
                        transaction.rentItem(albumToRent);
                        customer.incrementRentals();
                    }
                } else {
                    System.out.println(maxRentals);
                }
            }
    }

    public void returnAlbum() {
        String name = input.getInput("Type your name to begin a lengthy and tedious return process: ");
        Customer customer = retrieveCustomer(name);
            if (customer != null) {
                Inventory rentedItem = retrieveItem(input.getInput("Which album are you returning? ID: "));
                if (rentedItem != null){
                    customer.applyCredits();
                    double userBill =  transaction.returnItem(customer, rentedItem);
                    if (userBill != 0){
                        RentalTransaction newTransaction = askRating(customer.getId(), rentedItem, userBill);
                        getRentalHistory().add(newTransaction);
                        customer.setSpentMoney(userBill);
                        exportTransaction(newTransaction);
                    }
                }
            }
    }

    public void viewAlbums() {
        inventory.sort(Comparator.comparingInt(Inventory::getYear)); //change to lambda
        Collections.reverse(inventory);
        albums.forEach(System.out::println);
        for (Inventory album : inventory){
            if (album instanceof Album){
                System.out.println(album.toString());
            }
        }
    }

    public void searchAlbums() {
        int year = input.getInt("Album Search" + input.EOL + "Release Year: ");
        boolean yearExists = false;
        for (Inventory album : inventory) {
            if (album instanceof Album){
                if (album.getYear() == year) {
                    yearExists = true;
                    System.out.println(album.toString());
                }
            }
        }
        if (yearExists == false){
            System.out.println("There is no album available for this year.");
        }
    }

    public void viewAlbumsByRating() {
        // sorts albums by comparing the rating value
        // the :: (invokes the method getRating from the album class. compares albums ratings as a double)
        inventory.sort(Comparator.comparingDouble(Inventory::getRating));
        Collections.reverse(inventory);
        for (Inventory album : inventory){
            if (album instanceof Album){
                System.out.println(album.toString());
            }
        }
    }

    public void addGame() {
        String userInput = "";
        do {
            try {
                String newGameTitle = input.getInput("Title:  ");
                String newGameGenre = input.getInput("Genre:  ");
                int newGameYear = input.getInt("Year:  ");
                double newGameRentCost = input.getDouble("Daily Rent Fee:  ");
                inventory.add(new Game(newGameTitle, newGameGenre, newGameRentCost, newGameYear ));
                System.out.println("Game Added Successfully " +input.EOL + inventory.get(inventory.size()-1).toString());
            } catch (Exception exception){
               System.out.println( exception.getMessage());
                userInput = input.getInput("Would you like to try again? Y/N"+input.EOL);
            }
        } while (userInput.equalsIgnoreCase("y"));
    }

    public void removeGame() {
        viewGames();
        String gameId = input.getInput("Which game should be removed? Enter the Game ID: ");
        Inventory gameToRemove = retrieveItem(gameId);
        if (gameToRemove != null) {
            System.out.println("Are you sure you want to remove this game from the directory?" + input.EOL + gameToRemove.toString() + input.EOL + "(Y/N)");
            String doubleCheck = input.input.nextLine();
            if (doubleCheck.equalsIgnoreCase("y")) {
                inventory.removeIf(game -> game.getId().equals(gameId));
                System.out.println("Game fed to a bunch of alpacas - theres no retrieving it anymore...");
            } else {
                System.out.println("Okay, no problem. ");
            }
        }
    }

    public void viewGames() {
        inventory.sort(Comparator.comparingInt(Inventory::getYear));
        Collections.reverse(inventory);
        for (Inventory game : inventory) {
            if (game instanceof Game){
                System.out.println(game.toString());
            }
        }
    }

    public void rentGame() {
        String maxRentals = "You have reached your current limit on rentals. That's awesome that you enjoy our products so much!";
        String name = input.getInput(input.EOL + "Customer Name: ");
        Customer user = retrieveCustomer(name);
        if (user != null) {
            if (user.canRent()) {
                viewGames();
                String rentId = input.getInput("Hi " + user.getName() + ". Which game would you like to rent?" + input.EOL + "ID: ");
                Inventory gameToRent = retrieveItem(rentId);
                if (gameToRent != null) {
                    transaction.rentItem(gameToRent);
                    user.incrementRentals();
                }
            } else {
                System.out.println(maxRentals);
            }
        }
    }

    public void returnGame() {
        String name = input.getInput("Hiya! What is your name?  ");
        String returned;
        Customer customer = retrieveCustomer(name);
        if (customer != null) {
            viewGames();
            do {
                returned = "n";
                String rentId = input.getInput(input.EOL + "Enter the ID of the game would you like to return: ");
                Inventory gameToReturn = retrieveItem(rentId);
                if (gameToReturn != null) {
                    if (gameToReturn.isRentStatus()) {
                        double userBill = transaction.returnItem(customer, gameToReturn);
                        if (userBill != 0) {
                            RentalTransaction newTransaction = askRating(customer.getId(), gameToReturn, userBill);
                            getRentalHistory().add(newTransaction);
                            customer.setSpentMoney(userBill);
                            exportTransaction(newTransaction);
                        }
                    } else {
                       returned = input.getInput("This game hasn't been rented. " + input.EOL + "Try again? Y/N: ");
                    }
                }
            } while (!returned.equalsIgnoreCase("n")) ;
        }
    }

    public void exportTransaction(RentalTransaction transaction){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("{code}pendent/src/transactions.txt", true));
            String newTransaction = transaction.getCustomerId()+";"+transaction.getItemId()+";"+transaction.getTitle()+";"+transaction.getRentExpense(); //TODO ADD TITLE!!!
            bw.write(newTransaction + input.EOL);
            bw.close();
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void searchGames() {
        String google = input.getInput("Game Search" + input.EOL + "Genre: ");
        for (Inventory game : inventory) {
            if (game instanceof Game){
                if (((Game) game).getGenre().equalsIgnoreCase(google)) {
                    System.out.println(game.toString());
                }
            }
        }
    }

    public void viewGamesByRating() {
        inventory.sort(Comparator.comparingDouble(Inventory::getRating));
        Collections.reverse(inventory);
        for (Inventory game : inventory) {
            if (game instanceof Game){
                System.out.println(game.toString());
            }
        }
    }

    public void sendMessage() {
        viewCustomer();
        String recipientId = input.getInput(input.EOL + "Enter the customer ID of the person you want to communicate with (for those times were communication is necessary with other humans...): ");
        for (Customer customer : customerList) {
            if (customer.getId().equalsIgnoreCase(recipientId)) {
                String senderID = input.getInput("Type your ID: ");
                String senderName = input.getInput("Type your Name: ");
                String subject = input.getInput("Type your Title: ");
                String body = input.getInput("Type your message: ");
                Message newMessage = new Message(subject, body, senderID, senderName);
                System.out.println("Your message has been delivered to our own personal flying T-rex for prompt delivery (we breed them specifically for speed and agility).");

                customer.getInbox().add(newMessage);
            }
        }
    }

    public void viewMessages() {
        String name = input.getInput("Type your name to view your inbox (we know you don't want to see all those unread messages! but you've got to sort it out sometime!): ");
        for (Customer reader : customerList) {
            if (reader.getName().equalsIgnoreCase(name) && reader.getInbox().size() != 0) {
                Collections.reverse(reader.getInbox());
                System.out.println(input.EOL + ">> List of messages in order received <<");
                System.lineSeparator();
                customer.viewMessages(reader);
                Collections.reverse(reader.getInbox());
            } else if (reader.getName().equalsIgnoreCase(name) && reader.getInbox().size() == 0) {
                System.out.println(input.EOL + "No messages to view (guess no one likes you...).");
            }
        }
    }

    public void removeMessages() {
        viewMessages();
        String removeMessage = input.getInput("Enter the message ID you want to delete: ");
        for (Customer customer : customerList) {
            customer.getInbox().removeIf(message -> message.getMessageId().equalsIgnoreCase(removeMessage));
        }
        System.out.println("The message has been deleted.");
        viewMessages();
    }

    public void team() {
        System.out.println("-- Team {Code}pendant --" + input.EOL+"Silent Saboteur - Axel"+input.EOL+"Hacker SEM 2020 - Navya"+input.EOL+"The Dead One - Drake"+input.EOL+"The British One - Vernita");
    }
}
