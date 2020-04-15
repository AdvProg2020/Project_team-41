package Client.View.Menus.UserSectionMenus.BuyerAccount;

import Client.Controller.UserSectionController.BuyerAccountController.BuyerController;
import Client.View.Menus.Menu;

public class Purchase extends Menu {
    public Purchase(Menu superMenu) {
        super(superMenu, "purchase");
        this.addSubMenu(receiverInformation());
    }

    private Menu receiverInformation(){
        return new Menu(this , "receiver information") {
            @Override
            public void show() {
                //TODO print sth for user to enter information
            }

            @Override
            public void execute() {
                //TODO get information from user
                //TODO ability to back to past menu(Purchase)



                //going to next menu:
                this.addSubMenu(new Menu(this, "discount code") {
                    @Override
                    public void show() {
                        //TODO print sth for user to enter information
                    }

                    @Override
                    public void execute() {
                        // get discount code from user:
                        String input = scanner.nextLine();
                        BuyerController.checkDiscountCode(input);
                        //TODO print result of validation of the code
                        //TODO ability to back to past menu(receiver information)



                        //go to next menu:
                        this.addSubMenu(new Menu(this, "payment") {
                            @Override
                            public void show() {
                                //TODO print sth for user to show what he/she should do
                            }

                            @Override
                            public void execute() {
                                BuyerController.payForTheShop();
                                //TODO print the result of payment
                                //TODO ability to back to past menu(receiver information)
                            }
                        });
                    }
                });
            }
    };
}
}

