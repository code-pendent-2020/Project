package people;

import items.Inventory;
import people.features.Membership;
import people.features.Message;
import tools.Input;

import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class Customer extends Person {
    private String membershipType;
    private Membership membership;
    private ArrayList<Message> inbox;
    private boolean readStatus;
    private final Input input = Input.getInstance();
    private Membership memberRequest = new Membership();
    private int maxNumberOfRentals;
    public static final int SILVER_MEMBERSHIP = 1;
    public static final int GOLD_MEMBERSHIP = 2;
    public static final int PlATINUM_MEMBERSHIP = 3;

    public Customer() {
    }

    public Customer(String name) {
        super(name);
        this.maxNumberOfRentals = 0;
    }

    public Customer(String name, Membership membership) {
        super(name);
        this.inbox = new ArrayList<>(Arrays.asList(
                new Message("Welcome!", "Welcome to your inbox to send a message or view your messages simply use the menu!"+input.EOL, "Management", "DART")));
        this.membership = membership;
        this.maxNumberOfRentals = 0;
    }

    public Customer addCustomer() {
        System.out.print("Enter the customers name: ");
        String customerName = input.input.nextLine();
        return new Customer(customerName);
    }

    public int getMaxNumberOfRentals() {
        return maxNumberOfRentals;
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

    public boolean getReadStatus() {
        return readStatus;
    }

    public void viewMessages(Customer customer) {
        for (Message message : customer.getInbox()) {
            if (!message.getReadStatus()) {
                message.setReadStatus(true);
                System.out.print(Input.ANSI_RED+"Unread"+Input.ANSI_RESET);
            } else {
                System.out.print(Input.ANSI_CYAN+"Read"+Input.ANSI_RESET);
            }
            System.out.println(message.toString());
        }
    }

    public ArrayList<Membership> addMembership(ArrayList<Customer> customerList) {
        ArrayList<Membership> requestList = null;
        boolean contains = false;
        String name = input.getInput("What is your name?: ");
        for (Customer customer : customerList) {
            if (customer.getName().equalsIgnoreCase(name)) {
                contains = true;
                if (customer.getMembership().getType().equals("No membership")) {
                    String type = null;
                    int membershipType = input.getInt("Which membership do you want to apply for? " + input.EOL + " 1) Silver " + input.EOL + " 2) Gold " + input.EOL + " 3) Platinum" + input.EOL);
                    if (membershipType == SILVER_MEMBERSHIP) {
                        type = "Silver";
                    } else if (membershipType == GOLD_MEMBERSHIP) {
                        type = "Gold";
                    } else if (membershipType == PlATINUM_MEMBERSHIP) {
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
                System.out.println("Hi " + databaseName + "! You currently have a " + membershipType + " membership. " + input.EOL + "Which Membership would you like to upgrade to?: \n");
                String requestType = null;
                int userInput;
                if (membershipType.equals("Silver")) {
                    userInput = input.getInt("1) Gold " + input.EOL + "2) Platinum " + input.EOL + "3) Back to Customer Menu" + input.EOL);
                    if (userInput == GOLD_MEMBERSHIP) {
                        requestType = "Gold";
                    } else if (userInput == (PlATINUM_MEMBERSHIP)) {
                        requestType = "Platinum";
                    }
                } else if (membershipType.equals("Gold")) {
                    userInput = input.getInt("1) Platinum " + input.EOL + "2) Back to Customer Menu" + input.EOL);
                    if (userInput == 1) {
                        requestType = "Platinum";
                    }
                }
                System.out.println("Your request for a " + requestType + " Membership will be reviewed shortly.");
                upgradeList = memberRequest.requestMembership(databaseName, requestType);
            }
        }
        if (!contains) {
            System.out.println("Sorry, " + name + " is not in our Customer database.");
        }
        return upgradeList;
    }

    public void incrementMaxNumberOfRentals() {
        this.maxNumberOfRentals++;
    }

    public String toString() {
        return input.EOL + "Customer ID: " + super.getId() + input.EOL + "Name: " + this.getName() + input.EOL +
                           "Membership: " + this.getMembershipType() + input.EOL + "Credits: " + this.getMembership().getCredits() + input.EOL + "-----";
    }


}











