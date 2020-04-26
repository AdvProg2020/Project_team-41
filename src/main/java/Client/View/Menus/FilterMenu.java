package Client.View.Menus;

import Client.Controller.AllProductsController;
import Client.Controller.FilterController;
import Client.Controller.ProductController;
import Client.Models.Category;
import Client.Models.Product;

import java.util.Scanner;

public class FilterMenu extends Menu {

    public FilterMenu(Menu superMenu) {
        super(superMenu,"Filter");
        addSubMenu(showAvailableFilters());
        addSubMenu(filterAnAvailableFilter());
        addSubMenu(currentFilters());
        addSubMenu(disableFilter());
    }

    @Override
    public void show() {
        super.show();
    }
    @Override
    public void execute() {
        super.execute();
    }

    private Menu showAvailableFilters(){
       return new Menu(this , "show available filters") {
           @Override
           public void show() {
               System.out.println("Category Names:");
               for (Category eachCategory : AllProductsController.getInstance().getAllCategories()) {
                   System.out.println( eachCategory.getName());}
           }
           @Override
           public void execute() {
               super.execute();
           }
       };
    }

    private Menu filterAnAvailableFilter(){
        return new Menu(this , "filter") {
            @Override
            public void show() {
                System.out.println("Enter an available filter:");
            }

            @Override
            public void execute() {
                String givenFilter = scanner.nextLine();
                FilterController.getInstance().setFilterCategory(givenFilter);

                System.out.println("filtered products:");
                for (Product filteredProduct : FilterController.getInstance().getFilteredProducts()) {
                    System.out.println(filteredProduct.getName());
                }
                super.execute();
            }
        };
    }

    private Menu currentFilters(){
        return new Menu(this , "current filters") {
            @Override
            public void show() {
                Category filterCategory = FilterController.getInstance().getFilterCategory();

                if( filterCategory!=null)
                  System.out.println(filterCategory.getName());
                else
                    System.out.println("No filter is selected");
            }

            @Override
            public void execute() {
                super.execute();
            }
        };
    }

    private Menu disableFilter(){
        return new Menu(this , "disable filter") {
            @Override
            public void show() {
                System.out.println("Enter a selected filter:");
            }

            @Override
            public void execute() {
                String filterToBeDisabled = scanner.nextLine();
                FilterController.getInstance().setFilterCategory(null);
                System.out.println("The filter has been disabled");
                super.execute();
            }
        };
    }
}
