package Client.View.Menus.UserSectionMenus;

import Client.Controller.UserSectionController.BuyerAccountController.BuyerController;
import Client.Controller.UserSectionController.SellerController;
import Client.Models.Person.Seller;
import Client.View.Menus.Menu;

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

            }

        };
    }
    public Menu addMangeProduct(){
        return new Menu(this,"MangeProduct") {
            @Override
            public void show() {
                //todo
            }

            @Override
            public void execute() {
                //todo
            }
            public void viewProduct(int id){

            }
            public void viewProductBuyers(int id){

            }
            public void editProduct(int id){

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
