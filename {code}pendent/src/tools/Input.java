package tools;

import java.time.Year;
import java.util.Scanner;

public class Input {
    public static final String EOL = System.lineSeparator();
    public static final String DIVIDER = "-----";
    public static final int CURRENT_YEAR = Year.now().getValue();
    public static Scanner input = new Scanner(System.in);
    String userInput;


    private static Input instance = null; // Static so it can be access everywhere

    private Input() {
    } // private so you cant explicitly instantiate a new tools.Input instance

    public static Input getInstance() {
        if (instance == null) { // check if null
            instance = new Input(); // Yes, make new instance
        }
        return instance; // No, return current instance
    }

    // Cleanup, save memory, etc
    public void tearDown() {
        instance = null;
        input.close();
    }

    public String getInput(String message) {
        System.out.print(message);
        String userInput = input.nextLine();
        return userInput;
    }

    public int getInt(String message) {
        System.out.print(message);
        String userInput = input.nextLine().replaceAll("[^0-9]", "");
        if (userInput.isBlank() || userInput.isEmpty()) {
            System.out.println("-----------------"+EOL+"--- Invalid input ---");
            getInt(message);
        }
        return Integer.parseInt(userInput);

    }

    public double getDouble(String message) {
        System.out.print(message);
        double userInput = input.nextDouble();
        input.nextLine();
        return userInput;
    }

    public void userCheck() {
        String check = getInput(">> Press 'Enter' to continue: ");
    }
}

