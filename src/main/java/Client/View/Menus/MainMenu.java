package Client.View.Menus;

import Client.Controller.UserSectionController.ManagerController;

public class MainMenu extends Menu {
    private static MainMenu single_instance = null;
    public static MainMenu getInstance()
    {
        if (single_instance == null)
            single_instance = new MainMenu();

        return single_instance;
    }

    @Override
    public void execute() {
        super.execute();
        //todo remove code between comments
        //
        if(command.equalsIgnoreCase("showUsers")){
            for (String user : ManagerController.getInstance().getAllUsers()) {
                System.out.println(user);
            }
            this.show();
            this.execute();
        }
        //
        System.out.println("invalid command");
        this.show();
        this.execute();
    }

    private MainMenu() {
        super(null, "Main");
        addSubMenu(new AllProductsMenu(this));
        addSubMenu(new OffsMenu(this));


    }
    public void removeUserSection(){
        this.subMenus.remove(subMenus.size()-1);
    }


}