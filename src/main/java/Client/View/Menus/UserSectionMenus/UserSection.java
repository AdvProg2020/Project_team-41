package Client.View.Menus.UserSectionMenus;

import Client.Controller.UserSectionController.UserSectionController;
import Client.View.Menus.Menu;

import java.util.ArrayList;

public abstract class UserSection extends Menu {

    public UserSection(Menu superMenu, String name) {
        super(superMenu, name);
        addSubMenu(this.addViewPersonalInfo());
    }
    private Menu addViewPersonalInfo() {
        return new Menu(this,"ViewPersonalInfo") {
            private void viewPersonalInfo(){
                ArrayList<String> personalInfo = UserSectionController.viewPersonalInfo(UserSectionController.getLoggedInPerson());
                //todo print arraylist
            }
            private void edit(String field){

            }

            @Override
            public void show() {
                super.show();
            }

            @Override
            public void execute() {
                viewPersonalInfo();
                super.execute();
            }
        };
       }

    @Override
    public void execute() {
        super.execute();
    }
}
