import java.util.ArrayList;

public class DartController {
    private Menus menus;
    private Input input = Input.getInstance(); // DO singleton
    Storage storage;

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
        System.out.println(menus.EOL + menus.DIVIDER + menus.EOL + "     Good Bye!" + menus.EOL + menus.DIVIDER);
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
            System.out.println(menus.EOL + "*** Wrong password *** " + menus.EOL);
            mainMenu();
        }
    }

    public void authEmployee() {
        String password = "password123";

        Boolean authSuccess = authenticate(password);

        if (authSuccess) {
            employeeMenu();
        } else {
            System.out.println(menus.EOL + "*** Wrong password ***" + menus.EOL);
            mainMenu();
        }
    }

    private void membershipRequestList(){
        for ( Membership request : requestList ){
            System.out.println("The following Customer has requested a membership: ");
            System.out.println("Customer : " + request.getName() + "\n Requesting: " + request.getType() + " membership");
            String requestListAnswer = input.getInput("(Y/N): ");
            if (requestListAnswer.equalsIgnoreCase("Y")){
                for ( Customer requested : storage.getCustomers()){
                    if(requested.getName().equalsIgnoreCase(request.getName())){
                        requested.setMembershipType(request.getType());
                    }
                }
            } else {
                System.out.println("Okay, fair enough");
            }
        } requestList.clear();
    }

    public void mainMenu() {
        menus.mainMenu();
        do {
            String choice = Input.input.nextLine();
            switch (choice) {
                case "1":
                    managerMenu();
                    break;
                case "2":
                    employeeMenu();
                    break;
                case "3":
                    customerMenu();
                    break;
                case "4":
                    exit();
                    break;
                default:
                    System.out.println(menus.DIVIDER + menus.EOL + "--- Invalid input ---");
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
                    System.out.print(menus.EOL + ">> New Employee" + menus.EOL);
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
                    System.out.println(menus.EOL + ">> All Employees");
                    storage.viewEmployee();
                    input.userCheck();
                    managerMenu();
                    break;
                case "4":
                    mainMenu();
                    break;
                default:
                    System.out.println(menus.DIVIDER + menus.EOL + "--- Invalid input ---");
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
                    System.out.println("\n>> Total Rent Profit");
                    Rental rental = new Rental();
                    rental.totalProfit();
                    System.out.println("TODO!");
                    employeeMenu();
                    break;
                case "5":
                    mainMenu();
                    break;
                default:
                    System.out.println(menus.DIVIDER + "\n--- Invalid input ---");
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
                    System.out.println(menus.EOL + ">> New Game");
                    storage.addNewGame();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "2":
                    System.out.println(menus.EOL + ">> Remove Game:");
                    storage.viewGames();
                    storage.removeGame();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "3":
                    System.out.println(menus.EOL + ">> All Games");
                    storage.viewGames();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "4":
                    employeeMenu();
                    break;
                default:
                    System.out.println(menus.DIVIDER + menus.EOL + "--- Invalid input ---");
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
                    System.out.println(menus.EOL + ">> New Album");
                    storage.addAlbum();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "2":
                    System.out.println(menus.EOL + ">> Remove Album:");
                    storage.viewAlbums();
                    storage.removeAlbum();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "3":
                    System.out.println(menus.EOL + ">> All Albums");
                    storage.viewAlbums();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "4":
                    employeeMenu();
                    break;
                default:
                    System.out.println(menus.DIVIDER + menus.EOL + "--- Invalid input ---");
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
                    System.out.println(menus.EOL + ">> New Customer");
                    storage.addCustomer();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "2":
                    System.out.println(menus.EOL + ">> Remove Customer");
                    storage.removeCustomer();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "3":
                    System.out.println(menus.EOL + ">> All Customers");
                    storage.viewCustomer();
                    input.userCheck();
                    employeeMenu();
                    break;
                case "4":
                    employeeMenu();
                    break;
                default:
                    System.out.println(menus.DIVIDER + menus.EOL + "--- Invalid input ---");
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
                    input.userCheck();
                    break;
                case "2":
                    cusAlbumOptions();
                    input.userCheck();
                    break;
                case "3":
                    cusMembershipOptions();
                    input.userCheck();
                case "4":
                    input.userCheck();
                    inboxMenu();
                    break;
                case "5":
                    mainMenu();
                    break;
                default:
                    System.out.println(menus.DIVIDER + menus.EOL + "--- Invalid input ---");
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
                    System.out.println(menus.EOL + ">> Rent Game");
                    storage.viewGames();
                    storage.rentGame();
                    input.userCheck();
                    cusGameOptions();
                    break;
                case "2":
                    System.out.println(menus.EOL + ">> Return Game");
                    storage.viewGames();
                    storage.returnGame();
                    input.userCheck();
                    cusGameOptions();
                    break;
                case "3":
                    System.out.println(menus.EOL + ">> Search Games by Genre");
                    storage.searchGames();
                    cusGameOptions();
                    input.userCheck();
                    break;
                case "4":
                    System.out.println(menus.EOL + ">> All Games by Year");
                    storage.viewGames();
                    cusGameOptions();
                    input.userCheck();
                    break;
                case "5":
                    System.out.println(menus.EOL + ">> All Games by Rating");
                    storage.viewGamesByRating();
                    cusGameOptions();
                    input.userCheck();
                    break;
                case "6":
                    employeeMenu();
                    input.userCheck();
                    break;
                default:
                    System.out.println(menus.DIVIDER + menus.EOL + "--- Invalid input ---");
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
                    System.out.println(menus.EOL + ">> Rent Album");
                    storage.viewAlbums();
                    storage.rentAlbum();
                    input.userCheck();
                    cusAlbumOptions();
                    break;
                case "2":
                    System.out.println(menus.EOL + ">> Return Album:");
                    storage.viewAlbums();
                    storage.returnAlbum();
                    input.userCheck();
                    cusAlbumOptions();
                    break;
                case "3":
                    System.out.println(menus.EOL + ">> Search Albums by Year");
                    storage.searchAlbums();
                    cusAlbumOptions();
                    break;
                case "4":
                    System.out.println(menus.EOL + ">> All Albums by Year");
                    storage.viewAlbums();
                    cusAlbumOptions();
                    break;
                case "5":
                    System.out.println(menus.EOL + ">> All Albums by Rating");
                    storage.viewAlbumsByRating();
                    cusAlbumOptions();
                    break;
                case "6":
                    customerMenu();
                    input.userCheck();
                    break;
                default:
                    System.out.println(menus.DIVIDER + menus.EOL + "--- Invalid input ---");
                    System.out.print(menus.PROMPT);
                    break;
            }
        } while (true);
    }

    public void cusMembershipOptions(){
        menus.cusMembershipOptions();
        System.out.print(menus.PROMPT);
        do {
            String choice = Input.input.nextLine();
        switch (choice) {
            case "1":
                System.out.println(menus.EOL + ">> Add Membership");
                storage.addMembership();
                cusMembershipOptions();
                break;
            case "2":
                System.out.println(menus.EOL + ">> Upgrade Membership ");
                storage.upgradeMembership();
                cusMembershipOptions();
                break;
            case "3":
                customerMenu();
                break;
            default:
                System.out.println(menus.DIVIDER + menus.EOL + "--- Invalid input ---");
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
                    System.out.println(menus.EOL + ">> View Messages");
                    storage.viewMessages();
                    inboxMenu();

                    break;
                case "2":
                    System.out.println(menus.EOL + ">> Send Message");
                    storage.sendMessage();
                    inboxMenu();

                    break;
                case "3":
                    System.out.println(menus.EOL + ">> Delete Message");
                    storage.removeMessages();
                    inboxMenu();

                    break;
                case "4":
                    customerMenu();
                    break;
                default:
                    System.out.println(menus.DIVIDER + menus.EOL + "--- Invalid input ---");
                    System.out.print(menus.PROMPT);
                    break;
            }
        } while (true);
    }

    // Getters

    // Setters
}