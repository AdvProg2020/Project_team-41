package Client.View.Menus;

import Client.Controller.AllProductsController;
import Client.Controller.OffsController;
import Client.Models.Off;
import Client.Models.Product;
import Server.Controller.TimeControl;

import java.util.ArrayList;
import java.util.Date;

public class OffsMenu extends Menu {
    ProductMenu productMenu;
    public OffsMenu(Menu superMenu) {
        super(superMenu, "Offs");
        addSubMenu(addShowProduct());
        addSubMenu(new FilterMenu(this));
        addSubMenu(new SortView(this));
        productMenu=new ProductMenu(this);
    }

    @Override
    public void show() {
        System.out.print("+------------------+------------+------------+---------------------+---------------------+--------------------+\n");
        System.out.print("| Product id       | Name       | Price      | Price with discount |      Start date     |       End date     |\n");
        System.out.print("+------------------+------------+------------+---------------------+---------------------+--------------------+\n");
        ArrayList<Off> offsToDelete=new ArrayList<>();
        Date date=new Date();
        for (Off off : OffsController.getInstance().getOffs()) {
            if(date.after(off.getEndDate())){
                offsToDelete.add(off);
                continue;
            }
            for (Product product : off.getProducts()) {
                System.out.format("| %-16s | %-10s | %-10d | %-19s | %-19s | %19s |\n", product.getProductId(), product.getName(),
                        product.getPrice(),(product.getPrice()*(100-off.getAmountOfDiscount()))/100,
                        TimeControl.getJalaliDateAndTimeForPrint(off.getStartDate()),TimeControl.getJalaliDateAndTimeForPrint(off.getEndDate()));
            }
        }
        System.out.print("+------------------+------------+------------+---------------------+---------------------+--------------------+\n");
        OffsController.getInstance().deleteOffs(offsToDelete);
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
                String productId = scanner.nextLine();
                try {
                    AllProductsController.getInstance().goToProductPage(productId , productMenu);
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
