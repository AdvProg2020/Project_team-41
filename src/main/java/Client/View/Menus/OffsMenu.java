package Client.View.Menus;

public class OffsMenu extends Menu {
    public OffsMenu(Menu superMenu) {
        super(superMenu, "Offs");
        addSubMenu(new ProductMenu(this));
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
