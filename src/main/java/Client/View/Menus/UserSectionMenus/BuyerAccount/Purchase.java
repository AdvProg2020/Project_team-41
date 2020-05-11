package Client.View.Menus.UserSectionMenus.BuyerAccount;

import Client.Controller.UserSectionController.BuyerAccountController.BuyerController;
import Client.View.Menus.Menu;

import java.util.ArrayList;

public class Purchase extends Menu {

    public Purchase(Menu superMenu) {
        super(superMenu, "purchase");
    }

    @Override
    public void execute() {
        super.execute();
        payment().show();
        payment().execute();
    }

    private Menu receiverInformation() {
        return new Menu(this, "receiver information") {
            @Override
            public void show() {
                System.out.println("ok. now you must enter your information");
            }

            @Override
            public void execute() {
                ArrayList<String> userInformation = new ArrayList<>();
                System.out.println("enter your address");
                userInformation.add(scanner.nextLine());
                System.out.println("enter your phone number");
                userInformation.add(scanner.nextLine());
                System.out.println("enter your ");
                userInformation.add(scanner.nextLine());


            }
        };
    }
    private Menu payment() {
        return new Menu(this, "payment") {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    private Menu discountCode() {
        return new Menu(this, "discountCode") {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
}
