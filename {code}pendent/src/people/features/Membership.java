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
        if (this.type == null) {
            return "No membership";
        }
        return type;
    }

    public void incrementCredit(){
        this.credits++;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }
}


