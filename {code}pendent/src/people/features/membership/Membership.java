package people.features.membership;

// Dark Arts :D
public interface Membership {
    abstract String membershipType();
    abstract double discount(double userBill);
    abstract boolean maxRentals(int rentals);
    abstract int applyCredit(int credits);
}




