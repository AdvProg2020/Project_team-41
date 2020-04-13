package Client.View.Menus;

public class Product extends Menu {
    private int id;
    public Product(Menu superMenu) {
        super(superMenu, "Product");
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public Menu addDigest(){
        return new Menu(this,"Digest") {
            @Override
            public void show() {
                //todo
            }

            @Override
            public void execute() {
                //todo
            }
        };
    }
    public Menu addAttribute(){
        return new Menu(this,"Attributes") {
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
        return new Menu(this,"Comments") {
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
    public Menu addCompare(){
        return new Menu(this,"Compare") {
            private int id;
            @Override
            public void show() {
                super.show();
            }

            @Override
            public void execute() {
                super.execute();
            }


            public void setId(int id) {
                this.id = id;
            }
        };
    }
}
