package Client.View.Menus;

public class Offs extends Menu {
    public Offs(Menu superMenu) {
        super(superMenu, "Offs");
        addSubMenu(new Product(this));
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
