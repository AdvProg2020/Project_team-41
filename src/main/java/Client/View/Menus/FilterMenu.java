package Client.View.Menus;

import Client.Controller.FilterController;
import Client.Models.Product;
import javafx.util.Pair;

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
        System.out.println("Invalid Command!");
        this.commands();
        this.execute();
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
                            try {
                                FilterController.getInstance().setDefinitePrice(scanner.nextInt());
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
                                        int min = scanner.nextInt();
                                        System.out.println("Enter Max:");
                                        int max = scanner.nextInt();
                                        FilterController.getInstance().getRangeFeatures().put(featureName, new Pair<>(min, max));

                                    } else {
                                        System.out.println("Enter feature value to filter:");
                                        FilterController.getInstance().getDefiniteIntFeatures().put(featureName, scanner.nextInt());
                                    }
                                } else {
                                    System.out.println("Enter feature value to filter:");
                                    FilterController.getInstance().getDefiniteStringFeatures().put(featureName, scanner.nextLine());
                                }
                            } catch (ClassNotFoundException e) {
                                System.err.println(e.getMessage());
                            } catch (NullPointerException e) {
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
                        System.out.format("| %-16s | %-10s | %-10d |\n",filterProduct.getProductId(),filterProduct.getName(),filterProduct.getPrice() );
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

    private Menu currentFilters() {
        return new Menu(this, "current filters") {
            @Override
            public void show() {
                System.out.println(FilterController.getInstance().toString());
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
                if (filterToBeDisabled.equals("category special features")) {
                    System.out.println("Enter the feature:");
                    try {
                        if (FilterController.getInstance().getFilterCategory() == null)
                            System.err.println("Not any category is selected yet");
                        else
                            FilterController.getInstance().disableSpecialFeature(scanner.nextLine());
                        System.out.println("The filtered feature was successfully disabled");
                    } catch (ClassNotFoundException e) {
                        System.err.println(e.getMessage());
                    } catch (NullPointerException e) {
                        System.err.println(e.getMessage());
                    }
                } else
                    try {
                        FilterController.getInstance().disableFilter(filterToBeDisabled);
                        System.out.println("The filter was successfully disabled");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                super.execute();
            }
        };
    }


}
