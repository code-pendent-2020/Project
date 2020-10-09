package people.features;

import java.util.ArrayList;

public class Membership {

    private String name;
    private String type;
    private int credits;
    private ArrayList<Membership> membershipRequest = new ArrayList<>();

    public Membership() {
    }

    public Membership(String type, int credits) {
        this.type = type;
        this.credits = 0;
    }

    public Membership(String name, String type) {
        this.name = name;
        this.type = type;
        this.credits = 0;
    }

    public ArrayList<Membership> requestMembership(String name, String type) {
        membershipRequest.add(new Membership(name, type));
        return membershipRequest;
    }

    public String getType() {
        if (type == null) {
            return "No membership";
        } else return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }
}


