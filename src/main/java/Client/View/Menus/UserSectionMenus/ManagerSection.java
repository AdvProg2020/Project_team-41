package Client.View.Menus.UserSectionMenus;

import Client.Controller.UserSectionController.ManagerController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Person.Person;
import Client.View.Menus.Menu;
import Client.View.Menus.RegisterLoginMenu;

import java.util.ArrayList;

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
                System.out.println("now type it's special features(type them with a comma between each special feature");
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

            }
        };
    }

    private Menu addManageRequests(){
        return new Menu(this,"ManageRequests") {

            private void details(String requestId){

            }
            private void accept(String requestId){

            }
            private void decline(String requestId){

            }


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

    private Menu addViewDiscountCodes(){
        return new Menu(this,"ViewDiscountCodes") {
            private void viewDiscountCode(String code){

            }
            private void editDiscountCode(String code){

            }
            private void removeDiscountCode(String code){

            }

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

    private Menu addCreateDiscountCode(){
        return new Menu(this,"CreateDiscountCode") {
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

    private Menu addManageAllProducts(){
        return new Menu(this,"ManageAllProducts") {
            private void remove(String productId){

            }

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



            }


            @Override
            public void execute() {
                super.execute();


            }
            @Override
            public void show() {
                super.show();

            }

        };
    }

}
