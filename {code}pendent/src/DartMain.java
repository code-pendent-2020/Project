import controller.DartController;
import tools.Input;

import java.lang.*;

public class DartMain {
    private static final String WELCOME_MESSAGE = Input.EOL +"Welcome to DART, your good old game rental system. The competition has no steam to keep up!";

    public static void main(String[] args) {
        System.out.println(WELCOME_MESSAGE);
        DartController start = new DartController();
        start.run();
    }
}