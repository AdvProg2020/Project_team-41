package Client.View.Menus;

import Client.Controller.FilterController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.View.Menus.LoginRegister.LoginForm;
import Client.View.Menus.LoginRegister.Logout;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.App;

import javax.swing.*;
import java.io.IOException;
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
        if(!(this.superMenu instanceof RegisterLoginMenu)&&!(this instanceof RegisterLoginMenu)){
            this.addSubMenu(new RegisterLoginMenu(this,"Register or Login"));
        }
    }

    protected Menu() {
    }

    public void show(){
        this.commands();
    }
    public void execute() {
        command = scanner.nextLine();
        if(this instanceof RegisterLoginMenu) {
            handelLogoutInRegisterMenu();
        }


        if(command.equalsIgnoreCase("help")) {
            this.commands();
            this.execute();
        }else if(command.equalsIgnoreCase("back")) {
            if (superMenu != null) {
                if (this instanceof AllProductsMenu || this instanceof OffsMenu) {
                    FilterController.resetFilterController();
                }

                superMenu.show();
                superMenu.execute();
            }
            else{
                System.out.println("There isn't any back button here");
            }

        }else if(command.equalsIgnoreCase("end program")){
                System.exit(0);
        }else {
            for (Menu subMenu : subMenus) {
                if (command.equalsIgnoreCase(subMenu.getName())) {
                    subMenu.show();
                    subMenu.execute();
                }
            }
        }
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
        if(!(this.superMenu instanceof RegisterLoginMenu)&&!(this instanceof RegisterLoginMenu)){
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
    public void handelLogoutInRegisterMenu(){
        if(UserSectionController.getLoggedInPerson()==null){
            if(command.equalsIgnoreCase("logout")){
                System.out.println("Invalid command!");
                this.show();
                this.execute();
            }
        }
        if(UserSectionController.getLoggedInPerson()!=null){
            if(command.equalsIgnoreCase("create")||command.equalsIgnoreCase("login")){
                System.out.println("Invalid command!");
                this.show();
                this.execute();
            }
        }
    }

    public void commands(){
        setRightNameForLoginMenu();
        for (Menu subMenu : subMenus) {
            if(this instanceof RegisterLoginMenu){
                break;
            }
            System.out.println(subMenu.getName());
        }

        if(this instanceof RegisterLoginMenu){
            if(UserSectionController.getLoggedInPerson()==null){
                System.out.println("Create\nlogin");
            }
            else{
                System.out.println("Are you sure you want to logout?(logout/back)");
            }
        }

        if(superMenu != null)
            System.out.println("back");
        System.out.println("help");
        System.out.println("end Program");
    }

    public static Scene scene;
    public static Stage window;

    public void login(String fxmlAddress) throws IOException {
        LoginForm.father=fxmlAddress;
        window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Login or register");

        scene = new Scene(loadFXML("loginForm"));
        window.setScene(scene);
        window.showAndWait();
    }
    public static void setRootForNewWindow(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static void closeWindow(){
        window.close();
    }
    public void logout(String fxmlAddress) throws IOException {
        Logout.father=fxmlAddress;
        window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Logout");

        scene = new Scene(loadFXML("logout"));
        window.setScene(scene);
        window.showAndWait();
    }


}
