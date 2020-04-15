package Client.View.Menus;

public class RegisterLoginMenu extends Menu {
    public RegisterLoginMenu(Menu superMenu) {
        //toDoIfLogged
            super(superMenu, "RegisterLogin");
            this.addSubMenu(new Menu(this, "Create") {
                @Override
                public void show() {
                    //to do
                }

                @Override
                public void execute() {
                    //to do
                }
            });
            this.addSubMenu(new Menu(this, "login") {
                @Override
                public void show() {
                    //to do
                }

                @Override
                public void execute() {
                    //to do
                }
            });

        //elseLogout


    }
}
