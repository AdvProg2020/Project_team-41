package Client.View.Menus;

import Client.Controller.AllProductsController;
import Client.Controller.ProductController;
import Client.Models.Comment;
import Client.Models.Product;
import Client.Models.SpecialFeature;

public class ProductMenu extends Menu {
    private Product theProduct;

    public ProductMenu(Menu superMenu) {
        super(superMenu, "Product");
        addSubMenu(addDigest());
        addSubMenu(addAttribute());
        addSubMenu(addComments());
        addSubMenu(addCompare());
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
    }


    public Menu addDigest() {
        return new Menu(this, "digest") {
            @Override
            public void show() {
                System.out.println(
                        "name: " + theProduct.getName() + "\n" +
                                "description: " + theProduct.getDescription() + "\n" +
                                "price: " + theProduct.getPrice() + "\n" +
                                amountOfDiscount()+
                                "category: " + theProduct.getCategory().getName() + "\n" +
                                "seller: " + theProduct.getSeller().getUserName() + "\n" +
                                "average score: " + theProduct.calculateAverageScore()
                        //TODO print product discount ...
                );
            }

            @Override
            public void execute() {
                this.addSubMenu(new Menu(this, "add to cart") {
                    @Override
                    public void show() {
                        super.show();
                    }

                    @Override
                    public void execute() {
                        try {
                            ProductController.addToCart(theProduct);
                            System.out.println("The product added to cart successfully");
                            super.execute();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            //TODO go to register/login menu
                        }
                    }
                });
                  super.execute();
            }
        };
    }

    public Menu addAttribute() {
        return new Menu(this, "attributes") {
            @Override
            public void show() {
                printProductAttributes(theProduct);
            }

            @Override
            public void execute() {
                super.execute();
            }
        };
    }

    public Menu addComments() {
        return new Menu(this, "comments") {
            @Override
            public void show() {
                System.out.println("comments:");
                for (Comment comment : theProduct.getComments()) {
                    // TODO not certain about this syntax:
                    if(comment.getCommentSituation().equals("CONFIRMED")){
                        System.out.println(comment);
                    }
                }
                System.out.println("score:");
                System.out.println(theProduct.calculateAverageScore());
            }

            @Override
            public void execute() {
                this.addSubMenu(new Menu(this, "add comment") {
                    @Override
                    public void show() {
                        System.out.println("Enter title of your comment");
                    }

                    @Override
                    public void execute() {
                        String title = scanner.nextLine();
                        System.out.println("Enter your comment");
                        String content = scanner.nextLine();
                        ProductController.addComment(title, content , theProduct);
                        System.out.println("Thanks for your comment");
                        super.execute();
                    }
                });
            super.execute();
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
                System.out.println("product(you entered just now) details:");
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

                super.execute();
            }

        };
    }

    private void printProductAttributes(Product theProduct){

        System.out.println(theProduct + ", category: " + theProduct.getCategory());
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
}
