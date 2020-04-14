package Client.View.Menus.UserSectionMenus;

import Client.Models.Person.Person;
import Client.View.Menus.Menu;

public class ManagerSection extends UserSection {

    public ManagerSection(Menu superMenu) {
        super(superMenu, "ManagerSection");
        addSubMenu(this.addManageUsers());

    }

    @Override
    public void show() {
        super.show();

    }

    @Override
    public void execute() {
        super.execute();

    }
    private Menu addManageUsers(){
        return new Menu(this,"manageUsers") {
                    public void view(Person username){

                    }
                    @Override
                    public void show() {
                        super.show();
                    }

                    @Override
                    public void execute() {
                        super.execute();
                    }
                };
    }

}
