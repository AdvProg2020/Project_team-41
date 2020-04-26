import Client.Controller.StartProgram;
import Client.View.Menus.MainMenu;

public class Main {

    public static void main(String[] args)  {
        System.out.println("***************************************");
        System.out.println("username for the manager: m");
        System.out.println("password for the manager: m");
        System.out.println("***************************************");
        StartProgram.startProgram();
        MainMenu.getInstance().show();
        MainMenu.getInstance().execute();

    }

}
