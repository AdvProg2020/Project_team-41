package Client.View.Menus;

import Client.Controller.SortController;
import Client.Models.Product;

public class SortView extends Menu{
    public SortView(Menu superMenu) {
        super(superMenu,"Sort");
        addSubMenu(showAvailableSorts());
        addSubMenu(sortAnAvailableSort());
        addSubMenu(currentSort());
        addSubMenu(disableSort());
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }

    private Menu showAvailableSorts() {
    return new Menu(this , "show available sorts") {
        @Override
        public void show() {
            System.out.println("product name/ company name/ price/ seller username/ score/ view");
        }

        @Override
        public void execute() {
            super.execute();
        }
    };
    }

    private Menu sortAnAvailableSort() {
        return new Menu(this, "sort") {
            @Override
            public void show() {
                System.out.println("Enter an available sort:");
            }

            @Override
            public void execute() {
                SortController.getInstance().setSortFeature(scanner.nextLine());

                System.out.println("\n" + "Sorted Products:");
                for (Product sortedProduct : SortController.getInstance().getSortedProducts(this.superMenu instanceof OffsMenu)) {
                    System.out.println(sortedProduct.getName());
                }
                super.execute();
            }
        };
    }

    private Menu currentSort() {
        return new Menu(this , "current sort") {
            @Override
            public void show() {
                System.out.println(SortController.getInstance().getSortFeature());
            }

            @Override
            public void execute() {
                super.execute();
            }
        };
    }

    private Menu disableSort() {
        return new Menu(this, "disable sort") {
            @Override
            public void show() {
                super.show();
            }

            @Override
            public void execute() {
                SortController.getInstance().setSortFeature("view");
                System.out.println("Sorting is by default(view) now");
                super.execute();
            }
        };
    }

}
