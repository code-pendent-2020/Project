import java.util.ArrayList;

public class Customer extends DartController{

    private int customerId;
    private String name;
    private Membership membership;
    private ArrayList<Message> inbox;

    Helper helper=new Helper();


    // Default Constructor
    Customer() {
    }

    public Customer(int customerId,String name){
        this.customerId=customerId;
        this.name=name;
        this.inbox = new ArrayList<>();
        Membership membership = new Membership();
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

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    /*
        public String removeCustomer(){
            return null;
        }
    */
    public void addMembership(){
        String type = null;
        int membershipType = helper.getInt("Which membership do you want to apply for? \n 1) Silver \n 2) Gold \n 3) Platinum" );
        if(membershipType == 1){
            type = "Silver";
        }else if (membershipType == 2){
            type = "Gold";
        }else if (membershipType == 3){
            type = "Platinum";
        }else{
            System.out.println("Not a valid input.");
        }
        this.membership.requestMembership(type);
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










