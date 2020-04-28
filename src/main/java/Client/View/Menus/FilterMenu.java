package Client.View.Menus;

import Client.Controller.AllProductsController;
import Client.Controller.FilterController;
import Client.Controller.ProductController;
import Client.Models.Category;
import Client.Models.Product;
import Server.Database;
import javafx.util.Pair;

import java.util.Scanner;

public class FilterMenu extends Menu {

    public FilterMenu(Menu superMenu) {
        super(superMenu, "Filter");
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

    private Menu showAvailableFilters() {
        return new Menu(this, "show available filters") {
            @Override
            public void show() {
                System.out.println("product name/ company name/ price/ seller/ existence");
                System.out.println("category name");
                if (FilterController.getInstance().getFilterCategory() != null) {
                    System.out.println("category special features:");
                    for (String specialFeature : FilterController.getInstance().getFilterCategory().getSpecialFeatures()) {
                        System.out.println(specialFeature);
                    }
                }
            }

            @Override
            public void execute() {
                super.execute();
            }
        };
    }

    private Menu filterAnAvailableFilter() {
        return new Menu(this, "filter") {
            @Override
            public void show() {
                System.out.println("Enter an available filter type:");
            }

            @Override
            public void execute() {
                String filterType = scanner.nextLine();
                switch (filterType) {
                    case "product name": {
                        System.out.println("Enter the name:");
                        String name = scanner.nextLine();
                        FilterController.getInstance().setName(name);
                        break;
                    }
                    case "company name": {
                        System.out.println("Enter the name:");
                        String name = scanner.nextLine();
                        FilterController.getInstance().setCompanyName(name);
                        break;
                    }
                    case "price": {
                        System.out.println("range or definite?");
                        if (scanner.nextLine().equals("range")) {
                            System.out.println("Enter Min:");
                            int min = scanner.nextInt();
                            System.out.println("Enter Max:");
                            int max = scanner.nextInt();
                            FilterController.getInstance().setPriceMinMax(new Pair<>(min, max));
                        } else {
                            System.out.println("Enter the price:");
                            FilterController.getInstance().setDefinitePrice(scanner.nextInt());
                        }
                        break;
                    }
                    case "seller": {
                        System.out.println("Enter seller username:");
                        try {
                            FilterController.getInstance().setSellerUserName(scanner.nextLine());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case "existence": {
                        System.out.println("existed products? (yes/no)");
                        if (scanner.nextLine().equals("yes"))
                            FilterController.getInstance().setExistence(1);
                        else
                            FilterController.getInstance().setExistence(0);
                        break;
                    }
                    case "category name": {
                        System.out.println("Enter the name:");
                        try {
                            FilterController.getInstance().setFilterCategory(scanner.nextLine());
//                            System.out.println("filtered products:");
//                            for (Product filteredProduct : FilterController.getInstance().getFilteredProducts()) {
//                                System.out.println(filteredProduct.getName());
//                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    default:
                        System.out.println("invalid filter");
                }

                if(FilterController.getInstance().filterProducts().size()==0)
                    System.out.println("Nothing found");
                else {
                    for (Product filterProduct : FilterController.getInstance().filterProducts()) {
                        System.out.println(filterProduct.getName());
                    }
                }

                System.out.println("any other filter? (yes/no)");
                String yesOrNo = scanner.nextLine();
                if (yesOrNo.equals("yes")) {
                    this.show();
                    this.execute();
                } else {
                    this.superMenu.show();
                    this.superMenu.execute();
                }

            }
        };
    }

    private Menu currentFilters() {
        return new Menu(this, "current filters") {
            @Override
            public void show() {
                Category filterCategory = FilterController.getInstance().getFilterCategory();

                if (filterCategory != null)
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

    private Menu disableFilter() {
        return new Menu(this, "disable filter") {
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
