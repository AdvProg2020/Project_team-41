import Client.Controller.StartProgram;
import Client.View.Menus.MainMenu;

public class Main {

    public static void main(String[] args) throws Exception {
            new SimpleAudioPlayer().playMusic();
            StartProgram.startProgram();
            MainMenu.getInstance().show();
            MainMenu.getInstance().execute();
    }


}