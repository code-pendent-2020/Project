import java.util.ArrayList;

public class DartController{
    private Menus menus;
    Storage storage; // at the moment rental and customer has to have full access to
    // dartcontroller we don't want games and rental to ever need to open dartcontroller

    public DartController(){
        this.menus = new Menus();
        this.storage = new Storage();
    }

    public void run(){
        menus.mainMenu();
    }

    // Getters

    // Setters


}