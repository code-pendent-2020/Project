import java.lang.*;

public class DartMain{
    private static final String WELCOME_MESSAGE = "\nWelcome to DART, your good old game rental system. The competition has no steam to keep up!";

    public static void main(String[] args) {
        System.out.println(WELCOME_MESSAGE);
        DartController houstonWeHaveFuckingLiftOff = new DartController();
        houstonWeHaveFuckingLiftOff.run();
    }
}