package Client.View.Menus;

import Client.Controller.UserSectionController.UserSectionController;
import Client.View.Menus.UserSectionMenus.UserSection;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu {
    protected Menu superMenu;
    protected ArrayList<Menu> subMenus;
    protected String name;
    protected String command;
    protected static Scanner scanner = new Scanner(System.in);
    public Menu(Menu superMenu, String name) {
        subMenus = new ArrayList<>();
        this.superMenu = superMenu;
        this.name = name;
        if(!(this.superMenu instanceof RegisterLoginMenu)){
            this.addSubMenu(new RegisterLoginMenu(this,"Register or Login"));
        }
    }

    protected Menu() {
    }

    public void show(){
        setRightNameForLoginMenu();
        for (Menu subMenu : subMenus) {

            System.out.println(subMenu.getName());
        }
        if(superMenu != null)
            System.out.println("back");
    }
    public void execute(){
        command = scanner.nextLine();
        if(command.equalsIgnoreCase("help")) {
            this.show();
            this.execute();
        }
        if(command.equalsIgnoreCase("back")) {
            if (superMenu != null) {
                superMenu.show();
                superMenu.execute();
            }
            else{
                System.out.println("There isn't any back button here");
            }

        }


        for (Menu subMenu : subMenus) {
            if(command.equalsIgnoreCase(subMenu.getName())) {
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
    public Menu getSuperMenu(){
        return superMenu;
    }
    public void setRightNameForLoginMenu(){
        if(!(this.superMenu instanceof RegisterLoginMenu)){
            if(UserSectionController.getLoggedInPerson()==null){
                this.subMenus.get(0).setName("Register or Login");
            }else{
                this.subMenus.get(0).setName("Logout");
            }
        }
    }
    public void setName(String name){
        this.name=name;
    }

}
