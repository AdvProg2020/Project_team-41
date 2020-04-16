package Client.View.Menus;

public class RegisterLoginMenu extends Menu {
    public RegisterLoginMenu(Menu superMenu) {
        //toDoIfLogged
            super(superMenu, "RegisterLogin");
            this.addSubMenu(addCreate());
            this.addSubMenu(addLogin());

        //elseLogout


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
}
