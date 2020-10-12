package people;

import people.features.membership.Membership;
import people.features.Message;
import tools.Input;

import java.util.ArrayList;
import java.util.Arrays;

public class Customer extends Person {

    private Membership membership;
    private double spentMoney;
    private ArrayList<Message> inbox;
    private final Input input = Input.getInstance();
    private int credits;
    private int currentRentals;
    private ArrayList<Customer> memberRequest = new ArrayList<>();
    private String type;

    public Customer() {
    }

    public Customer(String name) {
        super(name);
        this.credits = 0;
        this.currentRentals = 0;
        this.inbox = new ArrayList<>(Arrays.asList(
                new Message("Welcome!", "Welcome to your inbox to send a message or view your messages simply use the menu!"+input.EOL, "Management", "DART")));

    }
    public Customer(String name, String type){
        super(name);
        this.type = type;
    }
    public Customer(String name, double spentMoney) {
        super(name);
        this.spentMoney = spentMoney;
    }

    public Customer(String name, Membership membership) {
        super(name);
        this.inbox = new ArrayList<>(Arrays.asList(
                new Message("Welcome!", "Welcome to your inbox to send a message or view your messages simply use the menu!"+input.EOL, "Management", "DART")));
        this.membership = membership;
        this.credits = 0;
        this.currentRentals = 0;
    }

    public Customer addCustomer() {
        System.out.print("Enter the customers name: ");
        String customerName = input.input.nextLine();
        return new Customer(customerName);
    }

    //------------------------------------------------

    public void setMembership(Membership type){
        this.membership = type;
    }

    public void applyCredits(){
        this.credits = this.membership.applyCredit(this.credits);
    }

    public boolean canRent(){
        return this.membership.maxRentals(this.currentRentals);
    }
    public double calculateBill(int userBill){
        return this.membership.discount(userBill);
    }

    //------------------------------------------------

    public int getCredits(){
        return this.credits;
    }

    public int getCurrentRentals(){
        return this.currentRentals;
    }

    public String getId() {
        return super.getId();
    }

    public String getName() {
        return super.getName();
    }

    public ArrayList<Message> getInbox() {
        return inbox;
    }

    public double getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(double spentMoney) {
        this.spentMoney = spentMoney;
    }

    public Membership getMembership(Object o) {
        return membership;
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

    public ArrayList<Customer> addMembership(ArrayList<Customer> customerList) {
        ArrayList<Customer> requestList = null;
        boolean contains = false;
        String name = input.getInput("What is your name?: ");
        for (Customer customer : customerList) {
            if (customer.getName().equalsIgnoreCase(name)) {
                contains = true;
                if (customer instanceof Membership) {
                    String type = null;
                    int membershipType = input.getInt("Which membership do you want to apply for? " + input.EOL + " 1) Silver " + input.EOL + " 2) Gold " + input.EOL + " 3) Platinum" + input.EOL);
                    switch (membershipType){
                        case 1:
                            type = "Silver";
                            break;
                        case 2:
                            type = "Gold";
                            break;
                        case 3:
                            type = "Platinum";
                            break;
                        default:
                            System.out.println("Not a valid input.");
                            break;
                    }
                    System.out.println("Your request for a " + type + " membership will be reviewed shortly.");
                    memberRequest.add(new Customer(name, type));
                    requestList = memberRequest;
                } else {
                    System.out.println("Hi " + customer.getName() + "! You are one of our most valued customers and have a " + customer.getMembershipType() + " membership already. Perhaps you want to try upgrading instead.\n");
                }
            }
        }
        if (!contains) {
            System.out.println("Sorry, " + name + " doesn't exist in this dimension");
        }
        return requestList;
    }


    public ArrayList<Customer> requestMembership(String name, Membership type) {

        return memberRequest;
    }


    public ArrayList<Membership> upgradeMembership(ArrayList<Customer> customerList) {
        ArrayList<Membership> upgradeList = null;
        String name = input.getInput("What is your name?: ");
        boolean contains = false;
        // requestingMember = customerList.stream().filter(customer -> getName().equalsIgnoreCase(name));
        for (Customer customer : customerList) {
            if (customer.getName().equalsIgnoreCase(name)) {
                contains = true;
                String membershipType = customer.getMembership(null).getType();
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
                    } else if (userInput == (PLATINUM_MEMBERSHIP)) {
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

    public void addSpentMoney(double transactionCost) {
        this.spentMoney = this.spentMoney + transactionCost;
    }

    public String toString() {
        return input.EOL + "Customer ID: " + super.getId() + input.EOL + "Name: " + this.getName() + input.EOL + this.getMembershipType() + input.EOL + "Credits: " + this.membership.getCredits() + input.EOL + "-----";
    }
}











