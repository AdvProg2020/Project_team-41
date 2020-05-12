package Client.View.Menus;

import Client.Controller.AllProductsController;
import Client.Controller.OffsController;
import Client.Models.Off;
import Client.Models.Product;

public class OffsMenu extends Menu {
    public OffsMenu(Menu superMenu) {
        super(superMenu, "Offs");
        addSubMenu(addShowProduct());
        addSubMenu(new FilterMenu(this));
        addSubMenu(new SortView(this));
    }

    @Override
    public void show() {
        System.out.print("+------------------+------------+------------+---------------------+------------+-----------+\n");
        System.out.print("| Product id       | Name       | Price      | Price with discount | Start date | End date  |\n");
        System.out.print("+------------------+------------+------------+---------------------+------------+-----------+\n");
        for (Off off : OffsController.getInstance().getOffs()) {
            for (Product product : off.getProducts()) {
                System.out.format("| %-16s | %-10s | %-10d | %-19s | %-10s | %10s |\n", product.getProductId(), product.getName(),
                        product.getPrice(),(product.getPrice()*(100-off.getAmountOfDiscount()))/100,off.getStartDate(),off.getEndDate());
            }
        }
        System.out.print("+------------------+------------+------------+---------------------+------------+-----------+\n");
        super.show();

    }

    @Override
    public void execute() {
        super.execute();
        System.out.println("Invalid Command!");
        this.commands();
        this.execute();
    }
    private Menu addShowProduct(){
        return new Menu(this,"Show product") {
            @Override
            public void show() {
                System.out.println("enter the productId:");
            }

            @Override
            public void execute() {
                ProductMenu productMenu = new ProductMenu(this.superMenu);
                String productId = scanner.nextLine();
                try {
                    productMenu.setTheProduct(AllProductsController.getInstance().getProduct(productId));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    this.superMenu.commands();
                    this.superMenu.execute();
                }
                productMenu.show();
                productMenu.execute();
            }
        };
    }
}
