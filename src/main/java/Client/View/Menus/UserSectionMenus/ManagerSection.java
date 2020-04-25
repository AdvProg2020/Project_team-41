package Client.View.Menus.UserSectionMenus;

import Client.Controller.UserSectionController.ManagerController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Category;
import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Client.View.Menus.Menu;
import Client.View.Menus.RegisterLoginMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ManagerSection extends UserSection {
    private ManagerController managerController;

    public ManagerSection(Menu superMenu) {
        super(superMenu, "ManagerSection");
        addSubMenu(this.addManageUsers());
        addSubMenu(this.addCreateDiscountCode());
        addSubMenu(this.addManageAllProducts());
        addSubMenu(this.addManageCategories());
        addSubMenu(this.addManageRequests());
        addSubMenu(this.addViewDiscountCodes());
    }

    @Override
    public void show() {
        super.show();

    }

    @Override
    public void execute() {
        super.execute();

    }

    private Menu addManageCategories(){
        return new Menu(this,"ManagerCategories") {
            private void edit(String Category){
                System.out.println("change what you want(type field  and edited field");
                ManagerController.getInstance().editCategory(Category,scanner.next(),scanner.next());
            }
            private void add(String category){
                ArrayList<String> categoryInformation = new ArrayList<>();
                System.out.println("ok...  type the name");
                categoryInformation.add(scanner.nextLine());
                System.out.println("now type its special features(type them with a comma between each special feature");
                categoryInformation.add(scanner.nextLine());
                System.out.println("ok. now tell us what product to put in(type the with a comma between each product");
                categoryInformation.add(scanner.nextLine());
                ManagerController.getInstance().addCategory(category,categoryInformation);
            }
            private void remove(String category){
                ManagerController.getInstance().removeCategory(category);
            }


            @Override
            public void show() {
                super.show();
                System.out.println("edit");
                System.out.println("add");
                System.out.println("remove");
            }

            @Override
            public void execute() {
                super.execute();
                if(command.startsWith("edit")){
                    edit(command.split(" ")[1]);
                }
                if(command.startsWith("add")){
                    add(command.split(" ")[1]);
                }
                if(command.startsWith("remove")){
                    remove(command.split(" ")[1]);
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
                for (String request : ManagerController.getInstance().showRequest()) {
                    System.out.println(request);
                }
                System.out.println();
                System.out.println("commands:");
                System.out.println("details");
                System.out.println("accept");
                System.out.println("decline");
            }

            @Override
            public void execute() {
                super.execute();
                if(command.startsWith("details")){
                    details(command.split(" ")[1]);
                }
                if(command.startsWith("accept")){
                    accept(command.split(" ")[1]);
                }
                if(command.startsWith("decline")){
                    decline(command.split(" ")[1]);
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
            }
            private void editDiscountCode(String code) {
                HashMap<String, String> edits = new HashMap<>();
                System.out.println("what do you want to change?");
                while (!scanner.hasNext("back")) {
                    edits.put(scanner.next(), scanner.next());
                }
                ManagerController.getInstance().editDiscountCode(code, edits);
                this.show();
                this.execute();
            }
            private void removeDiscountCode(String code){
                ManagerController.getInstance().removeDiscountCode(code);
            }

            @Override
            public void show() {
                super.show();
                System.out.println("view discount code");
                System.out.println("edit discount code");
                System.out.println("remove discount code");
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
                super.execute();
                ArrayList<String> codeInformation = new ArrayList<>();
                System.out.println("enter code");
                codeInformation.add(scanner.nextLine());
                System.out.println("enter exactStartTime");
                codeInformation.add(scanner.nextLine());
                System.out.println("enter exactEndTime");
                codeInformation.add(scanner.nextLine());
                System.out.println("enter discount percentage");
                codeInformation.add(scanner.nextLine());
                System.out.println("enter maximum discount");
                codeInformation.add(scanner.nextLine());
                System.out.println("enter numberOfRepeatsPerEachUser");
                codeInformation.add(scanner.nextLine());
                System.out.println("enter who can use this code(type allUsers to include every person or type usernames inside brackets and separated by commas(example: [mahdi,matin]");
                codeInformation.add(scanner.nextLine());
                ManagerController.getInstance().createDiscountCode(codeInformation);

            }
        };
    }

    private Menu addManageAllProducts(){
        return new Menu(this,"ManageAllProducts") {
            private void remove(String productId){
                ManagerController.getInstance().removeProduct(productId);
            }

            @Override
            public void show() {
                super.show();
                System.out.println("remove");
            }

            @Override
            public void execute() {
                super.execute();
                if(command.startsWith("remove")){
                    remove(command.split(" ")[1]);
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

            }
            private void deleteUser(String username){
                ManagerController.getInstance().deleteUser(username);
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

            }


            @Override
            public void execute() {
                super.execute();
                if(command.equals("create manager profile")){
                    createManagerProfile();
                }
                if(command.startsWith("view")){
                    view(command.split(" ")[1]);
                }
                if(command.startsWith("delete user")){
                    deleteUser(command.split(" ")[1]);
                }


            }
            @Override
            public void show() {
                super.show();
                System.out.println("view");
                System.out.println("delete user");
                System.out.println("create manager profile");

            }

        };
    }

}
