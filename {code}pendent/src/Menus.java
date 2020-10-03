import java.util.ArrayList;

public class Menus {
    private String title;
    private String[] options;
    private Storage storage = new Storage();
    private final Input helper = Input.getInstance();
    public static final String PROMPT = "---------------------\nChoose your option: ";
    public static final String EOL = System.lineSeparator();
    public static final String DIVIDER = "---------------------";

    // Constructor
    public Menus(String title, String[] options) {
        this.storage = new Storage();
        this.title = title;
        this.options = options;
    }

    ArrayList<Membership> requestList = null;
    private Customer customer = new Customer();
    public Menus() {
    }


    private void exit() {
        //close (public static final scanner (in Helper class))
        Input.getInstance().tearDown();
        System.out.println(EOL + DIVIDER + EOL + "     Good Bye!"+EOL+DIVIDER);
        System.exit(0);
    }

    public boolean authenticate(String password) { // authenticates passwords
        String enteredPassword = helper.getInput("Enter your password: ");
        return password.equals(enteredPassword);
    }

    // TODO ---------------------------------------MAIN MENU--------------------------------------------------

    public void mainMenu() {
        String[] options = {" 1) Manager Menu", " 2) Employee Menu", " 3) Customer Menu", " 4) Exit System"};
        Menus menu = new Menus(EOL+"------Main-Menu------", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.PROMPT);
    }

    // TODO ---------------------------------------MANAGER MENU--------------------------------------------------

    public void managerMenu() {
        String[] options = {" 1) Register Employee", " 2) Remove Employee", " 3) View Employees", " 4) Main Menu"};
        Menus menu = new Menus(EOL+"-----Manager-Menu-----", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
    }

    // Move me daddy
    public void authManager() { // Why is menu auth?
        String password = "admin1234";
        boolean authSuccess = authenticate(password);

        if (authSuccess) {
            managerMenu();
        } else {
            System.out.println(EOL+"*** Wrong password *** "+EOL);
            mainMenu();
        }
    }

    private void membershipRequestList(){
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
        Menus menu = new Menus("\n----Employee-Menu----", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.PROMPT);
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
                    System.out.println("TODO!");
                    employeeMenu();
                    break;
                case "5":
                    mainMenu();
                    break;
                default:
                    System.out.println(DIVIDER+"\n--- Invalid input ---");
                    System.out.print(menu.PROMPT);
                    break;
            }
        } while (true);
    }

    // TODO ---------------------------------------Employee SUBMENUS--------------------------------------------------

    public void empGameOptions() {
        String[] options = {" 1) Register Game", " 2) Remove Game", " 3) View Games", " 4) Employee Menu"};
        Menus menu = new Menus(EOL+"-----Game-Options----", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.PROMPT);
    }

    public void empAlbumOptions() {
        String[] options = {" 1) Register Album", " 2) Remove Album", " 3) View Albums", " 4) Employee Menu"};
        Menus menu = new Menus(EOL+"----Album-Options----", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.PROMPT);
        do {
            String choice = Input.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(EOL+">> New Album");
                    storage.addAlbum();
                    helper.userCheck();
                    employeeMenu();
                    break;
                case "2":
                    System.out.println(EOL+">> Remove Album:");
                    storage.viewAlbums();
                    storage.removeAlbum();
                    helper.userCheck();
                    employeeMenu();
                    break;
                case "3":
                    System.out.println(EOL+">> All Albums");
                    storage.viewAlbums();
                    helper.userCheck();
                    employeeMenu();
                    break;
                case "4":
                    employeeMenu();
                    break;
                default:
                    System.out.println(DIVIDER+EOL+"--- Invalid input ---");
                    System.out.print(menu.PROMPT);
                    break;
            }
        } while (true);
    }

    public void empCustomerOptions() {
        String[] options = {" 1) Register Customer", " 2) Remove Customer", " 3) View Customers", " 4) Employee Menu"};
        Menus menu = new Menus(EOL+"---Customer-Options---", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.PROMPT);
        do {
            String choice = Input.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(EOL+">> New Customer");
                    storage.addCustomer();
                    helper.userCheck();
                    employeeMenu();
                    break;
                case "2":
                    System.out.println(EOL+">> Remove Customer");
                    storage.removeCustomer();
                    helper.userCheck();
                    employeeMenu();
                    break;
                case "3":
                    System.out.println(EOL+">> All Customers");
                    storage.viewCustomer();
                    helper.userCheck();
                    employeeMenu();
                    break;
                case "4":
                    employeeMenu();
                    break;
                default:
                    System.out.println(DIVIDER+EOL+"--- Invalid input ---");
                    System.out.print(menu.PROMPT);
                    break;
            }
        } while (true);
    }

    //-------------------------------------------------------------------------------------------------------

    public void authEmployee() {
        String password = "password123";

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
        Menus menu = new Menus(EOL+"----Customer-Menu----", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.PROMPT);
        do {
            String choice = Input.input.nextLine();
            switch (choice) {
                case "1":
                    gameOptions();
                    helper.userCheck();
                    break;
                case "2":
                    albumOptions();
                    helper.userCheck();
                    break;
                case "3":
                    System.out.println(EOL+">> Add membership");
                    // requestList = addMembership();
                    customerMenu();
                    helper.userCheck();
                    break;
                case "4":
                    System.out.println(EOL+">> Upgrade membership");
                    System.out.println("Method todo");
                    break;
                case "5":
                    System.out.println(EOL+">> Inbox");
                    inboxMenu();
                    helper.userCheck();
                    break;
                case "6":
                    mainMenu();
                    break;
                default:
                    System.out.println(DIVIDER+EOL+"--- Invalid input ---");
                    System.out.print(menu.PROMPT);
                    break;
            }
        } while (true);
    }
