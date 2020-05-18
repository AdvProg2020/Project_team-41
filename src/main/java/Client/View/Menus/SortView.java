package Client.View.Menus;

import Client.Controller.FilterController;
import Client.Controller.SortController;
import Client.Models.Product;

public class SortView extends Menu{
    public SortView(Menu superMenu) {
        super(superMenu,"Sort");
        addSubMenu(sortAnAvailableSort());
        addSubMenu(disableSort());
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void commands() {

        super.commands();
        System.out.println("show available sorts");
        System.out.println("current sort");
    }

    @Override
    public void execute() {
        super.execute();
        if(command.equalsIgnoreCase("show available sorts")){
            showAvailableSorts();
        }
        else if(command.equalsIgnoreCase("current sort")){
            currentSort();
        }
        System.out.println("Invalid command!");
        this.show();
        this.execute();
    }

    private void showAvailableSorts() {
            System.out.println("product name/ company name/ price/ seller username/ score/ view\n");
            this.show();
            this.execute();
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
                    System.out.format("| %-16s | %-10s | %-10d |%d\n",sortedProduct.getProductId(),sortedProduct.getName(),sortedProduct.getPrice() , sortedProduct.getViews() );
                }
                System.out.print("+------------------+------------+------------+\n");

                super.show();
                super.execute();
            }
        };
    }

    private void currentSort() {
                System.out.println(SortController.getInstance().getSortFeature()+"\n");
                this.show();
                this.execute();
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
