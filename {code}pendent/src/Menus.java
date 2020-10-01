public class Menus extends DartController{
    private String title;
    private String[] options;
    private String prompt;
    private String dividerOne = "---------------------";

    private Helper helper = new Helper();
    public Menus() {
    }

    public Menus(String title, String[] options, String prompt) {
        this.title = title;
        this.options = options;
        this.prompt = prompt;
    }

    private void exit() {
        helper.input.close();
        System.out.println("\n" + dividerOne + "\n     Good Bye!\n"+dividerOne);
        System.exit(0);
    }

    public boolean authenticate(String password) { // authenticates passwords
        String enteredPassword = helper.getInput("Enter your password: ");
        return password.equals(enteredPassword);
    }

    // todo MAIN MENU

    public void mainMenu() {
        String[] options = {" 1) Manager Menu", " 2) Employee Menu", " 3) Customer Menu", " 4) Exit System"};
        Menus menu = new Menus("\n------Main-Menu------", options, dividerOne +"\nChoose your option: ");
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

    // todo MANAGER MENU

    public void managerMenu() {
        String[] options = {" 1) Register Employee", " 2) Remove Employee", " 3) View Employees", " 4) Main Menu"};
        Menus menu = new Menus("\n-----Manager-Menu-----", options, dividerOne+"\nChoose your option: ");
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

    // todo EMPLOYEE MENU

    public void employeeMenu() {
        String[] options = {" 1) Register Game", " 2) Remove Game", " 3) View Games", " 4) Register Album",
                " 5) Remove Albums", " 6) View Albums", " 7) Register Customer", " 8) Remove Customer",
                " 9) View Customers", " 10) Total Rent Profit", " 11) Main Menu"};
        Menus menu = new Menus("\n----Employee-Menu----", options, dividerOne+"\nChoose your option: ");
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
                    employeeMenu();
                    break;
                case "2":
                    System.out.println("\n>> Remove Game");
                    System.out.println("add the method for now returns you to main menu");
                    employeeMenu();
                    break;
                case "3":
                    System.out.println("\n>> All Games");
                    System.out.println("add the method for now returns you to main menu");
                    employeeMenu();
                    break;
                case "4":
                    System.out.println("\n>> New Album");
                    addAlbum();
                    employeeMenu();
                    break;
                case "5":
                    System.out.println("\n>> Remove Album:");
                    viewAlbums();
                    removeAlbum();
                    employeeMenu();
                    break;
                case "6":
                    System.out.println("\n>> All Albums");
                    viewAlbums();
                    employeeMenu();
                    break;
                case "7":
                    System.out.println("\n>> New Customer");
                    addCustomer();
                    employeeMenu();
                    break;
                case "8":
                    System.out.println("\n>> Remove Customer");
                    removeCustomer();
                    employeeMenu();
                    break;
                case "9":
                    System.out.println("\n>> All Customers");
                    viewCustomer();
                    employeeMenu();
                    break;
                case "10":
                    System.out.println("\n>> View Total Rent Profit");
                    System.out.println("add the method for now returns you to main menu");
                    employeeMenu();
                    break;
                case "11":
                    mainMenu();
                    break;
                default:
                    System.out.println(dividerOne+"\n--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }

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

    // todo CUSTOMER MENU

    public void customerMenu() {
        String[] options = {" 1) Rent a Game", " 2) Return a Game", " 3) Rent Album", " 4) Return Album", " 5) Messages", " 6) Main Menu"};
        Menus menu = new Menus("\n----Customer-Menu----", options, dividerOne+"\nChoose your option: ");
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.prompt);
        do {
            String choice = Helper.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(">> Rent Game");
                    rentGame();
                    break;
                case "2":
                    System.out.println(">> Return Game");
                    System.out.println("add the method for now returns you to main menu");
                    mainMenu();
                    break;
                case "3":
                    viewAlbums();
                    System.out.println(">> Rent Album");
                    rentAlbum();
                    mainMenu();
                    break;
                case "4":
                    System.out.println(">> Return Album");
                    System.out.println("add the method for now returns you to main menu");
                    mainMenu();
                    break;
                case "5":
                    System.out.println(">> Inbox");
                    System.out.println("add the method for now returns you to main menu");
                    mainMenu();
                    break;
                case "6":
                    mainMenu();
                    break;
                default:
                    System.out.println(dividerOne+"\n--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }

    // todo INBOX MENU

    public void inboxMenu() {
        String[] options = {" 1) View messages", " 2) Send Message", " 3) Return to Main Menu"};
        Menus menu = new Menus("--Inbox--", options, dividerOne+"\nChoose your option: "); //add the name of the user whose inbox we are using to title
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
