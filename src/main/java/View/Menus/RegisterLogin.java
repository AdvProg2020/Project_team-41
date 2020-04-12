package View.Menus;

public class RegisterLogin extends Menu {
    public RegisterLogin(Menu superMenu) {
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
