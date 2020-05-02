package Client.View.Menus.UserSectionMenus;

import Client.Controller.UserSectionController.BuyerAccountController.BuyerController;
import Client.Controller.UserSectionController.ManagerController;
import Client.Controller.UserSectionController.SellerController;
import Client.Models.Person.Seller;
import Client.Models.Product;
import Client.View.Menus.Menu;

import java.util.HashMap;

public class SellerSection extends UserSection {

    public SellerSection(Menu superMenu) {
        super(superMenu, "SellerSection");
        addSubMenu(addViewCompanyInfo());
        addSubMenu(addViewSalesHistory());
        addSubMenu(addMangeProduct());
        addSubMenu(addAddProduct());
        addSubMenu(addViewOffs());
        addSubMenu(addShowCategories());
        addSubMenu(addRemoveProduct());
        addSubMenu(addViewBalance());
    }

    public Menu addViewCompanyInfo(){
        return new Menu(this,"ViewCompanyInfo") {
            @Override
            public void show() {
                System.out.println(SellerController.getInstance().getFactoryName());
                //todo make factory class and put other things in it
            }

        };
    }
    public Menu addViewSalesHistory(){
        return new Menu(this,"ViewSalesHistory") {
            @Override
            public void show() {
                super.show();
                System.out.println(SellerController.getInstance().getSalesHistory());
            }

        };
    }
    public Menu addMangeProduct(){
        return new Menu(this,"MangeProduct") {
            @Override
            public void show() {
                super.show();
                if(SellerController.getInstance().getProducts().isEmpty())
                    System.out.println("there is no product to show here");
                for (Product product : SellerController.getInstance().getProducts()) {
                    System.out.println("product name: "+product.getName());
                    System.out.println("product id: "+product.getProductId());
                }
                System.out.println();
                System.out.println("commands:");
                System.out.println("viewProduct <product id>");
                System.out.println("viewProductBuyers <product id>");
                System.out.println("editProduct <product id>");
            }

            @Override
            public void execute() {
                super.execute();
                if(command.startsWith("viewProduct")){
                    viewProduct(Integer.parseInt(command.split(" ")[1]));
                }
                else if(command.startsWith("viewProductBuyers")){
                    viewProductBuyers(Integer.parseInt(command.split(" ")[1]));
                }
                else if(command.startsWith("editProduct")){
                    editProduct(Integer.parseInt(command.split(" ")[1]));
                }
                else{
                    System.out.println("invalid command");
                    this.show();
                    this.execute();
                }

            }
            public void viewProduct(int id){
                Product product = SellerController.getInstance().getProduct(id);
                System.out.println("id : " + product.getProductId());
                System.out.println("name : " + product.getName());
                System.out.println("price : " + product.getPrice());
                System.out.println("category : " + product.getCategory().getName());
                System.out.println("company name : " + product.getCompanyName());
                System.out.println("description : " + product.getDescription());
                System.out.println("views : " + product.getViews());
                System.out.println("special features : ");
                for (String key : product.getSpecialFeatures().keySet()) {
                    System.out.println(key + product.getSpecialFeatures().get(key));
                }
                this.show();
                this.execute();

            }
            public void viewProductBuyers(int id){
                for (String buyer : SellerController.getInstance().getProductBuyers(id)) {
                    System.out.println("buyer : " + buyer);
                }
                this.show();
                this.execute();
            }
            public void editProduct(int id){
                HashMap<String,String> edits = new HashMap<>();
                System.out.println("what do you want to change?(type end to finish editing");
                while (!scanner.hasNext("end")) {
                    edits.put(scanner.next(), scanner.next());
                }
                SellerController.getInstance().editProduct(id,edits);
                this.show();
                this.execute();
            }
        };
    }
    public Menu addAddProduct(){
        return new Menu(this,"AddProduct") {
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
    public Menu addViewOffs(){
        return new Menu(this,"ViewOffs") {
            @Override
            public void show() {
                //todo
            }

            @Override
            public void execute() {
                //todo
            }
            public void viewOff(int id){

            }
            public void editOff(int id){

            }
            public void addOff(){

            }
        };
    }
    public Menu addShowCategories(){
        return new Menu(this,"ShowCategories") {
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
    public Menu addRemoveProduct(){
        return new Menu(this,"RemoveProduct") {
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
    public Menu addViewBalance(){
        return new Menu(this,"ViewBalance") {
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

}
