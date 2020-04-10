package View;

import java.util.Scanner;

public class MainPageView {
    private Scanner scanner = new Scanner(System.in);
    private String command;
    private boolean loggedIn;

    public MainPageView() {
    }

    public void run(){
        mainMenu();

    }
    private void mainMenu(){
        command = scanner.nextLine();
      //if
         userSection();
      //else if
        productSection();
      //else if
        salesSection();

    }
    private void userSection(){
        //if not logged in

    }
    private void productSection(){

    }
    private void salesSection(){

    }
    private void registerUser(){

    }

}
