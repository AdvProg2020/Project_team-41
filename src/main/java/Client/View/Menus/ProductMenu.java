package Client.View.Menus;

import Client.Controller.ProductController;
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

            }
        };
    }

    public Menu addAttribute() {
        return new Menu(this, "attributes") {
            @Override
            public void show() {
                System.out.println(theProduct + ", category: " + theProduct.getCompanyName());
                for (String featureName : theProduct.getSpecialFeatures().keySet()) {
                    System.out.print("feature name: " + featureName );
                    SpecialFeature productSpecialFeature = theProduct.getSpecialFeatures().get(featureName);
                    if(productSpecialFeature.StringOrInt().equalsIgnoreCase("int"))
                        System.out.println(", feature value: " + productSpecialFeature.getSpecialFeatureInt());
                    else
                        System.out.println(", feature value: " + productSpecialFeature.getSpecialFeatureString());
                }
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
                //TODO print comments
            }

            @Override
            public void execute() {
                this.addSubMenu(new Menu(this, "add comment") {
                    @Override
                    public void show() {
                        //TODO print sth for user to enter comment
                    }

                    @Override
                    public void execute() {
                        //TODO get title and content:
                        String title = "";
                        String content = "";
                        ProductController.addComment(title, content);
                    }
                });
            }
        };
    }

    public Menu addCompare() {
        return new Menu(this, "compare") {
            private int id;

            @Override
            public void show() {
                super.show();
            }

            @Override
            public void execute() {
                super.execute();
            }

        };
    }
}
