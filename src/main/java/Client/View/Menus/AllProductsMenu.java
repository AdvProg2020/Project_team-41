package Client.View.Menus;

import Client.Controller.AllProductsController;
import Client.Controller.SortController;
import Client.Models.Category;
import Client.Models.Product;

public class AllProductsMenu extends Menu {
    ProductMenu productMenu;

    public AllProductsMenu(Menu superMenu) {
        super(superMenu, "AllProducts");
        addSubMenu(new FilterMenu(this));
        addSubMenu(new SortView(this));
        addSubMenu(addViewCategories());
        addSubMenu(addShowProducts());
        addSubMenu(addShowProduct());
        productMenu = new ProductMenu(this);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
        System.out.println("Invalid command!");
        this.show();
        this.execute();
    }

    private Menu addViewCategories() {
        return new Menu(this, "ViewCategories") {
            @Override
            public void show() {
                System.out.println("Categories are:");
                for (Category category : AllProductsController.getInstance().getAllCategories()) {
                    System.out.println(category.getName());
                }
                System.out.println("");
                super.show();
            }

            @Override
            public void execute() {
                super.execute();
                System.out.println("Invalid command!");
                this.commands();
                this.execute();
            }
        };
    }

    private Menu addShowProducts(){
        return new Menu(this,"ShowProducts") {
            @Override
            public void show() {
                System.out.print("+------------------+------------+------------+\n");
                System.out.print("| Product id       | Name       | Price      |\n");
                System.out.print("+------------------+------------+------------+\n");
                for (Product sortedProduct : SortController.getInstance().getSortedProducts(false)) {
                    System.out.format("| %-16s | %-10s | %-10d |\n",sortedProduct.getProductId(),sortedProduct.getName(),sortedProduct.getPrice() );
                }
                System.out.print("+------------------+------------+------------+\n");
                super.show();

            }

            @Override
            public void execute() {
                super.execute();
                System.out.println("Invalid command!");
                this.commands();
                this.execute();
            }
        };
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
                    productMenu.show();
                    productMenu.execute();
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "\n");
                    superMenu.show();
                    superMenu.execute();
                }

            }
        };
    }

}
