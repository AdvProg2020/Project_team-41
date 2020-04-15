package Client.View.Menus.UserSectionMenus;

import Client.Controller.UserSectionController.ManagerController;
import Client.Models.Person.Manager;
import Client.View.Menus.Menu;

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
