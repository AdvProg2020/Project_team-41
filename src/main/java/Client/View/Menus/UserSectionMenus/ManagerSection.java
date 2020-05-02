package Client.View.Menus.UserSectionMenus;

import Client.Controller.UserSectionController.ManagerController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Person.Person;
import Client.View.Menus.Menu;

import java.util.ArrayList;
import java.util.HashMap;

public class ManagerSection extends UserSection {
    private ManagerController managerController;

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
        System.out.println("invalid command");
        this.show();
        this.execute();

    }

    public ManagerSection(Menu superMenu) {
        super(superMenu, "ManagerSection");
        addSubMenu(this.addManageUsers());
        addSubMenu(this.addCreateDiscountCode());
        addSubMenu(this.addManageAllProducts());
        addSubMenu(this.addManageCategories());
        addSubMenu(this.addManageRequests());
        addSubMenu(this.addViewDiscountCodes());
    }


    private Menu addManageCategories(){
        return new Menu(this,"ManageCategories") {
            private void edit(String Category){
                System.out.println("change what you want(type field and edited field)");
                try {
                    ManagerController.getInstance().editCategory(Category, scanner.next(), scanner.next());
                }
                catch (Exception e){
                    System.out.println("no category found");
                }
                this.show();
                this.execute();
            }
            private void add(String category){
                String specialFeatures;
                System.out.println("type its special features(type them with a comma between each special feature)");
                //todo separate two types of special features
                specialFeatures = scanner.nextLine();
                try{
                    ManagerController.getInstance().addCategory(category,specialFeatures);
                    System.out.println("category " + category + " is created");
                }
                catch (Exception e){
                    System.out.println("category could not be created");
                }
                this.show();
                this.execute();
            }
            private void remove(String category){
                try{
                    ManagerController.getInstance().removeCategory(category);
                }
                catch (Exception e){
                    System.out.println("no category exists with this name");
                }
                this.show();
                this.execute();
            }



            @Override
            public void show() {
                super.show();
                if(ManagerController.getInstance().showCategories().isEmpty())
                    System.out.println("there is no category to show here");
                for (String category : ManagerController.getInstance().showCategories()) {
                    System.out.println(category);
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
                    edit(command.split(" ")[1]);
                }
                else if(command.startsWith("add")){
                    add(command.split(" ")[1]);
                }
                else if(command.startsWith("remove")){
                    remove(command.split(" ")[1]);
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
                for (String requestDetail : ManagerController.getInstance().getRequestDetails(requestId)) {
                    System.out.println(requestDetail);
                }
            }
            private void accept(String requestId){
                ManagerController.getInstance().acceptRequest(requestId);
            }
            private void decline(String requestId){
                ManagerController.getInstance().declineRequest(requestId);
            }


            @Override
            public void show() {
                super.show();
                if(ManagerController.getInstance().showRequest().isEmpty())
                    System.out.println("there is no request to show here");
                for (String request : ManagerController.getInstance().showRequest()) {
                    System.out.println(request);
                }
                System.out.println();
                System.out.println("commands:");
                System.out.println("details <requestId>");
                System.out.println("accept <requestId>");
                System.out.println("decline <requestId>");
            }

            @Override
            public void execute() {
                super.execute();
                if(command.startsWith("details")){
                    details(command.split(" ")[1]);
                }
                else if(command.startsWith("accept")){
                    accept(command.split(" ")[1]);
                }
                else if(command.startsWith("decline")){
                    decline(command.split(" ")[1]);
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
                for (String discountCode : ManagerController.getInstance().viewDiscountCode(code)) {
                    System.out.println(discountCode);
                }
                this.show();
                this.execute();
            }
            private void editDiscountCode(String code) {
                HashMap<String, String> edits = new HashMap<>();
                System.out.println("what do you want to change?(type end to finish editing");
                while (!scanner.hasNext("end")) {
                    edits.put(scanner.next(), scanner.next());
                }
                ManagerController.getInstance().editDiscountCode(code, edits);
                this.show();
                this.execute();
            }
            private void removeDiscountCode(String code){
                ManagerController.getInstance().removeDiscountCode(code);
                this.show();
                this.execute();
            }

            @Override
            public void show() {
                super.show();
                if(ManagerController.getInstance().viewAllDiscountCodes().isEmpty())
                    System.out.println("there is no discount code to show here");
                for (String discountCode : ManagerController.getInstance().viewAllDiscountCodes()) {
                    System.out.println(discountCode);
                }
                System.out.println();
                System.out.println("commands:");
                System.out.println("view discount code <code>");
                System.out.println("edit discount code <code>");
                System.out.println("remove discount code <code>");
            }

            @Override
            public void execute() {
                super.execute();
                if(command.startsWith("view discount code")){
                    viewDiscountCode(command.split(" ")[3]);
                }
                if(command.startsWith("edit discount code")){
                    editDiscountCode(command.split(" ")[3]);
                }
                if(command.startsWith("remove discount code")){
                    removeDiscountCode(command.split(" ")[3]);
                }
                else{
                    System.out.println("invalid command");
                    this.show();
                    this.execute();
                }

            }
        };
    }

    private Menu addCreateDiscountCode(){
        return new Menu(this,"CreateDiscountCode") {
            @Override
            public void show() {
                super.show();
                System.out.println("you must enter these things:");
                System.out.println("code" +
                        "\n" +
                        "exactStartTime" +
                        "\n" +
                        "exactEndTime" +
                        "\n" +
                        "discountAmount(which is made from percentage and maximum discount" +
                        "\n" +
                        "numberOfRepeatsPerEachUser" +
                        "\n" +
                        "who can use this code");
            }

            @Override
            public void execute() {
                ArrayList<String> codeInformation = new ArrayList<>();
                System.out.println("enter code(or back:-))");
                super.execute();
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
                System.out.println("enter who can use this code(type allUsers to include every person or type usernames inside brackets and separated by commas(example: [mahdi,matin]");
                codeInformation.add(scanner.nextLine());
                try{
                    ManagerController.getInstance().createDiscountCode(codeInformation);
                }
                catch (Exception e){
                    System.out.println("invalid username");
                    //todo other errors
                }

            }
        };
    }

    private Menu addManageAllProducts(){
        return new Menu(this,"ManageAllProducts") {
            private void remove(String productId){
                ManagerController.getInstance().removeProduct(productId);
                this.show();
                this.execute();
            }

            @Override
            public void show() {
                super.show();
                //todo show products so manager can remove them easier
                System.out.println("remove <productId>");
            }

            @Override
            public void execute() {
                super.execute();
                if(command.startsWith("remove")){
                    remove(command.split(" ")[1]);
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
                Person user = ManagerController.getInstance().getUserByUsername(username);
                for (String field : UserSectionController.getPersonalInfo(user)) {
                    System.out.println(field);
                }
                this.show();
                this.execute();

            }
            private void deleteUser(String username){
                try{
                    ManagerController.getInstance().deleteUser(username);
                }
                catch (Exception e){
                    System.out.println("no user exists with this username");
                }
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
                System.out.println("How much money do you have?");
                userInfo.add(scanner.nextLine());
                ManagerController.getInstance().createManagerProfile(userInfo);
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
                    view(command.split(" ")[1]);
                }
                else if(command.startsWith("delete user")){
                    deleteUser(command.split(" ")[1]);
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
                System.out.println("view <username>");
                System.out.println("delete user <username>");
                System.out.println("create manager profile");

            }

        };
    }

}
