import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DartController {

    //opening component classes
    private Employee employee = new Employee();
    private Customer customer = new Customer();
    private Helper helper = new Helper();
    private Album album = new Album();
    private Rental rental = new Rental();


    // "kind of" Storage
    private ArrayList<Album> albums = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<Game> games = new ArrayList<>();
    private ArrayList<Customer> customerList = new ArrayList<>(Arrays.asList(new Customer(1,"Vernita"),new Customer(2,"Navya"), new Customer(3,"Drake"),new Customer(4,"Altan"),  new Customer(5,"Axel")));


    public ArrayList<Employee> getEmployees(){return employees;}
    public  ArrayList<Customer> getCustomers() {
        return customerList;
    }
    public ArrayList<Game> getGames(){ return games; }


    public void addCustomer(){
       this.customerList.add(customer.addCustomer());
       System.out.println(customerList.toString());
   }

   public void removeCustomer(){
       int removeId = helper.getInt("ID of customer to remove: ");
       this.customerList.removeIf(customer -> customer.getCustomerId() == removeId);
       viewCustomer();
   }
     public void viewCustomer(){
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }
}


    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(){
        this.customer = customer;
    }

    // Talking to employee
    //--------------------------------------------------------------------------//
    public void addEmployee(){
        this.employees.add(employee.addEmployee());
    }
    public void removeEmployee() {
        String check = helper.getInput("ID: ");
        removeEmployee();
        employees.remove(employee);
    }
    public void viewEmployee(){
        for (Employee employee : employees){
            System.out.println(employee.toString());
        }
    }
    //--------------------------------------------------------------------------//

    public void viewAlbums() {
        for (Album album : albums) {
            System.out.println(album.toString());
        }
    }

    public void addAlbum(){
        this.albums.add(album.addAlbum());
    }

    public void rentGame(){
        rental.rentGame();
    }



}