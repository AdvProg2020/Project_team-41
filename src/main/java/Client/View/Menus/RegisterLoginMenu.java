package Client.View.Menus;

import Client.Controller.UserSectionController.UserSectionController;
import Client.View.Menus.UserSectionMenus.UserSection;

public class RegisterLoginMenu extends Menu {
    public RegisterLoginMenu(Menu superMenu,String name) {

        super(superMenu, name);
        if (UserSectionController.getLoggedInPerson() == null) {
            this.addSubMenu(addCreate());
            this.addSubMenu(addLogin());
        }
        else this.addSubMenu(addLogout());

    }
    public Menu addCreate(){
        return new Menu(this, "Create") {
            @Override
            public void show() {
                //to do
            }

            @Override
            public void execute() {
                //to do
            }
        };
    }
    public Menu addLogin(){
        return new Menu(this, "login") {
            @Override
            public void show() {
                //to do
            }

            @Override
            public void execute() {
                //to do
            }
        };
    }
    public Menu addLogout(){
        return new Menu(this,"Logout") {
            @Override
            public void show() {
                System.out.println("GoodBye!");
            }

            @Override
            public void execute() {
                UserSectionController.setLoggedInPerson(null);
                //todo other things
            }
        };
    }
}
