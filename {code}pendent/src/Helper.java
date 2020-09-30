//d

import java.time.Year;
import java.util.*;

public class Helper {
    public static final int CURRENT_YEAR = Year.now().getValue();
    public static Scanner input = new Scanner(System.in); // static scanner
    String userInput;

    //Default Constructor
    public Helper() {
    }


    // THIS ONE IS SUPER USEFUL USE IT! something with opening and closing the scanner break swapping between menus (Drake)
    public String getInput(String message) { // Method to get string input from user and return

        System.out.print(message);
        String userInput = input.nextLine();  // Read user input
        // scanner.close(); // Close scanner - This causes issue, leave it commented out (Altan)
        return userInput;  // Output user input

    }

    // made this to get integers instead of String (Drake)
    public int getInt(String message) { // Method to get string input from user and return

        System.out.print(message); //removed println and replaced with print (D) if we need and println version we'll make one
        int userInput = input.nextInt();  // Read user input
        input.nextLine();
        // scanner.close(); // Close scanner - This causes issue, leave it commented out (Altan)
        return userInput;  // Output user input

    }
    public double getDouble(String message) { // Method to get string input from user and return

        System.out.print(message); //removed println and replaced with print (D) if we need and println version we'll make one
        double userInput = input.nextInt();  // Read user input
        input.nextLine();
        // scanner.close(); // Close scanner - This causes issue, leave it commented out (Altan)
        return userInput;  // Output user input

    }
}

