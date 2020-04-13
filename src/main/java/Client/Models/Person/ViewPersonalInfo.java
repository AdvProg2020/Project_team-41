package Client.Models.Person;

import Client.Controller.UserSectionController;
import Client.View.Menus.Menu;

import java.util.ArrayList;

public class ViewPersonalInfo extends Menu {

    public ViewPersonalInfo(Menu superMenu) {
        super(superMenu, "ViewPersonalInfo");
    }

    public void viewPersonalInfo() {
        ArrayList<String> personalInfo = UserSectionController.viewPersonalInfo(UserSectionController.getLoggedInPerson());
        //todo print arraylist
    }

    @Override
    public void execute() {
        viewPersonalInfo();
        //todo for edit


    }
}
