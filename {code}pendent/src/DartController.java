public class DartController{
    private Menus menus;
    private Input input = Input.getInstance(); // DO singleton
    Storage storage; // at the moment rental and customer has to have full access to
    // dartcontroller we don't want games and rental to ever need to open dartcontroller

    public DartController(){
        this.menus = new Menus();
        this.storage = new Storage();
    }

    public void run(){
        mainMenu();
    }

    private void exit() {
        //close (public static final scanner (in Helper class))
        Input.getInstance().tearDown();
        System.out.println(menus.EOL + menus.DIVIDER + menus.EOL + "     Good Bye!"+menus.EOL+menus.DIVIDER);
        System.exit(0);
    }
    public void mainMenu(){
        menus.mainMenu();
        do {
            String choice = Input.input.nextLine();
            switch (choice) {
                case "1":
                    menus.managerMenu();
                    break;
                case "2":
                    menus.employeeMenu();
                    break;
                case "3":
                    menus.customerMenu();
                    break;
                case "4":
                    exit();
                    break;
                default:
                    System.out.println(menus.DIVIDER+menus.EOL+"--- Invalid input ---");
                    System.out.print(menus.PROMPT);
                    break;
            }
        } while (true);
    }

    public void managerMenu() {
        do {
            String choice = input.getInput("");
            switch (choice) {
                case "1":
                    System.out.print(menus.EOL+">> New Employee"+menus.EOL);
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
                    System.out.println(menus.EOL+">> All Employees");
                    storage.viewEmployee();
                    input.userCheck();
                    managerMenu();
                    break;
                case "4":
                    menus.mainMenu();
                    break;
                default:
                    System.out.println(menus.DIVIDER+menus.EOL+"--- Invalid input ---");
                    System.out.print(menus.PROMPT);
                    break;
            }
        } while (true);
    }
    // Getters

    // Setters


}