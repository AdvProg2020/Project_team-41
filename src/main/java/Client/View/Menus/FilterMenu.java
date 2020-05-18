package Client.View.Menus;

import Client.Controller.FilterController;
import Client.Models.Product;
import javafx.util.Pair;

import java.io.FileNotFoundException;

public class FilterMenu extends Menu {

    public FilterMenu(Menu superMenu) {
        super(superMenu, "Filter");
        addSubMenu(filterAnAvailableFilter());
        addSubMenu(disableFilter());
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void commands() {
        super.commands();
        System.out.println("show available filters");
        System.out.println("current filters");
    }

    @Override
    public void execute() {
        super.execute();
        if(command.equalsIgnoreCase("show available filters")){
            showAvailableFilters();
        }
        else if(command.equalsIgnoreCase("current filters")){
            currentFilters();
        }
        System.out.println("Invalid Command!");
        this.commands();
        this.execute();
    }

    private void showAvailableFilters() {
        System.out.println("product name/ company name/ price/ seller/ existence");
        System.out.println("category name");
        if (FilterController.getInstance().getFilterCategory() != null) {
            System.out.println("category special features:");
            for (String specialFeature : FilterController.getInstance().getFilterCategory().getSpecialFeatures()) {
                System.out.println(specialFeature);
            }
        }
        System.out.println("");
        this.show();
        this.execute();
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
                            int min = enterAnInteger();
                            System.out.println("Enter Max:");
                            int max = enterAnInteger();

                            FilterController.getInstance().setPriceMinMax(new Pair<>(min, max));
                        } else {
                            System.out.println("Enter the price:");
                            try {
                                FilterController.getInstance().setDefinitePrice(enterAnInteger());
                            } catch (Exception e) {
                                System.err.println(e.getMessage());
                            }
                        }
                        break;
                    }
                    case "seller": {
                        System.out.println("Enter seller username:");
                        try {
                            FilterController.getInstance().setSellerUserName(scanner.nextLine());
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
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
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    }
                    case "category special features": {
                        if (FilterController.getInstance().getFilterCategory() == null) {
                            System.err.println("No category is selected yet");
                            break;
                        } else {
                            System.out.println("Enter feature name:");
                            String featureName = scanner.nextLine();
                            try {
                                if (FilterController.getInstance().isTheFeatureNumeric(featureName)) {
                                    System.out.println("range or definite?");
                                    if (scanner.nextLine().equals("range")) {
                                        System.out.println("Enter Min:");
                                        int min = enterAnInteger();
                                        System.out.println("Enter Max:");
                                        int max = enterAnInteger();
                                        FilterController.getInstance().getRangeFeatures().put(featureName, new Pair<>(min, max));

                                    } else {
                                        System.out.println("Enter feature value to filter:");
                                        FilterController.getInstance().getDefiniteIntFeatures().put(featureName, enterAnInteger());

                                    }
                                } else {
                                    System.out.println("Enter feature value to filter:");
                                    FilterController.getInstance().getDefiniteStringFeatures().put(featureName, scanner.nextLine());
                                }
                            } catch (ClassNotFoundException | NullPointerException e) {
                                System.err.println(e.getMessage());
                            }
                            break;
                        }
                    }
                    default:
                        System.out.println("invalid filter");
                }


                if (FilterController.getInstance().filterProducts(this.superMenu instanceof OffsMenu).size() == 0)
                    System.out.println("Nothing found");
                else {
                    System.out.println("");
                    System.out.println("Filtered Products Are:");
                    System.out.print("+------------------+------------+------------+\n");
                    System.out.print("| Product id       | Name       | Price      |\n");
                    System.out.print("+------------------+------------+------------+\n");
                    for (Product filterProduct : FilterController.getInstance().filterProducts(this.superMenu instanceof OffsMenu)) {
                        System.out.format("| %-16s | %-10s | %-10d |\n", filterProduct.getProductId(), filterProduct.getName(), filterProduct.getPrice());
                    }
                    System.out.print("+------------------+------------+------------+\n");

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

    private void currentFilters() {
        System.out.println(FilterController.getInstance().toString()+"\n");
        this.show();
        this.execute();
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
                if (filterToBeDisabled.equals("category special features")) {
                    try {
                        if (FilterController.getInstance().getFilterCategory() == null)
                            System.err.println("Not any category is selected yet\n");
                        else {
                            System.out.println("Enter the feature:");
                            FilterController.getInstance().disableSpecialFeature(scanner.nextLine());
                            System.out.println("The filtered feature was successfully disabled\n");
                        }
                    } catch (ClassNotFoundException | NullPointerException | FileNotFoundException e) {
                        System.err.println(e.getMessage());
                    }
                } else {
                    try {
                        FilterController.getInstance().disableFilter(filterToBeDisabled);
                        System.out.println("The filter was successfully disabled\n");
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }

                super.show();
                super.execute();

            }
        };
    }

    private int enterAnInteger( ) {
        int number = 0;
        boolean again = true;
        while (again) {
            try {
                number = Integer.parseInt(scanner.nextLine());
                again = false;
            } catch (Exception e) {
                System.out.println("You must enter an integer:");
            }
        }
        return number;
    }

}
