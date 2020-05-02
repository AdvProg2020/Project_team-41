package Client.View.Menus;

import Client.Controller.ProductController;
import Client.Models.Product;

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
                        theProduct.getName() + "\n" +
                        theProduct.getDescription() + "\n" +
                        theProduct.getPrice() + "\n" +
                        theProduct.getCategory().getName() + "\n" +
                        theProduct.getSeller().getUserName() + "\n" +
                        theProduct.calculateAverageScore()
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
                super.show();
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