// TODO ---------------------------------------CUSTOMER SUBMENUS--------------------------------------------------

    public void gameOptions() {
        String[] options = {" 1) Rent Game", " 2) Return Game", " 3) View Games", " 4) Search Games", " 5) Customer Menu"};
        Menus menu = new Menus(EOL+"---------Game--------", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.PROMPT);
        do {
            String choice = Input.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(EOL+">> Rent Game");
                    storage.rentGame();
                    helper.userCheck();
                    customerMenu();
                    break;
                case "2":
                    System.out.println(EOL+">> Return Game");
                    System.out.println("add the method for now returns you to main menu");
                    customerMenu();
                    helper.userCheck();
                    break;
                case "3":
                    System.out.println(EOL+">> All Games");
                    System.out.println("add the method for now returns you to main menu");
                    customerMenu();
                    helper.userCheck();
                    break;
                case "4":
                    System.out.println(EOL+">> Search Games");
                    System.out.println("add the method for now returns you to main menu");
                    customerMenu();
                    helper.userCheck();
                    break;
                case "5":
                    employeeMenu();
                    helper.userCheck();
                    break;
                default:
                    System.out.println(DIVIDER+EOL+"--- Invalid input ---");
                    System.out.print(menu.PROMPT);
                    break;
            }
        } while (true);
    }

    public void albumOptions() {
        String[] options = {" 1) Rent Album", " 2) Return Album", " 3) View Albums", " 4) Search Albums", " 5) Customer Menu"};
        Menus menu = new Menus(EOL+"--------Album--------", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.PROMPT);
        do {
            String choice = Input.input.nextLine();
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
                    System.out.println(DIVIDER+EOL+"--- Invalid input ---");
                    System.out.print(menu.PROMPT);
                    break;
            }
        } while (true);
    }

    // TODO ---------------------------------------Messenger/Inbox Menu--------------------------------------------------

    public void inboxMenu() {
        String[] options = {" 1) View messages", " 2) Send Message", " 3) Delete Message", " 4) Main Menu"};
        Menus menu = new Menus("--------Inbox--------", options); //add the name of the user whose inbox we are using to title
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.PROMPT);
        do {
            String choice = Input.input.nextLine();
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
                    System.out.println(DIVIDER+EOL+"--- Invalid input ---");
                    System.out.print(menu.PROMPT);
                    break;
            }
        } while (true);
    }

}
