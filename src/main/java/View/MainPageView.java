package View;
import java.util.Scanner;
public class MainPageView {
    private String command;
    private boolean loggedIn;
    private static Scanner scanner = new Scanner(System.in);
    private static MainPageView single_instance = null;
    public static MainPageView getInstance()
    {
        if (single_instance == null)
            single_instance = new MainPageView();

        return single_instance;
    }

    public void mainMenu(){
        command = scanner.nextLine();
      //if
         UserSection.getInstance();
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

    public static Scanner getScanner() {
        return scanner;
    }
}
