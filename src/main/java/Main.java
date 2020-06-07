import Client.Controller.StartProgram;
import Client.View.Menus.MainMenu;

public class Main {

    public static void main(String[] args)  {

            MainMenu.getInstance().show();
            MainMenu.getInstance().execute();
    }


}