package Client.View.Menus;

import Client.Controller.AllProductsController;
import Client.Controller.ProductController;
import Client.Controller.SortController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Comment;
import Client.Models.CommentSituation;
import Client.Models.Product;
import Client.Models.SpecialFeature;

public class ProductMenu extends Menu {
    private Product theProduct;
    private Menu registerMenu;

    public ProductMenu(Menu superMenu) {
        super(superMenu, "Product");
        addSubMenu(addDigest());
        addSubMenu(addAttribute());
        addSubMenu(addComments());
        addSubMenu(addCompare());
       registerMenu = new RegisterLoginMenu(addDigest(), "Register or Login");
    }

    public Product getTheProduct() {
        return theProduct;
    }

    public void setTheProduct(Product theProduct) {
        this.theProduct = theProduct;
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
        System.out.println("invalid command");
        this.show();
        this.execute();
    }


    public Menu addDigest() {
        return new Menu(this, "digest") {
            @Override
            public void show() {
                if (this.subMenus.size() < 2) {
                    this.addSubMenu(addToCart());
                }
                System.out.println(
                        "name: " + theProduct.getName() + "\n" +
                                "description: " + theProduct.getDescription() + "\n" +
                                "price: " + theProduct.getPrice() + "\n" +
                                amountOfDiscount() +
                                "category: " + theProduct.getCategory().getName() + "\n" +
                                "seller: " + theProduct.getSeller().getUserName() + "\n" +
                                "average score: " + theProduct.calculateAverageScore() + "\n"
                );
                super.show();
            }

            @Override
            public void execute() {
                  super.execute();
                  System.out.println("Invalid command!");
                  this.show();
                  this.execute();
            }
            private Menu addToCart(){
                return new Menu(this, "add to cart") {

                    @Override
                    public  void show(){}

                    @Override
                    public void execute() {
                        try {
                            ProductController.addToCart(theProduct);
                            System.out.println("The product added to cart successfully");
                            super.show();
                            super.execute();
                        } catch (NullPointerException e) {
                            System.out.println(e.getMessage());

                            if (this.subMenus.size() < 2) {
                                this.addSubMenu(registerMenu);}
                            registerMenu.show();
                            registerMenu.execute();
                        }
                        catch (ClassCastException e){
                            System.out.println(e.getMessage());

                        }

                    }
                };
            }

        };
    }

    public Menu addAttribute() {
        return new Menu(this, "attributes") {
            @Override
            public void show() {
                printProductAttributes(theProduct);
                System.out.println();
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

    public Menu addComments() {
        return new Menu(this, "comments") {
            @Override
            public void show() {
                if(this.subMenus.size()<2) {
                    this.addSubMenu(addAddComment());
                }
                System.out.println("comments:");
                for (Comment comment : theProduct.getComments()) {
                    if(comment.getCommentSituation().equals(CommentSituation.CONFIRMED)) {
                        System.out.println(comment.toString());
                    }
                }
                System.out.println("score:"+theProduct.calculateAverageScore()+"\n");
                super.show();

            }

            @Override
            public void execute() {
                super.execute();
                System.out.println("Invalid command");
                this.commands();
                this.execute();
            }
            private Menu addAddComment(){
                return new Menu(this, "add comment") {
                    @Override
                    public void show() {
                        System.out.println("Enter title of your comment");
                    }

                    @Override
                    public void execute() {
                        String title = scanner.nextLine();
                        System.out.println("Enter your comment");
                        String content = scanner.nextLine();
                        try {
                        ProductController.addComment(title, content , theProduct);
                            System.out.println("Thanks for your comment");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                    }
                        superMenu.show();
                        superMenu.execute();
                    }
                };
            }
        };
    }

    public Menu addCompare() {
        return new Menu(this, "compare") {
            private int id;

            @Override
            public void show() {
                System.out.println("Enter another product ID");
            }

            @Override
            public void execute() {
                String secondId = scanner.nextLine();
                Product secondProduct = null;
                try {
                    secondProduct = AllProductsController.getInstance().getProduct(secondId);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    superMenu.show();
                    superMenu.execute();
                }
                if(!theProduct.getCategory().getName().equals(secondProduct.getCategory().getName())){
                    System.out.println("You can only compare products of a same category!");
                    superMenu.show();
                    superMenu.execute();
                }
                compareTable(theProduct,secondProduct);
                /*System.out.println("product(you entered just now) details:");
                try {
                    Product secondProduct = AllProductsController.getInstance().getProduct(secondId);
                    printProductAttributes(secondProduct);
                    System.out.println("average score: " + secondProduct.calculateAverageScore());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                System.out.println("\nproduct(whose page you are in) details:");
                printProductAttributes(theProduct);
                System.out.println("average score: " + theProduct.calculateAverageScore());

                 */
                super.execute();
                System.out.println("Invalid command!");
                this.commands();
                this.execute();
            }

        };
    }

    private void printProductAttributes(Product theProduct){

        System.out.println(theProduct + ", category: " + theProduct.getCategory().getName());
        for (String featureName : theProduct.getSpecialFeatures().keySet()) {
            System.out.print("feature name: " + featureName );
            SpecialFeature productSpecialFeature = theProduct.getSpecialFeatures().get(featureName);
            if(productSpecialFeature.StringOrInt().equalsIgnoreCase("int"))
                System.out.println(", feature value: " + productSpecialFeature.getSpecialFeatureInt());
            else
                System.out.println(", feature value: " + productSpecialFeature.getSpecialFeatureString());
        }
    }
    private String amountOfDiscount(){
        try {
            int amountOfDiscount=ProductController.getInstance().amountOfDiscount(theProduct.getProductId());
            return "price with discount: "+(theProduct.getPrice()*(100-amountOfDiscount))/100+"\n";
        } catch (Exception e) {
            return e.getMessage()+"\n";
        }
    }
    public void compareTable(Product product1,Product product2){
        System.out.print("+------------------+----------------+----------------+\n");
        System.out.print("|                  |   Product 1    |    Product 2   |\n");
        System.out.print("+------------------+----------------+----------------+\n");
        System.out.format("| Product Id       | %-14s | %-14s |\n",product1.getProductId(),product2.getProductId());
        System.out.format("| Product Name     | %-14s | %-14s |\n",product1.getName(),product2.getName());
        System.out.format("| Category         | %-14s | %-14s |\n",product1.getCategory().getName(),product2.getCategory().getName());
        System.out.format("| Product Price    | %-14d | %-14d |\n",product1.getPrice(),product2.getPrice());
        System.out.format("| Seller           | %-14s | %-14s |\n",product1.getSeller().getUserName(),product2.getSeller().getUserName());
        System.out.format("| Average Score    | %-14d | %-14d |\n",product1.calculateAverageScore(),product2.calculateAverageScore());
        try {
            for (String featureName : product1.getCategory().getSpecialFeatures()) {
                SpecialFeature productSpecialFeature = product1.getSpecialFeatures().get(featureName);
                if(productSpecialFeature.StringOrInt().equalsIgnoreCase("int"))
                    System.out.format("| %-16s | %-14d | %-14d |\n",featureName,product1.getSpecialFeatures().get(featureName).getSpecialFeatureInt()
                            ,product2.getSpecialFeatures().get(featureName).getSpecialFeatureInt());

                else
                    System.out.format("| %-16s | %-14s | %-14s |\n",featureName,product1.getSpecialFeatures().get(featureName).getSpecialFeatureString()
                            ,product2.getSpecialFeatures().get(featureName).getSpecialFeatureString());


            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.print("+------------------+----------------+----------------+\n");
    }
}
