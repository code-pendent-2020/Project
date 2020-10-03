public class DartController{
    private Menus menus;
    private Input input = Input.getInstance() // DO singleton
    Storage storage; // at the moment rental and customer has to have full access to
    // dartcontroller we don't want games and rental to ever need to open dartcontroller

    public DartController(){
        this.menus = new Menus();
        this.storage = new Storage();
    }

    public void run(){
        menus.mainMenu();
    }

    public void mainMenuChoice(){
        do {
            String choice = Input.input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(menus.EOL+">> New Game");
                    storage.addNewGame();
                    Input.userCheck();
                    employeeMenu();
                    break;
                case "2":
                    System.out.println(menus.EOL+">> Remove Game");
                    storage.removeGame();
                    helper.userCheck();
                    employeeMenu();
                    break;
                case "3":
                    System.out.println(menus.EOL+">> All Games");
                    storage.viewAll();
                    helper.userCheck();
                    employeeMenu();
                    break;
                case "4":
                    mainMenu();
                    break;
                default:
                    System.out.println(menus.divider+menus.EOL+"--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }

    public void managerMenu() {
        menus.managerMenu();
        System.out.print(menu.prompt);
        do {
            String choice = helper.getInput("");
            switch (choice) {
                case "1":
                    System.out.print(menus.EOL+">> New Employee"+EOL);
                    storage.addEmployee();
                    helper.userCheck();
                    managerMenu();
                    break;
                case "2":
                    storage.viewEmployee();
                    storage.removeEmployee();
                    helper.userCheck();
                    managerMenu();
                    break;
                case "3":
                    System.out.println(menus.EOL+">> All Employees");
                    storage.viewEmployee();
                    helper.userCheck();
                    managerMenu();
                    break;
                case "4":
                    mainMenu();
                    break;
                default:
                    System.out.println(menus.divider+menus.EOL+"--- Invalid input ---");
                    System.out.print(menu.prompt);
                    break;
            }
        } while (true);
    }
    // Getters

    // Setters


}