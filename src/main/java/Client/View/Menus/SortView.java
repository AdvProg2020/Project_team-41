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
        System.out.println("Invalid command!");
        this.show();
        this.execute();
    }

    private Menu showAvailableSorts() {
    return new Menu(this , "show available sorts") {
        @Override
        public void show() {
            System.out.println("product name/ company name/ price/ seller username/ score/ view\n");
        }

        @Override
        public void execute() {
            super.show();
            super.execute();
            System.out.println("Invalid command!");
            this.execute();
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
                boolean again = true;

                while (again){
                try {
                    SortController.getInstance().setSortFeature(scanner.nextLine());
                    again = false;
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                }}

                System.out.println("\n" + "Sorted Products:");
                System.out.print("+------------------+------------+------------+\n");
                System.out.print("| Product id       | Name       | Price      |\n");
                System.out.print("+------------------+------------+------------+\n");
                for (Product sortedProduct : SortController.getInstance().getSortedProducts(this.superMenu instanceof OffsMenu)) {
                    System.out.format("| %-16s | %-10s | %-10d |\n",sortedProduct.getProductId(),sortedProduct.getName(),sortedProduct.getPrice() );
                }
                System.out.print("+------------------+------------+------------+\n");

                super.show();
                super.execute();
            }
        };
    }

    private Menu currentSort() {
        return new Menu(this , "current sort") {
            @Override
            public void show() {
                System.out.println(SortController.getInstance().getSortFeature()+ "\n");
            }

            @Override
            public void execute() {
                super.show();
                super.execute();
            }
        };
    }

    private Menu disableSort() {
        return new Menu(this, "disable sort") {
            @Override
            public void show() {

            }

            @Override
            public void execute() {
                SortController.getInstance().setSortFeature("view");
                System.out.println("Sorting is by default(view) now\n");

                super.show();
                super.execute();
            }
        };
    }

}
