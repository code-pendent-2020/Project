import java.lang.reflect.Array;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class Customer{

    private int customerId;
    private String name;
    private String membershipType;
    private ArrayList<Message> inbox;

    Helper helper=new Helper();
    Membership memberRequest = new Membership();

    // Default Constructor
    Customer() {
    }

    public Customer(int customerId,String name){
        this.customerId=customerId;
        this.name=name;
        this.inbox = new ArrayList<>();
        this.membershipType = null;
    }

    public Customer(int customerId,String name, String membership){
        this.customerId=customerId;
        this.name=name;
        this.inbox = new ArrayList<>();
        this.membershipType= membership;
    }

    public String toString(){
        return  "\n" + "ID: "+ this.getCustomerId() + ", Name: " + this.getName() + ", Membership: " + this.getMembership();
    }

    public Customer addCustomer(){
        System.out.print("Enter the customers ID : ");
        int customerID = helper.input.nextInt();
        helper.input.nextLine();
        System.out.print("Enter the customers  name: ");
        String customerName = helper.input.nextLine();
        Customer newCustomer = new Customer(customerID, customerName);
        return newCustomer;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembership() {
        return membershipType;
    }

    public void setMembership(String membership) {
        this.membershipType = membership;
    }

    /*
        public String removeCustomer(){
            return null;
        }
    */
    public ArrayList<Membership> addMembership(){
        DartController dartController = new DartController();
        ArrayList<Customer> customerList = dartController.getCustomers();
        ArrayList<Membership> requestList = null;
        String name = helper.getInput("What is your name?: ");
        if (customerList.stream().anyMatch(o->o.getName().equalsIgnoreCase(name))){
            String type = null;
            int membershipType = helper.getInt("Which membership do you want to apply for? \n 1) Silver \n 2) Gold \n 3) Platinum: " );
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

   /*
    public void IncreaseArray() {
        ArrayList<Customer> customerListNew = new Customer[customerList.size() + (customerList.size() / 2)];
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










