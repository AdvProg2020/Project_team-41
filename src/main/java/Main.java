import Client.View.Menus.MainMenu;

public class Main {

    public static void main(String[] args) {
        startProgram();
        MainMenu mainMenu=new MainMenu();
        mainMenu.show();
        mainMenu.execute();

    }
    private static void startProgram(){
        //todo read files from resources
    }
    private static void endProgram(){

    }
}
