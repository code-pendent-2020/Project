package tools;

public class Menus {

    private String title;
    private String[] options;
    private final Input input = Input.getInstance();
    public static final String PROMPT = "---------------------"+System.lineSeparator()+"Choose your option: ";
    public static final String DIVIDER = "---------------------";

    // Constructor
    public Menus(String title, String[] options) {
        this.title = title;
        this.options = options;
    }

    public Menus() {
    }

    public void mainMenu() {
        String[] options = {" 1) Manager Menu", " 2) people.Employee Menu", " 3) people.Customer Menu", " 4) Exit System"};
        Menus menu = new Menus(input.EOL + "------Main-Menu------", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(menu.PROMPT);
    }

    public void managerMenu() {
        String[] options = {" 1) Register people.Employee", " 2) Remove people.Employee", " 3) View Employees", " 4) View items.properties.Rental History ", "5) Main Menu"};
        Menus menu = new Menus(input.EOL + "-----Manager-Menu-----", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
    }

    public void employeeMenu() {
        String[] options = {" 1) items.Game Options", " 2) items.Album Options", " 3) people.Customer Options", " 4) Total Rent Profit", " 5) Main Menu"};
        Menus menu = new Menus(input.EOL+"----people.Employee-Menu----", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
    }

    public void empGameOptions() {
        String[] options = {" 1) Register items.Game", " 2) Remove items.Game", " 3) View Games", " 4) people.Employee Menu"};
        Menus menu = new Menus(input.EOL + "-----items.Game-Options----", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
    }

    public void empAlbumOptions() {
        String[] options = {" 1) Register items.Album", " 2) Remove items.Album", " 3) View Albums", " 4) people.Employee Menu"};
        Menus menu = new Menus(input.EOL + "----items.Album-Options----", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
    }

    public void empCustomerOptions() {
        String[] options = {" 1) Register people.Customer", " 2) Remove people.Customer", " 3) View Customers", " 4) people.Employee Menu"};
        Menus menu = new Menus(input.EOL + "---people.Customer-Options---", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
    }

    public void customerMenu() {
        String[] options = {" 1) items.Game Options", " 2) items.Album Options", " 3) people.membership.Membership Options", " 4) Inbox", " 5) Main Menu"};
        Menus menu = new Menus(input.EOL + "----people.Customer-Menu----", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
    }

    public void cusGameOptions() {
        String[] options = {" 1) Rent items.Game", " 2) Return items.Game", " 3) Search items.Game by Genre", " 4) View Games by Year", " 5) View Games by items.properties.Rating", " 6) people.Customer Menu"};
        Menus menu = new Menus(input.EOL + "---------items.Game--------", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
    }

    public void cusAlbumOptions() {
        String[] options = {" 1) Rent items.Album", " 2) Return items.Album", " 3) Search items.Album by Year", " 4) View Albums by Year", " 5) View Albums by items.properties.Rating", " 6) people.Customer Menu"};
        Menus menu = new Menus(input.EOL + "--------items.Album--------", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
    }

    public void cusMembershipOptions() {
        String[] options = {" 1) Add people.membership.Membership", " 2) Upgrade people.membership.Membership", " 3) people.Customer Menu"};
        Menus menu = new Menus(input.EOL + "-----people.membership.Membership------", options);
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
    }

    public void inboxMenu() {
        String[] options = {" 1) View messages", " 2) Send people.membership.Message", " 3) Delete people.membership.Message", " 4) people.Customer Menu"};
        Menus menu = new Menus("--------Inbox--------", options); //add the name of the user whose inbox we are using to title
        System.out.println(menu.title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
    }
}
