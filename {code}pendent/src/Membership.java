import java.util.ArrayList;
import java.util.List;

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


        public ArrayList<Membership> requestMembership(String name, String type){
            customerMember.add(new Membership(name, type));
            return customerMember;
        }

        public double discount(){

            return 0;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }


