public class Employee extends Person {

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

    Input tools = new Input();

    public Employee(){

    }

    public Employee(String name, int birthYear, String address, double salary) {
        super();
        this.birthYear = birthYear;
        this.grossSalary = salary;
        this.address = address;

    }

    public Employee addEmployee() {
        String name = tools.getInput("Name: ");
        int birthYear = tools.getInt("Birth year: ");
        String address = tools.getInput("Address: ");
        double monthlySalary = tools.getDouble("Monthly gross salary: ");
        grossSalary = monthlySalary * 12;
        Employee employee = new Employee(name, birthYear, address, grossSalary);
        return employee;
    }

    public double netSalary() {
        Input year = new Input();
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

    public double getSalary(){
        return netSalary();
    }

    public int getBirthYear(){
        return birthYear;
    }

    public String getAddress(){
        return address;
    }


    public String toString() {
        return divider+ "\nID: " + this.getId() + "\nName: " + this.getName() + "\nBirth year: " + this.getBirthYear()
                + "\nAddress: " + this.getAddress() + "\nsalary: " + this.netSalary();
    }
}


