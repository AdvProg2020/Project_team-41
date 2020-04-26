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
               System.out.println("product name/ company name/ price/ seller/ existence");
               System.out.println("category name");
               if(FilterController.getInstance().getFilterCategory() != null){
                   System.out.println("category special features:");
                   for (String specialFeature : FilterController.getInstance().getFilterCategory().getSpecialFeatures()) {
                       System.out.println(specialFeature);
                   }}
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
                try {
                    FilterController.getInstance().setFilterCategory(givenFilter);
                    System.out.println("filtered products:");
                    for (Product filteredProduct : FilterController.getInstance().getFilteredProducts()) {
                        System.out.println(filteredProduct.getName());
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
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
                try {
                    FilterController.getInstance().setFilterCategory(null);
                    System.out.println("The filter has been disabled");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                super.execute();
            }
        };
    }
}
