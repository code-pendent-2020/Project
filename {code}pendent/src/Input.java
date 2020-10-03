
import java.time.Year;
import java.util.*;

import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

public class Input {
    public static final int CURRENT_YEAR = Year.now().getValue();
    public static Scanner input = new Scanner(System.in); 
    String userInput;

    public Input() {
    }


    public String getInput(String message) {
        System.out.print(message);
        String userInput = input.nextLine(); 
        return userInput; 

    }


    public int getInt(String message) {
        System.out.print(message);
        String userInput = input.nextLine().replaceAll("[^0-9]", ""); 
        if (userInput.isBlank() || userInput.isEmpty()){
            System.out.println("-----------------\n- Invalid input -");
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

    public void userCheck(){
        String check = getInput(Menus.EOL+">> Press any key to continue: ");
        }
    }

