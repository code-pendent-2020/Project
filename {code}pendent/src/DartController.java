import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private List<Game> games = new ArrayList<>(Arrays.asList(new Game( "Sonic: The Hedgehog", "Explore", 23, false),new Game( "Crash Bandicoot", "Racing", 24, false),new Game( "The Legend of Zelda", "Explore", 51, true),
            new Game ( "Prince of Persia", "Impossible", 33, false),
            new Game ( "Super Mario", "Classic", 32, false),
            new Game( "Street Fighter", "Fighting", 54, false),
            new Game( "Tekken", "Fighting", 29, false)));

    private ArrayList<Customer> customerList = new ArrayList<>(Arrays.asList(new Customer(1,"Vernita", "Silver"),new Customer(2,"Navya"), new Customer(3,"Drake"),new Customer(4,"Altan"),  new Customer(5,"Axel")));

    public ArrayList<Employee> getEmployees(){return employees;}
    public ArrayList<Customer> getCustomers() {
        return customerList;
    }

    public List<Game> getGames(){
        return games;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(){
        this.customer = customer;
    }
    //Game
    public void rentGame(){
        rental.rentGame();
    }
    //Customer
    //--------------------------------------------------------------------------//

    public void addCustomer(){
       this.customerList.add(customer.addCustomer());
       System.out.println(customerList.toString());
    }

    public void removeCustomer(){
       int removeId = helper.getInt("Enter the ID of the customer you want to remove.\nID: ");
       this.customerList.removeIf(customer -> customer.getCustomerId() == removeId);
       viewCustomer();
   }

     public void viewCustomer(){
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }
    }

    public ArrayList<Membership> addMembership(){
        return this.customer.addMembership();
    }


    //--------------------------------------------------------------------------//
    public void addEmployee(){
        this.employees.add(employee.addEmployee());
    }
    public void removeEmployee() {
        String removeID = helper.getInput("Enter the ID of the employee you want to remove.\nID: ");
        this.employees.removeIf(employee -> employee.getEmployeeID().equals(removeID));
        System.out.println("Employee Removed\n");
    }
    public void viewEmployee(){
        for (Employee employee : employees){
            System.out.println(employee.toString());
        }
    }
    //--------------------------------------------------------------------------//


    public void addAlbum(){
        this.albums.add(album.addAlbum());
    }
    public void removeAlbum(){
        String removeID = helper.getInput("Enter the ID of the album you want to remove.\nID: ");
        this.albums.removeIf(album -> album.getID().equals(removeID));
        System.out.println("Employee Removed\n");
    }
    public void rentAlbum(){
        String rental = helper.getInput("Title: ");
        for (Album album : albums) {
            if (album.getTitle().equals(rental)) {
                album.setRentStatus(true);
                System.out.println("Rented "+ album.getTitle() + "- by "+ album.getArtist());
            }
        }
    }
    public void returnAlbum(){ // still needs to do calculation of price
        String rental = helper.getInput("Title: ");
        for (Album album : albums) {
            if (album.getTitle().equals(rental)) {
                album.setRentStatus(false);
                System.out.println("Returned "+ album.getTitle() + "- by "+ album.getArtist());
            }
        }
    }
    public void viewAlbums() {
        for (Album album : albums) {
            System.out.println(album.toString());
        }
    }

    //--------------------------------------------------------------------------//


}