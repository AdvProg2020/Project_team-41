package Models.Person;

import Controller.UserSectionController;
import View.Menus.Menu;

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
