package tools;

import java.time.Year;
import java.util.*;

public class Input {
    public static final String EOL = System.lineSeparator();
    public static final String DIVIDER = "-----";
    public static final int CURRENT_YEAR = Year.now().getValue();
    public static Scanner input = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    private static Input instance = null; // Static so it can be access everywhere

    private Input() {
    } // private so you cant explicitly instantiate a new Input instance

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

    public void slowPrint(String str) throws InterruptedException {
        // Get message, convert to char array
        char[] eachChar = str.toCharArray();

        // Print a char from the array, then sleep for 1/10 second
        for (int i = 0; i < eachChar.length; i++) {
            Thread.sleep(100);
            System.out.print(eachChar[i]);
            Thread.sleep(10);
        }
    }

    public void userCheck() {
        String check = getInput(">> Press \u001B[31m'Enter'\u001B[0m to continue: ");
    }
}

