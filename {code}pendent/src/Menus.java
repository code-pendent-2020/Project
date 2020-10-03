import java.util.ArrayList;

public class Menus {
    private String title;
    private String[] options;
    private String prompt;
    public static final String EOL = System.lineSeparator();
    private final String divider = "---------------------";
    private Storage storage = new Storage();

    // Constructor
    public Menus(String title, String[] options, String prompt) {
        this.storage = new Storage();
        this.title = title;
        this.options = options;
        this.prompt = prompt;
    }

    ArrayList<Membership> requestList = null;
    private Helper helper = new Helper();
    private Customer customer = new Customer();
    public Menus() {
    }


    private void exit() {
        //close (public static final scanner (in Helper class))
        helper.input.close();
        System.out.println(EOL + divider + EOL + "     Good Bye!"+EOL+divider);
        System.exit(0);
    }

    public boolean authenticate(String password) { // authenticates passwords
        String enteredPassword = helper.getInput("Enter your password: ");
        return password.equals(enteredPassword);
    }

    // TODO ---------------------------------------MAIN MENU--------------------------------------------------

    public void mainMenu() {
        String[] options = {" 1) Manager Menu", " 2) Employee Menu", " 3) Customer Menu", " 4) Exit System"};
        Menus menu = new Menus(EOL+"------Main-Menu------", options, divider +EOL+"Choose your option: ");
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.prompt);
        do {
            String choice = Helper.input.nextLine();
            switch (choice) {
                case "1":
                    //authManager();
                    managerMenu();
                    break;
                case "2":
                    //authEmployee();
                    employeeMenu();
                    break;
                case "3":
                    customerMenu();
                    break;
                case "4":
                    exit();
                    break;
                default:
                    System.out.println(divider +EOL+"--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }

    // TODO ---------------------------------------MANAGER MENU--------------------------------------------------

    public void managerMenu() {
        String[] options = {" 1) Register Employee", " 2) Remove Employee", " 3) View Employees", " 4) Main Menu"};
        Menus menu = new Menus(EOL+"-----Manager-Menu-----", options, divider+EOL+"Choose your option: ");
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.prompt);
        do {
            String choice = Helper.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.print(EOL+">> New Employee"+EOL);
                    storage.addEmployee();
                    managerMenu();
                    break;
                case "2":
                    storage.viewEmployee();
                    storage.removeEmployee();
                    managerMenu();
                    break;
                case "3":
                    System.out.println(EOL+">> All Employees");
                    storage.viewEmployee();
                    managerMenu();
                    break;
                case "4":
                    mainMenu();
                    break;
                default:
                    System.out.println(divider+EOL+"--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }

    public void authManager() {
        String password = "admin1234";
        Helper Authorize = new Helper();
        Boolean authSuccess = authenticate(password);

        if (authSuccess) {
            managerMenu();
        } else {
            System.out.println(EOL+"*** Wrong password *** "+EOL);
            mainMenu();
        }
    }

    public void membershipRequestList(){
        for ( Membership request : requestList ){
            System.out.println("The following Customer has requested a membership: ");
            System.out.println("Customer : " + request.getName() + "\n Requesting: " + request.getType() + " membership");
            String requestListAnswer = helper.getInput("(Y/N): ");
            if (requestListAnswer.equalsIgnoreCase("Y")){
                for ( Customer requested : storage.getCustomers()){
                    if(requested.getName().equalsIgnoreCase(request.getName())){
                        requested.setMembershipType(request.getType());
                    }
                }
            } else {
                System.out.println("Okay, fair enough");
            }
        } requestList = null;
    }

    // TODO ---------------------------------------EMPLOYEE MENU--------------------------------------------------
    public void employeeMenu() {
        if (requestList != null) {
            membershipRequestList();
        }
        String[] options = {" 1) Game Options", " 2) Album Options", " 3) Customer Options", " 4) Total Rent Profit", " 5) Main Menu"};
        Menus menu = new Menus("\n----Employee-Menu----", options, divider+"\nChoose your option: ");
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.prompt);
        do {
            String choice = Helper.input.nextLine();
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
                    System.out.println("TODO!");
                    employeeMenu();
                    break;
                case "5":
                    mainMenu();
                    break;
                default:
                    System.out.println(divider+"\n--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }

    // TODO ---------------------------------------Employee SUBMENUS--------------------------------------------------

    public void empGameOptions() {
        String[] options = {" 1) Register Game", " 2) Remove Game", " 3) View Games", " 4) Employee Menu"};
        Menus menu = new Menus(EOL+"-----Game-Options----", options, divider+EOL+"Choose your option: ");
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.prompt);
        do {
            String choice = Helper.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(EOL+">> New Game");
                    storage.addNewGame();
                    mainMenu();
                    break;
                case "2":
                    System.out.println(EOL+">> Remove Game");
                    storage.removeGame();
                    mainMenu();
                    break;
                case "3":
                    System.out.println(EOL+">> All Games");
                    storage.viewAll();
                    mainMenu();
                    break;
                case "4":
                    employeeMenu();
                    break;
                default:
                    System.out.println(divider+EOL+"--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }

    public void empAlbumOptions() {
        String[] options = {" 1) Register Album", " 2) Remove Album", " 3) View Albums", " 4) Employee Menu"};
        Menus menu = new Menus(EOL+"----Album-Options----", options, divider+EOL+"Choose your option: ");
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.prompt);
        do {
            String choice = Helper.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(EOL+">> New Album");
                    storage.addAlbum();
                    employeeMenu();
                    break;
                case "2":
                    System.out.println(EOL+">> Remove Album:");
                    storage.viewAlbums();
                    storage.removeAlbum();
                    employeeMenu();
                    break;
                case "3":
                    System.out.println(EOL+">> All Albums");
                    storage.viewAlbums();
                    employeeMenu();
                    break;
                case "4":
                    employeeMenu();
                    break;
                default:
                    System.out.println(divider+EOL+"--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }

    public void empCustomerOptions() {
        String[] options = {" 1) Register Customer", " 2) Remove Customer", " 3) View Customers", " 4) Employee Menu"};
        Menus menu = new Menus(EOL+"---Customer-Options---", options, divider+EOL+"Choose your option: ");
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.prompt);
        do {
            String choice = Helper.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(EOL+">> New Customer");
                    storage.addCustomer();
                    employeeMenu();
                    break;
                case "2":
                    System.out.println(EOL+">> Remove Customer");
                    storage.removeCustomer();
                    employeeMenu();
                    break;
                case "3":
                    System.out.println(EOL+">> All Customers");
                    storage.viewCustomer();
                    employeeMenu();
                    break;
                case "4":
                    employeeMenu();
                    break;
                default:
                    System.out.println(divider+EOL+"--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }

    //-------------------------------------------------------------------------------------------------------

    public void authEmployee() {
        String password = "password123";
        Helper Authorize = new Helper();
        Boolean authSuccess = authenticate(password);

        if (authSuccess) {
            employeeMenu();
        } else {
            System.out.println(EOL+"*** Wrong password ***"+EOL);
            mainMenu();
        }
    }

    // TODO ---------------------------------------CUSTOMER MENU--------------------------------------------------

    public void customerMenu() {
        String[] options = {" 1) Game Options", " 2) Album Options", " 3) Add Membership", " 4) Upgrade Membership", " 5) Inbox", " 6) Main Menu"};
        Menus menu = new Menus(EOL+"----Customer-Menu----", options, divider+EOL+"Choose your option: ");
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.prompt);
        do {
            String choice = Helper.input.nextLine();
            switch (choice) {
                case "1":
                    gameOptions();
                    break;
                case "2":
                    albumOptions();
                    break;
                case "3":
                    System.out.println(EOL+">> Add membership");
                    // requestList = addMembership();
                    customerMenu();
                    break;
                case "4":
                    System.out.println(EOL+">> Upgrade membership");
                    System.out.println("Method todo");
                    break;
                case "5":
                    System.out.println(EOL+">> Inbox");
                    inboxMenu();
                    break;
                case "6":
                    mainMenu();
                    break;
                default:
                    System.out.println(divider+EOL+"--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }
// TODO ---------------------------------------CUSTOMER SUBMENUS--------------------------------------------------

    public void gameOptions() {
        String[] options = {" 1) Rent Game", " 2) Return Game", " 3) View Games", " 4) Search Games", " 5) Customer Menu"};
        Menus menu = new Menus(EOL+"---------Game--------", options, divider+EOL+"Choose your option: ");
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.prompt);
        do {
            String choice = Helper.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(EOL+">> Rent Game");
                    storage.rentGame();
                    customerMenu();
                    break;
                case "2":
                    System.out.println(EOL+">> Return Game");
                    System.out.println("add the method for now returns you to main menu");
                    customerMenu();
                    break;
                case "3":
                    System.out.println(EOL+">> All Games");
                    System.out.println("add the method for now returns you to main menu");
                    customerMenu();
                    break;
                case "4":
                    System.out.println(EOL+">> Search Games");
                    System.out.println("add the method for now returns you to main menu");
                    customerMenu();
                    break;
                case "5":
                    employeeMenu();
                    break;
                default:
                    System.out.println(divider+EOL+"--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }

    public void albumOptions() {
        String[] options = {" 1) Rent Album", " 2) Return Album", " 3) View Albums", " 4) Search Albums", " 5) Customer Menu"};
        Menus menu = new Menus(EOL+"--------Album--------", options, divider+EOL+"Choose your option: ");
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.prompt);
        do {
            String choice = Helper.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(EOL+">> Rent Album");
                    storage.rentAlbum();
                    employeeMenu();
                    break;
                case "2":
                    System.out.println(EOL+">> Return Album:");
                    storage.viewAlbums();
                    storage.returnAlbum();
                    employeeMenu();
                    break;
                case "3":
                    System.out.println(EOL+">> All Albums");
                    storage.viewAlbums();
                    employeeMenu();
                    break;
                case "4":
                    System.out.println(EOL+">> Search Albums");
                    storage.searchAlbums();
                    employeeMenu();
                    break;
                case "5":
                    employeeMenu();
                    break;
                default:
                    System.out.println(divider+EOL+"--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }

    // TODO ---------------------------------------Messenger/Inbox Menu--------------------------------------------------

    public void inboxMenu() {
        String[] options = {" 1) View messages", " 2) Send Message", " 3) Delete Message", " 4) Main Menu"};
        Menus menu = new Menus("--------Inbox--------", options, divider+"\nChoose your option: "); //add the name of the user whose inbox we are using to title
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.prompt);
        do {
            String choice = Helper.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(EOL+">> View Messages");
                    System.out.println("add the method for now returns you to main menu");
                    mainMenu();
                    break;
                case "2":
                    System.out.println(EOL+">> Send Message");
                    System.out.println("add the method for now returns you to main menu");
                    mainMenu();
                    break;
                case "3":
                    System.out.println(EOL+">> Delete Message");
                    System.out.println("add the method for now returns you to main menu");
                    mainMenu();
                    break;
                case "4":
                    mainMenu();
                    break;
                default:
                    System.out.println(divider+EOL+"--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }

}
