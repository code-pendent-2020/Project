
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Customer extends Person {
    private String membershipType;
    private ArrayList<Message> inbox;
    private Person person;

    private Input input = Input.getInstance();
    Membership memberRequest = new Membership();

    // Default Constructor
    Customer() {
    }


    public Customer(String name, String membership){
        super(name);
        this.inbox = new ArrayList<>(Arrays.asList(
                new Message("Welcome!", "Welcome to your inbox to send a message or view your messages simply use the menu!\n", UUID.randomUUID().toString(),"DART")));
        this.membershipType= membership;
    }

    public String toString(){
        return  "\n" + "ID: "+ super.getId() + ", Name: " + this.getName() + ", Membership: " + this.getMembershipType();
    }

    public Customer addCustomer(){
        System.out.print("Enter the customers name: ");
        String customerName = input.input.nextLine();
        Customer newCustomer = new Customer(customerName, "Basic");
        return newCustomer;
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
                if (customer.membershipType.equals(null)) {
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
        } if (!contains){
            System.out.println("Sorry, " + name + "is not on our Customer database.");
        }
        return upgradeList;
    }


   /*
    public void IncreaseArray() {
        ArrayList<Customer> customerListNevw = new Customer[customerList.size() + (customerList.size() / 2)];
        for (int i = 0; i < customerList.size(); i++) {
            customerListNew[i] = customerList(i);
        }
        customerList = customerListNew;
    }

    public void addCustomer() {
        int countArray = 0;
        for(int i = 0; i < customerList.length; i++){
            if(customerList[i] != null){
                countArray++;
            }
        }

        int countId = customerList[countArray - 1].customerId + 1;

        if (customerList[customerList.length - 1] != null) {
            IncreaseArray();
        }

        System.out.println("Add a customer here");
        System.out.println("Suggested ID: " + countId);
        System.out.print("Enter the customers ID : ");
        int cusIdInputs = helper.input.nextInt();
        helper.input.nextLine();
        System.out.print("Enter the customers  name: ");
        String cusName = helper.input.nextLine();
        int arrayCount = 0;
        for (int i = 0; customerList[i] != null; i++) {
            arrayCount = i + 1;
        }
        customerList[arrayCount] = new Customer(cusIdInputs, cusName);
        System.out.println("Customer added successfully :" + customerList[arrayCount].toString());
        System.out.print("If you want to add another customer press '1': ");
        int anotherEntry = helper.input.nextInt();
        if (anotherEntry == 1) {
            addCustomer();
        }
        else if (anotherEntry!=1){
            System.out.print("Invalid entry ");
            EmployeeMenu.employeeMenu();
        }
    }

    public void removeCustomer() {
        viewCustomer();
        int cusIdToRemove = helper.getInt("Remove a customer here by entering their ID : ");

        for (int i = 0; i < customerList.size(); i++) {
            if (customerList[i].customerId == cusIdToRemove) {
                for (int j = i + 1; j < customerList.size(); j++) {
                    customerList[i] = customerList[j];
                    i++;
                }
                int arrayCount = 0;
                for (int k = 0; k < customerList.length; k++){
                    if (customerList[k] != null){
                        arrayCount++;
                    }
                }
                customerList[arrayCount-1] = null;
            }
        }
        System.out.println("Customer Removed");
        EmployeeMenu.employeeMenu();
    }

*/

 //   public void viewCustomer(){
        /*for (int i = 0; i < customerList.size(); i++) {
            if (customerList == null) {
                continue;
            } */

}










