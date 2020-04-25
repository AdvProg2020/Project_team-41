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
                ArrayList<String> personalInfo = UserSectionController.getPersonalInfo(UserSectionController.getLoggedInPerson());
                for (String info : personalInfo) {
                    System.out.println(info);
                }

            }
            private void edit(){
                try {
                    UserSectionController.edit(scanner.next(),scanner.next());
                }
                catch (Exception e){
                    System.out.println("You can't edit username");
                    this.show();
                    this.execute();
                }
            }

            @Override
            public void show() {
                super.show();
                System.out.println("edit");
            }

            @Override
            public void execute() {
                viewPersonalInfo();
                super.execute();
                if(command.equals("edit")){
                    edit();
                }


            }
        };
       }

    @Override
    public void execute() {
        super.execute();
    }
}
