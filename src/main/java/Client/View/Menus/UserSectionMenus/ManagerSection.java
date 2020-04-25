package Client.View.Menus.UserSectionMenus;

import Client.Controller.UserSectionController.ManagerController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Person.Person;
import Client.View.Menus.Menu;

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

            }
            private void add(String Category){

            }
            private void remove(String Category){

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
