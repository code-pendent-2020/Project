package tools.s;

import controller.DartController;
import tools.Input;

import java.awt.*;


public class Secret {
    private Input input = Input.getInstance();
    private Menu menu = new Menu();
    public void secret(){
        String[] options = {input.ANSI_WHITE + " 1) {code}pendant ", " 2) Main Menu"+input.ANSI_RESET};
        String title = input.EOL + input.ANSI_WHITE + "---- Secret Menu ----"+input.ANSI_RESET;
        System.out.println(title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i]);
        }
        System.out.print(input.ANSI_WHITE +"---------------------" + input.ANSI_RESET + input.EOL + "Choose your option: ");
            do {
                String choice = Input.input.nextLine();
                switch (choice) {
                    case "1":
                        team();
                        input.userCheck();
                        secret();
                        break;
                    case "2":
                        System.out.println("Congrats on finding the Secret Menu");
                        DartController bad = new DartController();
                        bad.mainMenu();
                        break;
                    default:
                        System.out.println(input.ANSI_WHITE +"---------------------"+ input.ANSI_RESET + input.EOL + "--- Invalid input ---");
                        secret();
                        System.out.print(input.ANSI_WHITE +"---------------------"+ input.ANSI_RESET + input.EOL + "Choose your option: ");
                        break;
                }
            } while (true);
        }
    private void team(){System.out.println("-- Team {Code}pendant --" + input.EOL+"Silent Saboteur - Axel"+input.EOL+"Hacker SEM 2020 - Navya"+input.EOL+"Lost in Thought - Drake"+input.EOL+"The British One - Vernita");}
}
