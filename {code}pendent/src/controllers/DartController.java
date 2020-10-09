package controllers;

import items.properties.Rental;
import people.Customer;
import people.features.Membership;
import tools.Input;
import tools.Menus;

import java.util.ArrayList;

public class DartController {
    private Menus menus;
    private Input input = Input.getInstance();
    Storage storage; // should be private
    private final String invalidInput = "--- Invalid input ---";

    private ArrayList<Membership> requestList = null;

    public DartController() {
        this.menus = new Menus();
        this.storage = new Storage();

    }

    public void run() {
        mainMenu();
    }

    private void exit() {
        Input.getInstance().tearDown();
        System.out.println(input.EOL + menus.DIVIDER + input.EOL + "     Good Bye!" + input.EOL + menus.DIVIDER);
        System.exit(0);
    }

    public boolean authenticate(String password) { // authenticates passwords
        String enteredPassword = input.getInput("Enter your password: ");
        return password.equals(enteredPassword);
    }

    public void authManager() { // Why is menu auth?
        String password = "admin1234";
        boolean authSuccess = authenticate(password);

        if (authSuccess) {
            managerMenu();
        } else {
            System.out.println(input.EOL + "*** Wrong password *** " + input.EOL);
            mainMenu();
        }
    }

    public void authEmployee() {
        String password = "password123";

        Boolean authSuccess = authenticate(password);

        if (authSuccess) {
            employeeMenu();
        } else {
            System.out.println(input.EOL + "*** Wrong password ***" + input.EOL);
            mainMenu();
        }
    }

    private void membershipRequestList() {
        for (Membership request : requestList) {
            System.out.println("The following people.Customer has requested a membership: ");
            System.out.println("people.Customer : " + request.getName() + input.EOL + " Requesting: " + request.getType() + " membership");
            String requestListAnswer = input.getInput("(Y/N): ");
            if (requestListAnswer.equalsIgnoreCase("Y")) {
                for (Customer requested : storage.getCustomers()) {
                    if (requested.getName().equalsIgnoreCase(request.getName())) {
                        requested.setMembershipType(request.getType());
                    }
                }
            } else {
                System.out.println("Okay, fair enough");
            }
        }
        requestList.clear();
    }

    public void mainMenu() {
        menus.mainMenu();
        do {
            String choice = Input.input.nextLine();
            switch (choice) {
                case "1":
                    // authManager();
                    managerMenu();
                    input.userCheck();
                    break;
                case "2":
                    // authEmployee();
                    employeeMenu();
                    input.userCheck();

                    break;
                case "3":
                    customerMenu();
                    input.userCheck();
                    break;
                case "4":
                    exit();
                    break;
                default:
                    System.out.println(menus.DIVIDER + input.EOL + invalidInput);
                    System.out.print(menus.PROMPT);
                    break;
            }
        } while (true);
    }

