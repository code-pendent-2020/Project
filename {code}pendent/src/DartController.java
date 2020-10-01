import java.util.ArrayList;

public class DartController extends Storage{
    private String title;
    private String[] options;
    private String prompt;
    private String dividerOne = "---------------------";

    ArrayList<Membership> requestList = null;
    private Helper helper = new Helper();
    private Customer customer = new Customer();
    public DartController() {
    }

    public DartController(String title, String[] options, String prompt) {
        this.title = title;
        this.options = options;
        this.prompt = prompt;
    }

    private void exit() {
        //close (public static final scanner (in Helper class))
        helper.input.close();
        System.out.println("\n" + dividerOne + "\n     Good Bye!\n"+dividerOne);
        System.exit(0);
    }

    public boolean authenticate(String password) { // authenticates passwords
        String enteredPassword = helper.getInput("Enter your password: ");
        return password.equals(enteredPassword);
    }

    // TODO ---------------------------------------MAIN MENU--------------------------------------------------

    public void mainMenu() {
        String[] options = {" 1) Manager Menu", " 2) Employee Menu", " 3) Customer Menu", " 4) Exit System"};
        DartController menu = new DartController("\n------Main-Menu------", options, dividerOne +"\nChoose your option: ");
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
                    System.out.println(dividerOne +"\n--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }

    // TODO ---------------------------------------MANAGER MENU--------------------------------------------------

    public void managerMenu() {
        String[] options = {" 1) Register Employee", " 2) Remove Employee", " 3) View Employees", " 4) Main Menu"};
        DartController menu = new DartController("\n-----Manager-Menu-----", options, dividerOne+"\nChoose your option: ");
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.prompt);
        do {
            String choice = Helper.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("\n>> New Employee\n");
                    addEmployee();
                    managerMenu();
                    break;
                case "2":
                    viewEmployee();
                    removeEmployee();
                    managerMenu();
                    break;
                case "3":
                    System.out.println("\n>> All Employees");
                    viewEmployee();
                    managerMenu();
                    break;
                case "4":
                    mainMenu();
                    break;
                default:
                    System.out.println(dividerOne+"\n--- Invalid input ---");
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
            System.out.println("\n*** Wrong password *** \n");
            mainMenu();
        }
    }

    public void membershipRequestList(){
        for ( Membership request : requestList ){
            System.out.println("The following Customer has requested a membership: ");
            System.out.println("Customer : " + request.getName() + "\n Requesting: " + request.getType() + " membership");
            String requestListAnswer = helper.getInput("(Y/N): ");
            if (requestListAnswer.equalsIgnoreCase("Y")){
                for ( Customer requested : getCustomers()){
                    if(requested.getName().equalsIgnoreCase(request.getName())){
                        requested.setMembershipType(request.getType());
                    }
                }
            } else {
                System.out.println("Okay, fair enough");
            }
        }
    }

    // todo EMPLOYEE MENU
    // TODO ---------------------------------------EMPLOYEE MENU--------------------------------------------------
    public void employeeMenu() {
        membershipRequestList();
        String[] options = {" 1) Game Options", " 2) Album Options", " 3) Customer Options", " 4) Total Rent Profit", " 5) Main Menu"};
        DartController menu = new DartController("\n----Employee-Menu----", options, dividerOne+"\nChoose your option: ");
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
                    customerOptions();
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
                    System.out.println(dividerOne+"\n--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }

    // TODO ---------------------------------------Employee SUBMENUS--------------------------------------------------

    public void gameOptions() {
        String[] options = {" 1) Register Game", " 2) Remove Game", " 3) View Games", " 4) Employee Menu"};
        DartController menu = new DartController("\n-----Game-Options----", options, dividerOne+"\nChoose your option: ");
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.prompt);
        do {
            String choice = Helper.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("\n>> New Game");
                    System.out.println("add the method for now returns you to main menu");
                    mainMenu();
                    break;
                case "2":
                    System.out.println("\n>> Remove Game");
                    System.out.println("add the method for now returns you to main menu");
                    mainMenu();
                    break;
                case "3":
                    System.out.println("\n>> All Games");
                    System.out.println("add the method for now returns you to main menu");
                    mainMenu();
                    break;
                case "4":
                    employeeMenu();
                    break;
                default:
                    System.out.println(dividerOne+"\n--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }
    public void albumOptions() {
        String[] options = {" 1) Register Album", " 2) Remove Album", " 3) View Albums", " 4) Employee Menu"};
        DartController menu = new DartController("\n----Album-Options----", options, dividerOne+"\nChoose your option: ");
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.prompt);
        do {
            String choice = Helper.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("\n>> New Album");
                    addAlbum();
                    employeeMenu();
                    break;
                case "2":
                    System.out.println("\n>> Remove Album:");
                    viewAlbums();
                    removeAlbum();
                    employeeMenu();
                    break;
                case "3":
                    System.out.println("\n>> All Albums");
                    viewAlbums();
                    employeeMenu();
                    break;
                case "4":
                    employeeMenu();
                    break;
                default:
                    System.out.println(dividerOne+"\n--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }
    public void customerOptions() {
        String[] options = {" 1) Register Customer", " 2) Remove Customer", " 3) View Customers", " 4) Employee Menu"};
        DartController menu = new DartController("\n---Customer-Options---", options, dividerOne+"\nChoose your option: ");
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.prompt);
        do {
            String choice = Helper.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("\n>> New Customer");
                    addCustomer();
                    employeeMenu();
                    break;
                case "2":
                    System.out.println("\n>> Remove Customer");
                    removeCustomer();
                    employeeMenu();
                    break;
                case "3":
                    System.out.println("\n>> All Customers");
                    viewCustomer();
                    employeeMenu();
                    break;
                case "4":
                    employeeMenu();
                    break;
                default:
                    System.out.println(dividerOne+"\n--- Invalid input ---");
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
            System.out.println("\n*** Wrong password ***\n");
            mainMenu();
        }
    }

    // TODO ---------------------------------------CUSTOMER MENU--------------------------------------------------

    public void customerMenu() {
        String[] options = {" 1) Rent a Game", " 2) Return a Game", " 3) Rent Album", " 4) Return Album", " 5) Add Membership", " 6) Upgrade Membership", " 7) Inbox", " 8) Main Menu"};
        DartController menu = new DartController("\n----Customer-Menu----", options, dividerOne+"\nChoose your option: ");
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.prompt);
        do {
            String choice = Helper.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("\n>> Rent Game");
                    rentGame();
                    break;
                case "2":
                    System.out.println("\n>> Return Game");
                    System.out.println("add the method for now returns you to main menu");
                    mainMenu();
                    break;
                case "3":
                    viewAlbums();
                    System.out.println("\n>> Rent Album");
                    rentAlbum();
                    mainMenu();
                    break;
                case "4":
                    System.out.println("\n>> Return Album");
                    returnAlbum();
                    mainMenu();
                    break;
                case "5":
                    System.out.println("\n>> Add membership");
                    requestList = addMembership();
                    customerMenu();
                    break;
                case "6":
                    System.out.println("\n>> Upgrade membership");
                    System.out.println("Method todo");
                    break;
                case "7":
                    System.out.println("\n>> Inbox");
                    System.out.println("add the method for now returns you to main menu");
                    mainMenu();
                    break;
                case "8":
                    mainMenu();
                    break;
                default:
                    System.out.println(dividerOne+"\n--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }


    // TODO ---------------------------------------Messenger/Inbox Menu--------------------------------------------------

    public void inboxMenu() {
        String[] options = {" 1) View messages", " 2) Send Message", " 3) Main Menu"};
        DartController menu = new DartController("--Inbox--", options, dividerOne+"\nChoose your option: "); //add the name of the user whose inbox we are using to title
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.prompt);
        do {
            String choice = Helper.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("\n>> View Messages");
                    System.out.println("add the method for now returns you to main menu");
                    mainMenu();
                    break;
                case "2":
                    System.out.println("\n>> Send Message");
                    System.out.println("add the method for now returns you to main menu");
                    mainMenu();
                    break;
                case "3":
                    mainMenu();
                    break;
                default:
                    System.out.println(dividerOne+"\n--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }
    public void run(){
        mainMenu();
    }
}