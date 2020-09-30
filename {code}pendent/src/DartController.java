import java.lang.*;
import java.util.ArrayList;

public class DartController {

    //opening child classes
    private static Employee employee = new Employee();
    private static Customer customer = new Customer();
    private static Helper helper = new Helper();
    private static Menus menu = new Menus();
    private static Album album = new Album();


    // "kind of" Storage
    private ArrayList<Album> albums = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<Game> games = new ArrayList<>();
    private ArrayList<Customer> customerList = new ArrayList<>();

    public  ArrayList<Customer> getCustomers() {
        return customerList;
    }
   public void addCustomer(){
       this.customerList.add(customer.addCustomer());
       System.out.println(customerList.toString());
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
        menu.managerMenu();
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
        menu.employeeMenu();
    }

    // Default constructor
    public void run() {
        menu.mainMenu();
        }

}

 /* public void initialiseCustomerArraylist(){
        System.out.println("Checcccccccccccccccccccccck");
        setCustomers().add( new Customer(1,"Vernita"));
        getCustomers().add( new Customer(2,"Navya"));
        getCustomers().add( new Customer(3,"Drake"));
        getCustomers().add( new Customer(4,"Altan"));
        setCustomers().add( new Customer(5,"Axel"));
    }*/
