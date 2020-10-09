package people.features;

import java.util.ArrayList;

public class Membership {

    private String type;
    private String name;
    private int discount;
    private ArrayList<Membership> customerMember = new ArrayList<>();

    public Membership() {
    }

    public Membership(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public ArrayList<Membership> requestMembership(String name, String type) {
        customerMember.add(new Membership(name, type));
        return customerMember;
    }

    public double discount() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        if (type == null) {
            return "No membership";
        } else return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}