    public void managerMenu() {
        menus.managerMenu();
        System.out.print(menus.PROMPT);
        do {
            String choice = Input.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.print(input.EOL + ">> New people.Employee" + input.EOL);
                    storage.addEmployee();
                    input.userCheck();
                    managerMenu();
                    break;
                case "2":
                    storage.viewEmployee();
                    storage.removeEmployee();
                    input.userCheck();
                    managerMenu();
                    break;
                case "3":
                    System.out.println(input.EOL + ">> All Employees");
                    storage.viewEmployee();
                    input.userCheck();
                    managerMenu();
                    break;
                case "4":
                    System.out.println(input.EOL + ">> Transaction History");
                    storage.viewTransactions();
                    input.userCheck();
                    managerMenu();
                    break;
                case "5":
                    mainMenu();
                    break;
                default:
                    System.out.println(menus.DIVIDER + input.EOL + invalidInput);
                    System.out.print(menus.PROMPT);
                    break;
            }
        } while (true);
    }

    public void employeeMenu() {
        if (requestList != null) {
            membershipRequestList();
        }
        menus.employeeMenu();
        System.out.print(menus.PROMPT);
        do {
            String choice = Input.input.nextLine();
            switch (choice) {
                case "1":
                    empGameOptions();
                    break;
                case "2":
                    empAlbumOptions();
                    break;
                case "3":
                    empCustomerOptions();
                    break;
              case "4":
                    System.out.println(input.EOL +">> Total Rent Profit");
                    Rental rental = new Rental();
                    rental.totalProfit();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "5":
                    mainMenu();
                    break;
                default:
                    System.out.println(menus.DIVIDER + input.EOL + invalidInput);
                    System.out.print(menus.PROMPT);
                    break;
            }
        } while (true);
    }

    public void empGameOptions() {
        menus.empGameOptions();
        System.out.print(menus.PROMPT);
        do {
            String choice = Input.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(input.EOL + ">> New items.Game");
                    storage.addNewGame();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "2":
                    System.out.println(input.EOL + ">> Remove items.Game:");
                    storage.viewGames();
                    storage.removeGame();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "3":
                    System.out.println(input.EOL + ">> All Games");
                    storage.viewGames();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "4":
                    employeeMenu();
                    break;
                default:
                    System.out.println(menus.DIVIDER + input.EOL + invalidInput);
                    System.out.print(menus.PROMPT);
                    break;
            }
        } while (true);
    }

    public void empAlbumOptions() {
        menus.empAlbumOptions();
        System.out.print(menus.PROMPT);
        do {
            String choice = Input.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(input.EOL + ">> New items.Album");
                    storage.addAlbum();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "2":
                    System.out.println(input.EOL + ">> Remove items.Album:");
                    storage.viewAlbums();
                    storage.removeAlbum();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "3":
                    System.out.println(input.EOL + ">> All Albums");
                    storage.viewAlbums();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "4":
                    employeeMenu();
                    break;
                default:
                    System.out.println(menus.DIVIDER + input.EOL + invalidInput);
                    System.out.print(menus.PROMPT);
                    break;
            }
        } while (true);
    }

    public void empCustomerOptions() {
        menus.empCustomerOptions();
        System.out.print(menus.PROMPT);
        do {
            String choice = Input.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(input.EOL + ">> New people.Customer");
                    storage.addCustomer();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "2":
                    System.out.println(input.EOL + ">> Remove people.Customer");
                    storage.removeCustomer();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "3":
                    System.out.println(input.EOL + ">> All Customers");
                    storage.viewCustomer();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "4":
                    employeeMenu();
                    break;
                default:
                    System.out.println(menus.DIVIDER + input.EOL + invalidInput);
                    System.out.print(menus.PROMPT);
                    break;
            }
        } while (true);
    }

    public void customerMenu() {
        menus.customerMenu();
        System.out.print(menus.PROMPT);
        do {
            String choice = Input.input.nextLine();
            switch (choice) {
                case "1":
                    cusGameOptions();
                    break;
                case "2":
                    cusAlbumOptions();
                    break;
                case "3":
                    cusMembershipOptions();
                case "4":
                    inboxMenu();
                    break;
                case "5":
                    mainMenu();
                    break;
                default:
                    System.out.println(menus.DIVIDER + input.EOL + invalidInput);
                    System.out.print(menus.PROMPT);
                    break;
            }
        } while (true);
    }

    public void cusGameOptions() {
        menus.cusGameOptions();
        System.out.print(menus.PROMPT);
        do {
            String choice = Input.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(input.EOL + ">> Rent items.Game");
                    storage.viewGames();
                    storage.rentGame();
                    input.userCheck();
                    cusGameOptions();
                    break;
                case "2":
                    System.out.println(input.EOL + ">> Return items.Game");
                    storage.returnGame();
                    input.userCheck();
                    cusGameOptions();
                    break;
                case "3":
                    System.out.println(input.EOL + ">> Search Games by Genre");
                    storage.searchGames();
                    input.userCheck();
                    cusGameOptions();
                    break;
                case "4":
                    System.out.println(input.EOL + ">> All Games by Year");
                    storage.viewGames();
                    input.userCheck();
                    cusGameOptions();
                    break;
                case "5":
                    System.out.println(input.EOL + ">> All Games by items.properties.Rating");
                    storage.viewGamesByRating();
                    input.userCheck();
                    cusGameOptions();
                    break;
                case "6":
                    employeeMenu();
                    break;
                default:
                    System.out.println(menus.DIVIDER + input.EOL + invalidInput);
                    System.out.print(menus.PROMPT);
                    break;
            }
        } while (true);
    }

    public void cusAlbumOptions() {
        menus.cusAlbumOptions();
        System.out.print(menus.PROMPT);
        do {
            String choice = Input.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(input.EOL + ">> Rent items.Album");
                    storage.viewAlbums();
                    storage.rentAlbum();
                    input.userCheck();
                    cusAlbumOptions();
                    break;
                case "2":
                    System.out.println(input.EOL + ">> Return items.Album:");
                    storage.viewAlbums();
                    storage.returnAlbum();
                    input.userCheck();
                    cusAlbumOptions();
                    break;
                case "3":
                    System.out.println(input.EOL + ">> Search Albums by Year");
                    storage.searchAlbums();
                    input.userCheck();
                    cusAlbumOptions();
                    break;
                case "4":
                    System.out.println(input.EOL + ">> All Albums by Year");
                    storage.viewAlbums();
                    input.userCheck();
                    cusAlbumOptions();
                    break;
                case "5":
                    System.out.println(input.EOL + ">> All Albums by items.properties.Rating");
                    storage.viewAlbumsByRating();
                    input.userCheck();
                    cusAlbumOptions();
                    break;
                case "6":
                    customerMenu();
                    break;
                default:
                    System.out.println(menus.DIVIDER + input.EOL + invalidInput);
                    System.out.print(menus.PROMPT);
                    break;
            }
        } while (true);
    }

    public void cusMembershipOptions() {
        menus.cusMembershipOptions();
        System.out.print(menus.PROMPT);
        do {
            String choice = Input.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(input.EOL + ">> Add people.membership.Membership");
                    storage.addMembership();
                    input.userCheck();
                    cusMembershipOptions();
                    break;
                case "2":
                    System.out.println(input.EOL + ">> Upgrade people.membership.Membership ");
                    storage.upgradeMembership();
                    input.userCheck();
                    cusMembershipOptions();
                    break;
                case "3":
                    customerMenu();
                    break;
                default:
                    System.out.println(menus.DIVIDER + input.EOL + invalidInput);
                    System.out.print(menus.PROMPT);
                    break;
            }
        } while (true);
    }

    public void inboxMenu() {
        menus.inboxMenu();
        System.out.print(menus.PROMPT);
        do {
            String choice = Input.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(input.EOL + ">> View Messages");
                    storage.viewMessages();
                    input.userCheck();
                    inboxMenu();
                    break;
                case "2":
                    System.out.println(input.EOL + ">> Send people.membership.Message");
                    storage.sendMessage();
                    input.userCheck();
                    inboxMenu();
                    break;
                case "3":
                    System.out.println(input.EOL + ">> Delete people.membership.Message");
                    storage.removeMessages();
                    input.userCheck();
                    inboxMenu();
                    break;
                case "4":
                    customerMenu();
                    break;
                default:
                    System.out.println(menus.DIVIDER + input.EOL + invalidInput);
                    System.out.print(menus.PROMPT);
                    break;
            }
        } while (true);
    }
}