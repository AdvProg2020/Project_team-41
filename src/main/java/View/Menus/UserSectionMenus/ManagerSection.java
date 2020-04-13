package View.Menus.UserSectionMenus;

import View.Menus.Menu;

public class ManagerSection extends UserSection {

    public ManagerSection(Menu superMenu) {
        super(superMenu, "ManagerSection");
        addSubMenu(new Menu(this,"manageUsers") {
            public void view(Person usernaem)
            @Override
            public void show() {
                super.show();
            }

            @Override
            public void execute() {
                super.execute();
            }
        });
    }

    @Override
    public void show() {
        super.show();

    }

    @Override
    public void execute() {
        super.execute();

    }
}
