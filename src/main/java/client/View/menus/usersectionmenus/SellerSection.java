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
        addSubMenu(addManageProduct());
        addSubMenu(addViewOffs());
        addSubMenu(addShowCategories());
        addSubMenu(addViewBalance());
    }

    @Override
    public void commands() {
        super.commands();
        System.out.println("view sales history");
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
        else if(command.equalsIgnoreCase("view sales history"))
            viewSalesHistory();
        else if(command.equalsIgnoreCase("remove product"))
            removeProduct();
        System.out.println("invalid command");
        this.show();
        this.execute();
    }


    private void viewCompanyInfo(){
        try {
            System.out.println(SellerController.getInstance().getFactoryName());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        String categoryName = scanner.nextLine();
        productDetails.add(categoryName);
        System.out.println("enter the description");
        productDetails.add(scanner.nextLine());
        System.out.println("enter it's special features(e.g. (categorySpecialFeature1-productSpecialFeature1,categorySpecialFeature2-productSpecialFeature2))(you can view category's special feature by entering viewSpecialFeatures)");
        String input = scanner.nextLine();
        while (input.equalsIgnoreCase("viewSpecialFeatures")) {
            try {
                for (String categorySpecialFeature : SellerController.getInstance().getCategorySpecialFeatures(categoryName)) {
                    System.out.println(categorySpecialFeature);
                }
                System.out.println("ok. now enter it's special features(like i said)");
                input = scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                this.show();
                this.execute();
            }
        }
        productDetails.add(input);

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
    private void viewSalesHistory(){
        try {
            System.out.println(SellerController.getInstance().getSalesHistory());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.show();
        this.execute();
    }
    public Menu addManageProduct(){
        return new Menu(this,"ManageProducts") {
            @Override
            public void show() {
                super.show();
                try {
                    if(SellerController.getInstance().getProducts().isEmpty())
                        System.out.println("there is no product to show here");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    for (Product product : SellerController.getInstance().getProducts()) {
                        System.out.println("product name: "+product.getName());
                        System.out.println("product id: "+product.getProductId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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

                if(command.startsWith("viewProductBuyers")){
                    try {
                        viewProductBuyers(command.split(" ")[1]);
                    } catch (Exception e) {
                        if(e instanceof ArrayIndexOutOfBoundsException) {
                            System.out.println("invalid command");
                        }
                        else{
                            System.out.println(e.getMessage());
                        }
                        this.show();
                        this.execute();
                    }
                }
                else if(command.startsWith("viewProduct")){
                    try {
                        viewProduct(command.split(" ")[1]);
                    } catch (Exception e) {
                        if(e instanceof ArrayIndexOutOfBoundsException) {
                            System.out.println("invalid command");
                        }
                        else{
                            System.out.println(e.getMessage());
                        }
                        this.show();
                        this.execute();
                    }
                }
                else if(command.startsWith("editProduct")){
                    try {
                        editProduct(command.split(" ")[1]);
                    } catch (Exception e) {
                        if(e instanceof ArrayIndexOutOfBoundsException) {
                            System.out.println("invalid command");
                        }
                        else{
                            System.out.println(e.getMessage());
                        }
                        this.show();
                        this.execute();
                    }
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
                assert product != null;
                System.out.println("id : " + product.getProductId());
                System.out.println("name : " + product.getName());
                System.out.println("price : " + product.getPrice());
                System.out.println("category : " + product.getCategory().getName());
                System.out.println("company name : " + product.getCompanyName());
                System.out.println("description : " + product.getDescription());
                System.out.println("views : " + product.getViews());
                System.out.println("special features : ");
                for (String key : product.getSpecialFeatures().keySet()) {
                    System.out.println(key + " : " + product.getSpecialFeatures().get(key));
                }
                this.show();
                this.execute();

            }
            public void viewProductBuyers(String id) {
                try {
                    ArrayList<String> productBuyers = SellerController.getInstance().getProductBuyers(id);
                    if(productBuyers.isEmpty()) {
                        System.out.println("no one has bought this product yet");
                        this.show();
                        this.execute();
                    }
                    for (String buyer : productBuyers) {
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
                System.out.println("what do you want to change?");
                System.out.println("type end to finish editing");
                System.out.println("edit like this: (field editedField)");
                System.out.println("you can edit (price,companyName,description,name,specialFeature)");
                System.out.println("special feature edited field looks like this: (categorySpecialFeature1-productSpecialFeature1,categorySpecialFeature2-productSpecialFeature2)");
                String input;
                String[] inputs;
                while (!(input = scanner.nextLine()).equals("end")) {
                    inputs = input.split(" ");
                    if(inputs.length != 2) {
                        System.out.println("wrong input");
                        continue;
                    }
                    edits.put(inputs[0], inputs[1]);
                }
                if(edits.isEmpty()){
                    System.out.println("you didn't edit anything!");
                    this.show();
                    this.execute();
                }
                try {
                    SellerController.getInstance().editProduct(id,edits);
                    System.out.println("successfully edited product");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                this.show();
                this.execute();
            }
        };
    }
    private void removeProduct(){
        System.out.println("type the product Id you want to be removed");
        try {
            SellerController.getInstance().removeProduct(scanner.nextLine());
            System.out.println("successfully removed product");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        this.show();
        this.execute();
    }

    public Menu addViewOffs(){
        return new Menu(this,"ViewOffs") {


            @Override
            public void show() {

                System.out.print("+--------------+\n");
                System.out.print("|    Off id    |\n");
                System.out.print("+--------------+\n");

                try {
                    for (Off off : SellerController.getInstance().getOffs()) {
                        System.out.format("| %-12s |\n", off.getOffId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
                    try {
                        viewOff(command.split(" ")[1]);
                    } catch (Exception e) {
                        if(e instanceof ArrayIndexOutOfBoundsException) {
                            System.out.println("invalid command");
                        }
                        else{
                            System.out.println(e.getMessage());
                        }
                        this.show();
                        this.execute();
                    }
                }
                else if(command.startsWith("edit")){
                    try {
                        editOff(command.split(" ")[1]);
                    } catch (Exception e) {
                        if(e instanceof ArrayIndexOutOfBoundsException) {
                            System.out.println("invalid command");
                        }
                        else{
                            System.out.println(e.getMessage());
                        }
                        this.show();
                        this.execute();
                    }
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
                    try {
                        edits.put(splitInput[0], splitInput[1]);
                    } catch (Exception e) {
                        if(e instanceof ArrayIndexOutOfBoundsException) {
                            System.out.println("enter like i said");
                        }
                        else{
                            System.out.println(e.getMessage());
                        }
                    }
                }
                try {
                    SellerController.getInstance().editOff(offId,edits);
                    System.out.println("ok. manager has lots to do:)");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                this.show();
                this.execute();
            }
            private void showProducts(){
                try {
                    if(SellerController.getInstance().getProducts().isEmpty())
                        System.out.println("there is no product to show here");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    for (Product product : SellerController.getInstance().getProducts()) {
                        System.out.println("product name: "+product.getName());
                        System.out.println("product id: "+product.getProductId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
                try {
                    for (Category category : SellerController.getInstance().getCategories())
                        System.out.println(category.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                super.show();
            }

            @Override
            public void execute() {
                super.execute();
                System.out.println("invalid command");
                this.show();
                this.execute();

            }
        };
    }
    public Menu addViewBalance(){
        return new Menu(this,"ViewBalance") {
            @Override
            public void show() {
                System.out.println(SellerController.getLoggedInPerson().getCredit());
                super.show();
            }

            @Override
            public void execute() {
                super.execute();
                System.out.println("invalid command");
                this.show();
                this.execute();
            }
        };
    }

}