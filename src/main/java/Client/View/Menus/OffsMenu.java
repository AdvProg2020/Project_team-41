package Client.View.Menus;

import Client.Controller.OffsController;
import Client.Models.Off;
import Client.Models.Product;

public class OffsMenu extends Menu {
    public OffsMenu(Menu superMenu) {
        super(superMenu, "Offs");
        addSubMenu(new ProductMenu(this));
    }

    @Override
    public void show() {
        System.out.print("+------------------+------------+------------+---------------------+------------+-----------+\n");
        System.out.print("| Product id       | Name       | Price      | Price with discount | Start date | End date  |\n");
        System.out.print("+------------------+------------+------------+---------------------+------------+-----------+\n");
        for (Off off : OffsController.getInstance().getOffs()) {
            for (Product product : off.getProducts()) {
                System.out.format("| %-16s | %-10s | %-10d | %-19s | %-10s | %10s |%n", product.getProductId(), product.getName(),
                        product.getPrice(),(product.getPrice()*(100-off.getAmountOfDiscount()))/100,off.getStartDate(),off.getEndDate());
            }
        }
        System.out.print("+------------------+------------+------------+---------------------+------------+-----------+\n");

    }

    @Override
    public void execute() {

    }
}
