package Client.View.Menus.UserSectionMenus;

import Client.Controller.UserSectionController.SellerController;
import Client.Models.Category;
import Client.Models.Off;
import Client.Models.Product;
import Client.View.Menus.Menu;

import java.util.ArrayList;
import java.util.HashMap;

public class SellerSection extends UserSection {

    public SellerSection(Menu superMenu) {
        super(superMenu, "SellerSection");
        addSubMenu(addViewSalesHistory());
        addSubMenu(addManageProduct());
        addSubMenu(addViewOffs());
        addSubMenu(addShowCategories());
        addSubMenu(addViewBalance());
    }

    @Override
    public void show() {
        super.show();

    }

    @Override
    public void commands() {
        super.commands();
        System.out.println("view company info");
        System.out.println("add product");
        System.out.println("remove product");

    }

    @Override
    public void execute() {
        super.execute();
        if(command.equalsIgnoreCase("view company info"))
            viewCompanyInfo();
        else if(command.equalsIgnoreCase("add product"))
            addProduct();
        else if(command.equalsIgnoreCase("remove product"))
            removeProduct();
        System.out.println("invalid command");
        this.show();
        this.execute();
    }


    private void viewCompanyInfo(){
        System.out.println(SellerController.getInstance().getFactoryName());
        System.out.println("\n");
        this.show();
        this.execute();
        //todo make factory class and put other things in it
    }
    private void addProduct(){
        ArrayList<String> productDetails = new ArrayList<>();
        System.out.println("enter name");
        productDetails.add(scanner.nextLine());
        System.out.println("How many do you have?");
        productDetails.add(scanner.nextLine());
        System.out.println("now enter company name");
        productDetails.add(scanner.nextLine());
        System.out.println("ok... now enter the price");
        productDetails.add(scanner.nextLine());
        System.out.println("enter the category name");
        productDetails.add(scanner.nextLine());
        System.out.println("enter the description");
        productDetails.add(scanner.nextLine());
        System.out.println("enter it's special features(e.g. (categorySpecialFeature1-productSpecialFeature1,categorySpecialFeature2-productSpecialFeature2))");
        productDetails.add(scanner.nextLine());

        try {
            SellerController.getInstance().addProduct(productDetails);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.show();
            this.execute();
        }
        System.out.println("ok, the manager has lots to do :-)");
        this.show();
        this.execute();
    }
    //todo check if viewSalesHistory really needs to be a menu
    public Menu addViewSalesHistory(){
        return new Menu(this,"ViewSalesHistory") {
            @Override
            public void show() {
                try {
                    System.out.println(SellerController.getInstance().getSalesHistory());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                super.show();
                super.execute();
            }

        };
    }
    public Menu addManageProduct(){
        return new Menu(this,"ManageProducts") {
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
                System.out.println("view product <product id>");
                System.out.println("view product buyers <product id>");
                System.out.println("edit product <product id>");
            }

            @Override
            public void execute() {
                super.execute();

                if(command.startsWith("viewProduct")){
                    viewProduct(command.split(" ")[1]);
                }
                else if(command.startsWith("viewProductBuyers")){
                    viewProductBuyers(command.split(" ")[1]);
                }
                else if(command.startsWith("editProduct")){
                    editProduct(command.split(" ")[1]);
                }
                else{
                    System.out.println("invalid command");
                    this.show();
                    this.execute();
                }

            }
            public void viewProduct(String id){
                Product product = null;
                try {
                    product = SellerController.getInstance().getProduct(id);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    this.show();
                    this.execute();
                }
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
            public void viewProductBuyers(String id) {
                try {
                    for (String buyer : SellerController.getInstance().getProductBuyers(id)) {
                        System.out.println("buyer : " + buyer);
                    }
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
                this.show();
                this.execute();
            }
            public void editProduct(String id){
                HashMap<String,String> edits = new HashMap<>();
                System.out.println("what do you want to change?(type end to finish editing)(you can edit (seller,price,companyName,description,name,specialFeature(e.g. (categorySpecialFeature1-productSpecialFeature1,categorySpecialFeature2-productSpecialFeature2))))");
                while (!scanner.hasNext("end")) {
                    edits.put(scanner.next(), scanner.next());
                }
                try {
                    SellerController.getInstance().editProduct(id,edits);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                this.show();
                this.execute();
            }
        };
    }

    public Menu addViewOffs(){
        return new Menu(this,"ViewOffs") {


            @Override
            public void show() {

                System.out.print("+--------------+\n");
                System.out.print("|    Off id    |\n");
                System.out.print("+--------------+\n");

                for (Off off : SellerController.getInstance().getOffs()) {
                    System.out.format("| %-12s |\n", off.getOffId());
                }

                System.out.print("+--------------+\n");
                super.show();
            }

            @Override
            public void commands() {
                super.commands();
                System.out.println("view [offId]");
                System.out.println("edit [offId]");
                System.out.println("addOff");

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
                else if (command.equalsIgnoreCase("addOff")){
                    addOff();
                }
                else
                    System.out.println("invalid command");
                this.show();
                this.execute();



            }
            private void viewOff(String offId){
                try {
                    System.out.println(SellerController.getInstance().getOff(offId));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                this.show();
                this.execute();
            }
            private void editOff(String offId){

                HashMap<String, String> edits = new HashMap<>();
                System.out.println("what do you want to change?(type end to finish editing)");
                System.out.println("you can edit (start date,end date,amount of discount,products)");
                System.out.println("to change products, type product id's separated by comma");
                System.out.println("edit like this:(field,edited field)");
                String input;
                while (!(input = scanner.nextLine()).equals("end")) {
                    String[] splitInput = input.split(",");
                    edits.put(splitInput[0], splitInput[1]);
                }
                //todo optimize editing to handle used products in request
                try {
                    SellerController.getInstance().editOff(offId,edits);
                    System.out.println("edited fields successfully");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                this.show();
                this.execute();
            }
            private void showProducts(){
                if(SellerController.getInstance().getProducts().isEmpty())
                    System.out.println("there is no product to show here");
                for (Product product : SellerController.getInstance().getProducts()) {
                    System.out.println("product name: "+product.getName());
                    System.out.println("product id: "+product.getProductId());
                }
            }
            private void addOff(){
                ArrayList<String> offDetails = new ArrayList<>();
                System.out.println("enter exactStartDate(day/month/year)");
                offDetails.add(scanner.nextLine());
                System.out.println("enter exactStartTime(hour:minute:second)");
                offDetails.add(scanner.nextLine());
                System.out.println("enter exactEndDate(day/month/year)");
                offDetails.add(scanner.nextLine());
                System.out.println("enter exactEndTime(hour:minute:second)");
                offDetails.add(scanner.nextLine());
                System.out.println("enter amount of discount");
                offDetails.add(scanner.nextLine());
                System.out.println("enter product Id's you want to be included(type end to end this)(you can view products you can include by entering showProducts)");
                String input;

                while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
                    if(input.equalsIgnoreCase("showProducts")){
                        showProducts();
                    }
                    else {
                        offDetails.add(input);
                    }
                }
                try {
                    SellerController.getInstance().addOff(offDetails);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    this.show();
                    this.execute();
                }
                System.out.println("ok, the manager has lots to do :-)");
                this.show();
                this.execute();

            }
        };
    }
    public Menu addShowCategories(){
        return new Menu(this,"ShowCategories") {
            @Override
            public void show() {
                for (Category category : SellerController.getInstance().getCategories())
                    System.out.println(category.getName());
                super.show();
            }

        };
    }
    private void removeProduct(){
        System.out.println("type the product Id you want to be removed");
        try {
            SellerController.getInstance().removeProduct(scanner.nextLine());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            this.show();
            this.execute();
        }
        super.show();
        super.execute();
    }
    public Menu addViewBalance(){
        return new Menu(this,"ViewBalance") {
            @Override
            public void show() {
                System.out.println(SellerController.getLoggedInPerson().getCredit());
                super.show();
            }

        };
    }

}

//todo edit edits. it may cause problem in graphics. you can add a class for all of them
