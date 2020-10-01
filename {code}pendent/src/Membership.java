import java.util.ArrayList;
import java.util.List;

public class Membership {

        private String type;
        private int discount;
        private ArrayList<String> customerMember = new ArrayList<>();



        public Membership() {

        }

        public Membership(String type) {
            this.type = type;
        }


        public ArrayList<String> requestMembership(String type){
            customerMember.add(getType());
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


