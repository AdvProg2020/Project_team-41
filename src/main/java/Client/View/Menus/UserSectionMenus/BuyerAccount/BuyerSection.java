package Client.View.Menus.UserSectionMenus.BuyerAccount;

import Client.Controller.UserSectionController.BuyerAccountController.BuyerController;
import Client.Models.TradeLog;
import Client.View.Menus.Menu;
import Client.View.Menus.UserSectionMenus.UserSection;

public class BuyerSection extends UserSection {
    public BuyerSection(Menu superMenu) {
        super(superMenu, "BuyerSection");
        this.addSubMenu(new ViewCart(this));
        this.addSubMenu(new Purchase(this));
        this.addSubMenu(addViewOrders());
        this.addSubMenu(addViewBalance());
        this.addSubMenu(addViewDiscountCodes());
    }

    private Menu addViewOrders() {
        return new Menu(this, "view order") {
            private void showOrder(String orderId){
                System.out.println(BuyerController.getInstance().showTheOrder(orderId));
            }
            private void rate(String productId,int rate){
                try {
                    BuyerController.getInstance().rateTheProduct(productId,rate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void show() {
                int i = 1;
                for (TradeLog tradeLog : BuyerController.getInstance().getTradeLogs()) {
                    System.out.println(i + " : ");
                    System.out.println(tradeLog.getLogId());
                }
                System.out.println();
                System.out.println("commands : ");
                System.out.println("showOrder");
                System.out.println("rate");
            }

            @Override
            public void execute() {
                if(command.startsWith("view")){
                    showOrder(command.split(" ")[1]);
                }
                else if(command.startsWith("edit")) {
                    rate(command.split(" ")[1], Integer.parseInt(command.split(" ")[2]));
                }
                else
                    System.out.println("invalid command");
            }
        };
    }

    private Menu addViewBalance(){
        return new Menu(this , "view balance") {
            @Override
            public void show() {
                System.out.println("your current balance is : " + BuyerController.getInstance().getBalance());
            }

        };
    }

    private Menu addViewDiscountCodes(){
        return new Menu(this , "view discount codes") {
            @Override
            public void show() {
                for (String codedDiscount : BuyerController.getInstance().getCodedDiscounts()) {
                    System.out.println(codedDiscount);
                }
            }

        };
    }
}