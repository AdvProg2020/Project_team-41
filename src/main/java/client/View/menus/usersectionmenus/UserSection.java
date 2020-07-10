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
                ArrayList<String> personalInfo = null;
                try {
                    personalInfo = UserSectionController.getPersonalInfo(UserSectionController.getLoggedInPerson());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for (String info : personalInfo) {
                    System.out.println(info);
                }

            }
            private void edit(){
                String field;
                String edit;
                System.out.println("what field do you want to edit?(it can be password too)");
                field = scanner.nextLine();
                System.out.println("what do you want it to be?(if it's password:(currentPassword,newPassword))");
                edit = scanner.nextLine();


                try {
                    UserSectionController.edit(field,edit);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                    this.show();
                    this.execute();
                }
                System.out.println("successfully edited " + field);
                this.show();
                this.execute();
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
                System.out.println("invalid command");
                this.show();
                this.execute();


            }
        };
       }

}
