import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class Customer extends Person {
    private String membershipType;
    private Membership membership;
    private ArrayList<Message> inbox;
    private final Input input = Input.getInstance();
    private Membership memberRequest = new Membership();

    // Default Constructor
    Customer() {
    }

    public Customer(String name){
        super(name);
        this.inbox = new ArrayList<>();
    }

    public Customer(String name, Membership membership){
        super(name);
        this.inbox = new ArrayList<>(Arrays.asList(
                new Message("Welcome!", "Welcome to your inbox to send a message or view your messages simply use the menu!\n", UUID.randomUUID().toString(),"DART")));
        this.membership = membership;
    }

    public String toString() {
        return input.EOL + "ID: " + super.getId() + ", Name: " + this.getName() + ", Membership: " + this.getMembershipType();
    }

    public Customer addCustomer(){
        System.out.print("Enter the customers name: ");
        String customerName = input.input.nextLine();
        return new Customer(customerName);
    }

    public String getId() {
        return super.getId();
    }

    public String getMembershipType() {
        return this.membership.getType();
    }

    public void setMembershipType(String membershipType) {
        this.membership.setType(membershipType);
    }

    public String getName() {
        return super.getName();
    }

    public ArrayList<Message> getInbox() {

        return inbox;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public void viewMessages(Customer customer) {
        for (Message message : customer.getInbox()) {
            System.out.println(message.toString());
        }
    }

    public ArrayList<Membership> addMembership(ArrayList<Customer> customerList){
        ArrayList<Membership> requestList = null;
        boolean contains = false;
        String name = input.getInput("What is your name?: ");
        for (Customer customer : customerList){
            if (customer.getName().equalsIgnoreCase(name)){
                contains = true;
                if(customer.getMembership().getType().equals("No membership")) {
                    String type = null;
                    int membershipType = input.getInt("Which membership do you want to apply for? \n 1) Silver \n 2) Gold \n 3) Platinum \n");
                    if (membershipType == 1) {
                        type = "Silver";
                    } else if (membershipType == 2) {
                        type = "Gold";
                    } else if (membershipType == 3) {
                        type = "Platinum";
                    } else {
                        System.out.println("Not a valid input.");
                    }
                    System.out.println("Your request for a " + type + " membership will be reviewed shortly.");
                    requestList = memberRequest.requestMembership(name, type);
                } else {
                    System.out.println("Hi " + customer.getName() + "! You are one of our most valued customers and already have a " + customer.getMembershipType() + " membership already. Perhaps you want to try upgrading instead.\n");
                }
            }
        }
        if (!contains) {
            System.out.println("Sorry, " + name + " doesn't exist in this dimension");
        }
        return requestList;
    }

    public ArrayList<Membership> upgradeMembership(ArrayList<Customer> customerList) {
        ArrayList<Membership> upgradeList = null;
        String name = input.getInput("What is your name?: ");
        boolean contains = false;
        // requestingMember = customerList.stream().filter(customer -> getName().equalsIgnoreCase(name));
        for (Customer customer : customerList) {
            if (customer.getName().equalsIgnoreCase(name)) {
                contains = true;
                String membershipType = customer.getMembership().getType();
                String databaseName = customer.getName();
                if (membershipType.equalsIgnoreCase("No membership")) {
                    System.out.println("Sorry " + databaseName + ", it seems you don't have a membership yet. Perhaps you can try to register for our Silver Membership instead?");
                    return null;
                }
                System.out.println("Hi " + databaseName + "! You currently have a " + membershipType + " membership. \nWhich Membership would you like to upgrade to? \n");
                String requestType = null;
                int userInput;
                if (membershipType.equals("Silver")) {
                    userInput = input.getInt("1) Gold \n2) Platinum \n3) Back to Customer Menu \n");
                    if (userInput == 1) {
                        requestType = "Gold";
                    } else if (userInput == (2)) {
                        requestType = "Platinum";
                    }
                } else if (membershipType.equals("Gold")) {
                    userInput = input.getInt("1) Platinum \n2) Back to Customer Menu");
                    if (userInput == 1) {
                        requestType = "Platinum";
                    }
                }
                System.out.println("Your request for a " + requestType + " membership will be reviewed shortly.");
                upgradeList = memberRequest.requestMembership(databaseName, requestType);
            }
        }
        if (!contains) {
            System.out.println("Sorry, " + name + "is not in our Customer database.");
        }
        return upgradeList;
    }
}










