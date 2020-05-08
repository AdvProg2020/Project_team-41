package Client.View.Menus.UserSectionMenus;

import Client.Controller.UserSectionController.BuyerAccountController.BuyerController;
import Client.Controller.UserSectionController.ManagerController;
import Client.Controller.UserSectionController.SellerController;
import Client.Models.Category;
import Client.Models.Person.Seller;
import Client.Models.Product;
import Client.View.Menus.Menu;

import java.util.ArrayList;
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
                super.show();
                System.out.println("enter new product's details");
            }

            @Override
            public void execute() {
                ArrayList<String> productDetails = new ArrayList<>();
                System.out.println("enter name");
                productDetails.add(scanner.nextLine());
                System.out.println("now enter company name");
                productDetails.add(scanner.nextLine());
                System.out.println("ok... now enter the price");
                productDetails.add(scanner.nextLine());
                SellerController.getInstance().addProduct(productDetails);
                System.out.println("ok, the manager has lots to do :-)");
                super.show();
                super.execute();
            }
        };
    }
    public Menu addViewOffs(){
        return new Menu(this,"ViewOffs") {


            @Override
            public void show() {
                SellerController.getInstance().getOffs();
                System.out.println("view [offId]");
                System.out.println("edit [offId]");
                System.out.println("add off");
                super.show();
            }

            @Override
            public void execute() {
                super.execute();
                if(command.startsWith("view")){
                    viewOff(command.split(" ")[1]);
                }
                else if(command.startsWith("edit")){
                    editOff(command.split(" ")[1]);
                }
                else if (command.equals("add off")){
                    addOff();
                }
                else
                    System.out.println("invalid command");



            }
            private void viewOff(String offId){
                SellerController.getInstance().getOff(Integer.parseInt(offId));
            }
            private void editOff(String offId){
                HashMap<String, String> edits = new HashMap<>();
                System.out.println("enter edits then type end");
                while (!scanner.hasNext("end")) {
                    edits.put(scanner.next(), scanner.next());
                }
                SellerController.getInstance().editOff(edits);
            }
            private void addOff(){
                ArrayList<String> offDetails = new ArrayList<>();
                System.out.println("enter offId");
                offDetails.add(scanner.nextLine());
                System.out.println("now enter start date");
                offDetails.add(scanner.nextLine());
                System.out.println("enter end date");
                offDetails.add(scanner.nextLine());
                System.out.println("enter amount of discount");
                offDetails.add(scanner.nextLine());
                System.out.println("enter product Id's you want to be included(type end to end this");
                while (!scanner.hasNext("end")) {
                    offDetails.add(scanner.next());
                }
                SellerController.getInstance().addProduct(offDetails);
                System.out.println("ok, the manager has lots to do :-)");

            }
        };
    }
    public Menu addShowCategories(){
        return new Menu(this,"ShowCategories") {
            @Override
            public void show() {
                for (Category category : SellerController.getInstance().getCategories())
                    System.out.println(category.getName());
            }

        };
    }
    public Menu addRemoveProduct(){
        return new Menu(this,"RemoveProduct") {
            @Override
            public void show() {
                System.out.println("type the product Id you want to be removed");
            }

            @Override
            public void execute() {
                SellerController.getInstance().removeProduct(Integer.parseInt(scanner.nextLine()));
            }
        };
    }
    public Menu addViewBalance(){
        return new Menu(this,"ViewBalance") {
            @Override
            public void show() {
                System.out.println(SellerController.getLoggedInPerson().getCredit());
            }

        };
    }

}

//todo edit edits. it may cause problem in graphics. you can add a class for all of them
