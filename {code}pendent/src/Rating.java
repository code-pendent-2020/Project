import java.util.HashSet;

public class Rating {

    private double rating;
    private String feedback;

    Rating(){
    }

    Rating(double rating, String feedback){
        this.rating = rating;
        this.feedback = feedback;
    }

    public double getRating(){
        return this.rating;
    }
}
