package Client.View.Menus;

import Client.Controller.AllProductsController;
import Client.Models.Category;

public class AllProductsMenu extends Menu {
    public AllProductsMenu(Menu superMenu) {
        super(superMenu, "AllProducts");
        addSubMenu(new FilterMenu(this));
        addSubMenu(new SortView(this));
        addSubMenu(addViewCategories());
        addSubMenu(addShowProducts());
        addSubMenu(addShowProduct());
    }

    //todo getter for unnamed subclasses

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }

    private Menu addViewCategories() {
        return new Menu(this, "ViewCategories") {
            @Override
            public void show() {
                for (Category category : AllProductsController.getInstance().getAllCategories()) {
                    System.out.println(category.getName());
                }
            }

            @Override
            public void execute() {
                super.execute();
            }
        };
    }
    private Menu addShowProducts(){
        return new Menu(this,"ShowProducts") {
            @Override
            public void show() {
                //todo
            }

            @Override
            public void execute() {
                //todo
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
                ProductMenu productMenu = new ProductMenu(this);
                String productId = scanner.nextLine();
                productMenu.setId(productId);
                productMenu.show();
                productMenu.execute();
            }
        };
    }


}
