package Client.View.Menus;

import Client.Controller.ProductController;

public class Product extends Menu {
    private String id;
    public Product(Menu superMenu , String productId) {
        super(superMenu, "Product");
        id = productId;
        addSubMenu(addDigest());
        addSubMenu(addAttribute());
        addSubMenu(addComments());
        addSubMenu(addCompare());
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Menu addDigest(){
        return new Menu(this,"digest") {
            @Override
            public void show() {
                //todo print product info
            }

            @Override
            public void execute() {
                this.addSubMenu(new Menu(this , "add to cart") {
                    @Override
                    public void show() {
                        super.show();
                    }

                    @Override
                    public void execute() {
                        //todo Go to login page if user is not logged in(the function returns false):
                        ProductController.addToCart(id);
                    }
                });

            }
        };
    }
    public Menu addAttribute(){
        return new Menu(this,"attributes") {
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
    public Menu addComments(){
        return new Menu(this,"comments") {
            @Override
            public void show() {
                //TODO print comments
            }

            @Override
            public void execute() {
                this.addSubMenu(new Menu(this , "add comment") {
                    @Override
                    public void show() {
                        //TODO print sth for user to enter comment
                    }

                    @Override
                    public void execute() {
                        //TODO get title and content:
                        String title = "";
                        String content = "";
                        ProductController.addComment(title , content);
                    }
                });
            }
        };
    }
    public Menu addCompare(){
        return new Menu(this,"compare") {
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
