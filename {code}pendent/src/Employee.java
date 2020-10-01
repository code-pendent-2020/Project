import java.util.UUID;


public class Employee {

    private String employeeID;
    private String name;
    private int birthYear;
    private String address;
    private double grossSalary;
    private String divider = "-----";


    private final double MIN_SALARY=100000.00;
    private final double  BONUS_LOW=4000.00;
    private final double BONUS_MEDIUM=6000.00;
    private final double BONUS_HIGH=7500.00;
    private final int FIRST_AGE_FOR_BONUS=22;
    private final int SECOND_AGE_FOR_BONUS=30;

    Helper tools = new Helper();

    public Employee(){

    }

    public Employee(String name, int birthYear, String address, double salary) {
        this.employeeID = genEmployeeID();
        this.name = name;
        this.birthYear = birthYear;
        this.grossSalary = salary;
        this.address = address;

    }

    private String genEmployeeID() {
        String generatedID = UUID.randomUUID().toString();
        return generatedID;
    }

    public Employee addEmployee(){
        String name = tools.getInput("Name: ");
        int birthYear = tools.getInt("Birth year: ");
        String address = tools.getInput("Address: ");
        double monthlySalary = tools.getDouble("Monthly gross salary: ");
        grossSalary = monthlySalary * 12;
        Employee employee = new Employee(name, birthYear, address, grossSalary);
        return employee;
    }


    public double netSalary() {
        Helper year = new Helper();
        int age = year.CURRENT_YEAR - birthYear;
        double netSalary = 0;
        //public static final double MIN_SALARY=100000.00;
        if (grossSalary < MIN_SALARY) {
            netSalary = grossSalary;

        } else {
            if (grossSalary >= MIN_SALARY) {
                netSalary = grossSalary - ((30.0 / 100) * grossSalary);
            }
        }
        double bonus;
        if (age < FIRST_AGE_FOR_BONUS) {
            bonus = BONUS_LOW;
            netSalary = netSalary + bonus;
        } else if (age == FIRST_AGE_FOR_BONUS && age < SECOND_AGE_FOR_BONUS) {
            bonus = BONUS_MEDIUM;
            netSalary = netSalary + bonus;
        } else if (age > SECOND_AGE_FOR_BONUS) {
            bonus = BONUS_HIGH;
            netSalary = netSalary + bonus;
        }
        return netSalary / 12;
    }
    // getters
    public String getName(){
        return name;
    }

    public double getSalary(){
        return netSalary();
    }

    public int getBirthYear(){
        return birthYear;
    }

    public String getAddress(){
        return address;
    }

    public String getEmployeeID(){
        return employeeID;
    }


    public String toString() {
        return divider+ "\nID: " + this.employeeID + "\nName: " + this.name + "\nBirth year: " + this.birthYear
                + "\nAddress: " + this.address + "\nsalary: " + netSalary();
    }
}


