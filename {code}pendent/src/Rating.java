import java.util.HashSet;

public class Rating {

    private int rating;
    private String feedback;

    Rating(){
    }

    Rating(int rating, String feedback){
        this.rating = rating;
        this.feedback = feedback;
    }

    public int getRating(){
        return this.rating;
    }
}
