package View.Menus;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu {
    private Menu superMenu;
    private ArrayList<Menu> subMenus;
    private String name;
    protected static Scanner scanner = new Scanner(System.in);
    public Menu(Menu superMenu, String name) {
        subMenus = new ArrayList<>();
        this.superMenu = superMenu;
        this.name = name;
    }
    public void show(){
        for (Menu subMenu : subMenus) {

            System.out.println(subMenu.getName());
        }
        if(superMenu != null)
            System.out.println("back");
        this.execute();
    }
    public void execute(){
        String command = scanner.nextLine();
        if(command.equals("help")) {
            this.show();
            this.execute();
        }
        if(command.equals("back")) {
            if (superMenu != null) {
                superMenu.show();
                superMenu.execute();
            }
            else{
                //todo print invalid command
            }

        }


        for (Menu subMenu : subMenus) {
            if(command.equals(subMenu.getName())) {
                subMenu.show();
                subMenu.execute();

            }
        }
        //todo if not valid
    }


    public String getName() {
        return name;
    }

    public void addSubMenu(Menu subMenu){
        this.subMenus.add(subMenu);
    }

}
