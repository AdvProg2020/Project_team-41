package Client.View.Menus.UserSectionMenus;

import Client.Controller.UserSectionController.ManagerController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Person.Person;
import Client.Models.Product;
import Client.View.Menus.Menu;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.HashMap;


public class ManagerSection extends UserSection {
    public BorderPane managerSectionBoarderPane;
    private ManagerController managerController;

    @Override
    public void show() {
        System.out.println("create discount code");
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
        if(command.equalsIgnoreCase("create discount code"))
            createDiscountCode();
        System.out.println("invalid command");
        this.show();
        this.execute();

    }

    public ManagerSection(Menu superMenu) {
        super(superMenu, "ManagerSection");
        addSubMenu(this.addManageUsers());
        addSubMenu(this.addManageAllProducts());
        addSubMenu(this.addManageCategories());
        addSubMenu(this.addManageRequests());
        addSubMenu(this.addViewDiscountCodes());
    }


    private Menu addManageCategories(){
        return new Menu(this,"ManageCategories") {
            private void edit(String category){
                System.out.println("what do you want to change?(name,special features)");
                command = scanner.nextLine();
                if(command.equalsIgnoreCase("name")){
                    try {
                        System.out.println("ok. enter category's new name");
                        ManagerController.getInstance().editCategoryName(category,  scanner.nextLine());
                        System.out.println("category name edited successfully");
                    }
                    catch (Exception e){
                        System.out.println("no category found");
                    }
                    this.show();
                    this.execute();
                }
                else if(command.equalsIgnoreCase("special features")){
                    System.out.println("type its special features(type them with a comma between each special feature)");

                    try {
                        ManagerController.getInstance().editCategorySpecialFeatures(category,scanner.nextLine());
                        System.out.println("edited special features successfully");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    this.show();
                    this.execute();


                }
                else {
                    System.out.println("invalid command");
                    this.show();
                    this.execute();
                }
            }
            private void add(String category){
                String specialFeatures;
                System.out.println("type its special features(type them with a comma between each special feature)");
                specialFeatures = scanner.nextLine();
                try{
                    ManagerController.getInstance().addCategory(category,specialFeatures);
                    System.out.println("category " + category + " is created");
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
                this.show();
                this.execute();
            }
            private void remove(String category){
                try{
                    ManagerController.getInstance().removeCategory(category);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
                this.show();
                this.execute();
            }



            @Override
            public void show() {
                super.show();
                try {
                    if(ManagerController.getInstance().showCategories().isEmpty())
                        System.out.println("there is no category to show here");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    for (String category : ManagerController.getInstance().showCategories()) {
                        System.out.println(category);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println();
                System.out.println("commands:");
                System.out.println("edit <category>");
                System.out.println("add <category>");
                System.out.println("remove <category>");
            }

            @Override
            public void execute() {
                super.execute();
                if(command.startsWith("edit")){
                    try {
                        edit(command.split(" ")[1]);
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
                else if(command.startsWith("add")){
                    try {
                        add(command.split(" ")[1]);
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
                else if(command.startsWith("remove")){
                    try {
                        remove(command.split(" ")[1]);
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
        };
    }

    private Menu addManageRequests(){
        return new Menu(this,"ManageRequests") {

            private void details(String requestId){
                try {
                    for (String requestDetail : ManagerController.getInstance().getRequestDetails(requestId)) {
                        System.out.println(requestDetail);
                    }
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                    this.show();
                    this.execute();

            }
            private void accept(String requestId){
                try {
                    ManagerController.getInstance().acceptRequest(requestId);
                    System.out.println("request accepted");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                this.show();
                this.execute();
            }
            private void decline(String requestId){
                try {
                    ManagerController.getInstance().declineRequest(requestId);
                    System.out.println("request declined");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                this.show();
                this.execute();

            }
            private void acceptAll(){
                try {
                    ManagerController.getInstance().acceptAllRequests();
                    System.out.println("all requests accepted");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                this.show();
                this.execute();
            }


            @Override
            public void show() {
                super.show();
                try {
                    if(ManagerController.getInstance().showRequest().isEmpty())
                        System.out.println("there is no request to show here");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    for (String request : ManagerController.getInstance().showRequest()) {
                        System.out.println(request);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println();
                System.out.println("commands:");
                System.out.println("accept all");
                System.out.println("details <requestId>");
                System.out.println("accept <requestId>");
                System.out.println("decline <requestId>");
            }

            @Override
            public void execute() {
                super.execute();
                if(command.equalsIgnoreCase("accept all")){
                    acceptAll();
                }
                else if(command.startsWith("details")){
                    try {
                        details(command.split(" ")[1]);
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
                else if(command.startsWith("accept")){
                    try {
                        accept(command.split(" ")[1]);
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
                else if(command.startsWith("decline")){
                    try {
                        decline(command.split(" ")[1]);
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
        };
    }

    private Menu addViewDiscountCodes(){
        return new Menu(this,"ViewDiscountCodes") {
            private void viewDiscountCode(String code){
                try {
                    for (String discountCode : ManagerController.getInstance().viewDiscountCode(code)) {
                        System.out.println(discountCode);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                this.show();
                this.execute();
            }
            private void editDiscountCode(String code) {
                HashMap<String, String> edits = new HashMap<>();
                System.out.println("what do you want to change?(type end to finish editing)");
                System.out.println("you can edit (start date,end date,discount percentage,maximum discount,discount repeats for each user,people who can use it)");
                System.out.println("to edit dates, edited field should look like this:(d/m/y,h:m:s)");
                System.out.println("edit like this:(field,edited field)");
                String input;
                while (!(input = scanner.nextLine()).equals("end")) {
                    String[] splitInput = input.split(",",2);
                    try {
                        edits.put(splitInput[0], splitInput[1]);
                    } catch (Exception e) {
                        System.out.println("enter like i said. please...");
                    }
                }
                try {
                    ManagerController.getInstance().editDiscountCode(code, edits);
                    System.out.println("edited fields successfully");
                    this.show();
                    this.execute();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    this.show();
                    this.execute();
                }
            }
            private void removeDiscountCode(String code){
                try {
                    ManagerController.getInstance().removeDiscountCode(code);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    this.show();
                    this.execute();
                }
                System.out.println("removed discount code successfully");
                this.show();
                this.execute();
            }

            @Override
            public void show() {
                super.show();
                try {
                    if(ManagerController.getInstance().viewAllDiscountCodes().isEmpty())
                        System.out.println("there is no discount code to show here");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    for (String discountCode : ManagerController.getInstance().viewAllDiscountCodes()) {
                        System.out.println(discountCode);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println();
                System.out.println("commands:");
                System.out.println("view <code>");
                System.out.println("edit <code>");
                System.out.println("remove <code>");
            }

            @Override
            public void execute() {
                super.execute();
                if(command.startsWith("view")){
                    try {
                        viewDiscountCode(command.split(" ")[1]);
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
                if(command.startsWith("edit")){
                    try {
                        editDiscountCode(command.split(" ")[1]);
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
                if(command.startsWith("remove")){
                    try {
                        removeDiscountCode(command.split(" ")[1]);
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
        };
    }

    private void createDiscountCode(){
        ArrayList<String> codeInformation = new ArrayList<>();
        System.out.println("enter code");
        codeInformation.add(scanner.nextLine());
        System.out.println("enter exactStartDate(day/month/year)");
        codeInformation.add(scanner.nextLine());
        System.out.println("enter exactStartTime(hour:minute:second)");
        codeInformation.add(scanner.nextLine());
        System.out.println("enter exactEndDate(day/month/year)");
        codeInformation.add(scanner.nextLine());
        System.out.println("enter exactEndTime(hour:minute:second)");
        codeInformation.add(scanner.nextLine());
        System.out.println("enter discount percentage");
        codeInformation.add(scanner.nextLine());
        System.out.println("enter maximum discount");
        codeInformation.add(scanner.nextLine());
        System.out.println("enter numberOfRepeatsPerEachUser");
        codeInformation.add(scanner.nextLine());
        System.out.println("enter who can use this code(type allUsers to include every person or type username's separated by commas(example: mahdi,matin)");
        codeInformation.add(scanner.nextLine());
        try{
            ManagerController.getInstance().createDiscountCode(codeInformation);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            this.show();
            this.execute();
        }
        System.out.println("created discount code successfully");
        this.show();
         this.execute();

    }

    private Menu addManageAllProducts(){
        return new Menu(this,"ManageAllProducts") {
            private void remove(String productId){
                try {
                    ManagerController.getInstance().removeProduct(productId);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                this.show();
                this.execute();
            }

            @Override
            public void show() {
                super.show();
                    System.out.println("<<name - product Id>>");
                try {
                    for (Product product : ManagerController.getInstance().getAllProducts()) {
                        System.out.println(product.getName() + " - " + product.getProductId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println();
                System.out.println("commands : ");
                System.out.println("remove <productId>");
            }

            @Override
            public void execute() {
                super.execute();
                if(command.startsWith("remove")){
                    try {
                        remove(command.split(" ")[1]);
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

        };
    }

    private Menu addManageUsers(){
        return new Menu(this,"manageUsers") {

            private void view(String username){
                Person user = null;
                try {
                    user = ManagerController.getInstance().getUserByUsername(username);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    this.show();
                    this.execute();
                }
                try {
                    for (String field : UserSectionController.getPersonalInfo(user)) {
                        System.out.println(field);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.show();
                this.execute();

            }
            private void deleteUser(String username){
                try{
                    ManagerController.getInstance().deleteUser(username);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                    this.show();
                    this.execute();
                }
                System.out.println("deleted user " + username + " successfully");
                this.show();
                this.execute();
            }
            private void createManagerProfile(){
                ArrayList<String> userInfo = new ArrayList<>();
                System.out.println("UserName:");
                userInfo.add(scanner.nextLine());
                System.out.println("Password:");
                userInfo.add(scanner.nextLine());
                System.out.println("What is your first name?");
                userInfo.add(scanner.nextLine());
                System.out.println("What is your last name?");
                userInfo.add(scanner.nextLine());
                System.out.println("What is your email?");
                userInfo.add(scanner.nextLine());
                System.out.println("What is your phone number?");
                userInfo.add(scanner.nextLine());
                System.out.println("enter the money");
                userInfo.add(scanner.nextLine());
                try {
                    ManagerController.getInstance().createManagerProfile(userInfo);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    this.show();
                    this.execute();
                }
                System.out.println("Successfully created Manager");
                this.show();
                this.execute();

            }


            @Override
            public void execute() {
                super.execute();
                if(command.equals("create manager profile")){
                    createManagerProfile();
                }
                else if(command.startsWith("view")){
                    try {
                        view(command.split(" ")[1]);
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
                else if(command.startsWith("delete")){
                    try {
                        deleteUser(command.split(" ")[1]);
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
            @Override
            public void show() {
                super.show();
                System.out.println("users : ");
                try {
                    for (String user : ManagerController.getInstance().getAllUsers()) {
                        System.out.println(user);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println();
                System.out.println("commands : ");
                System.out.println("view <username>");
                System.out.println("delete <username>");
                System.out.println("create manager profile");

            }

        };
    }

}
