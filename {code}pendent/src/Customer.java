import java.util.ArrayList;

public class Customer extends Person {
    private String membershipType;
    private ArrayList<Message> inbox;

    private final Input input = Input.getInstance();
    Membership memberRequest = new Membership();

    // Default Constructor
    Customer() {
    }

    public Customer(String name){
        super(name);
        this.inbox = new ArrayList<>();
        this.membershipType = null;
    }

    public Customer(String name, String membership){
        super(name);
        this.inbox = new ArrayList<>(Arrays.asList(
                new Message("Welcome!", "Welcome to your inbox to send a message or view your messages simply use the menu!\n", UUID.randomUUID().toString(),"DART")));
        this.membershipType= membership;
    }

    public String toString() {
        return input.EOL + "ID: " + super.getId() + ", Name: " + this.getName() + ", Membership: " + this.getMembershipType();
    }

    public Customer addCustomer(){
        System.out.print("Enter the customers name: ");
        String customerName = input.input.nextLine();
        return new Customer(customerName);
    }

    @Override
    public String getId() {
        return super.getId();
    }

    public String getMembershipType() {
        return this.membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Message> getInbox() {

        return inbox;
    }

    public void viewMessages(Customer customer) {
        for (Message message : customer.getInbox()) {
            System.out.println(message.toString());
        }
    }
    /*
            public String removeCustomer(){
                return null;
            }
        */
    public ArrayList<Membership> addMembership(ArrayList<Customer> customerList){
        ArrayList<Membership> requestList = null;
        String name = input.getInput("What is your name?: ");
        if (customerList.stream().anyMatch(o->o.getName().equalsIgnoreCase(name))){
            String type = null;
            int membershipType = input.getInt("Which membership do you want to apply for? \n 1) Silver \n 2) Gold \n 3) Platinum" );
            if(membershipType == 1){
                type = "Silver";
            }else if (membershipType == 2){
                type = "Gold";
            }else if (membershipType == 3){
                type = "Platinum";
            }else{
                System.out.println("Not a valid input.");
            }
           requestList = memberRequest.requestMembership(name, type);
        }else{
            System.out.println("you dont exist");
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
                String membershipType = customer.getMembershipType();
                String databaseName = customer.getName();
                if (membershipType == null) {
                    System.out.println("Sorry, it seems " + databaseName + " doesn't have a membership yet.");
                    return null;
                }
                System.out.println("Hi " + databaseName + "! You currently have a " + membershipType + " membership. \nWhich Membership would you like to upgrade to? \n");
                String requestType = null;
                int userInput;
                if (membershipType.equals("Silver")) {
                    userInput = input.getInt(" 1) Gold \n 2) Platinum \n 3) Back to Customer Menu");
                    if (userInput == 1) {
                        requestType = "Gold";
                    } else if (userInput == (2)) {
                        requestType = "Platinum";
                    } else return null;
                } else if (membershipType.equals("Gold")) {
                    userInput = input.getInt("1) Platinum 2) Back to Customer Menu");
                    if (userInput == 1) {
                        requestType = "Platinum";
                    } else return null;
                }
                System.out.println("Your request for " + requestType + " will be reviewed shortly.");
                upgradeList = memberRequest.requestMembership(databaseName, requestType);
            }
        }
        if (!contains) {
            System.out.println("Sorry, " + name + "is not in our Customer database.");
        }
        return upgradeList;
    }
}










