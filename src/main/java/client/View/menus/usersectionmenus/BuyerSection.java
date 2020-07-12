package Client.View.Menus.UserSectionMenus;

import Client.Controller.UserSectionController.BuyerController;
import Client.Models.Cart;
import Client.Models.Product;
import Client.Models.TradeLog;
import Client.View.Menus.MainMenu;
import Client.View.Menus.Menu;
import Client.View.Menus.ProductMenu;

import java.util.ArrayList;

public class BuyerSection extends UserSection {
    public BuyerSection(Menu superMenu) {
        super(superMenu, "BuyerSection");
        this.addSubMenu(addViewCart());
        this.addSubMenu(addPurchase());
        this.addSubMenu(addViewOrders());
    }

    @Override
    public void commands() {
        System.out.println("view balance");
        System.out.println("view discount codes");
        super.commands();
    }

    @Override
    public void execute() {
        super.execute();
        if(command.equalsIgnoreCase("view balance")){
            viewBalance();
        }
        else if(command.equalsIgnoreCase("view discount codes")){
            viewDiscountCodes();
        }

        System.out.println("invalid command");
        this.show();
        this.execute();
    }

    private Menu addViewCart(){
        return new Menu(this,"view cart") {
            @Override
            public void show() {
                super.show();
                System.out.println("show Products");
                System.out.println("view [productId]");
                System.out.println("increase [productId]");
                System.out.println("decrease [productId]");
                System.out.println("show total price");
            }

            @Override
            public void execute() {
                super.execute();
                if(command.equalsIgnoreCase("show products")){
                    showProducts();
                }
                else if(command.startsWith("view")){
                    try {
                        viewProduct(command.split(" ")[1]);
                    }
                    catch (Exception e) {
                        if(e instanceof ArrayIndexOutOfBoundsException) {
                            System.out.println("invalid command");
                            this.show();
                            this.execute();
                        }
                        System.out.println(e.getMessage());
                    }
                }
                else if(command.startsWith("increase")){
                    try {
                        increase(command.split(" ")[1]);
                    }
                    catch (Exception e) {
                        if(e instanceof ArrayIndexOutOfBoundsException) {
                            System.out.println("invalid command");
                            this.show();
                            this.execute();
                        }
                        System.out.println(e.getMessage());
                    }
                }
                else if(command.startsWith("decrease")){
                    try {
                        decrease(command.split(" ")[1]);
                    }
                    catch (Exception e) {
                        if(e instanceof ArrayIndexOutOfBoundsException) {
                            System.out.println("invalid command");
                            this.show();
                            this.execute();
                        }
                        System.out.println(e.getMessage());
                    }
                }
                else if(command.equalsIgnoreCase("show total price")){
                    showTotalPrice();
                }
                else{
                    System.out.println("invalid command");
                }
                this.show();
                this.execute();

            }
            private void showProducts(){
                Cart cart = BuyerController.getInstance().getCart();
                if(cart.getProducts().isEmpty()){
                    System.out.println("cart is empty");
                }
                else{
                    System.out.println(cart);
                }
                this.show();
                this.execute();
            }
            private void showTotalPrice(){
                int totalPrice = BuyerController.getInstance().getCart().totalPrice();
                System.out.println(totalPrice);
                this.show();
                this.execute();
            }
            private void viewProduct(String productId) throws Exception {
                ProductMenu productMenu = new ProductMenu(this);
                productMenu.setTheProduct(BuyerController.getInstance().getProduct(productId));
                productMenu.show();
                productMenu.execute();
            }
            private void increase(String productId) throws Exception {
                Product product = BuyerController.getInstance().getProduct(productId);
                BuyerController.getInstance().getCart().increaseProductQuantity(product);
                this.show();
                this.execute();
            }
            private void decrease(String productId) throws Exception {
                Product product = BuyerController.getInstance().getProduct(productId);
                BuyerController.getInstance().getCart().decreaseProductQuantity(product);
                this.show();
                this.execute();
            }
        };
    }

    private Menu addPurchase(){
        return new Menu(this,"Purchase") {
            @Override
            public void show() {
                super.show();
                System.out.println("you are going to purchase products, are you willing to proceed?[y/n]");
            }

            @Override
            public void execute() {
                super.execute();
                if(command.equalsIgnoreCase("y")) {
                    if(BuyerController.getInstance().getCart().getProducts().isEmpty()) {
                        System.out.println("you haven't selected any products yet");
                        this.superMenu.show();
                        this.superMenu.execute();
                    }
                    Menu receiverInformationMenu = addReceiverInformation();
                    receiverInformationMenu.show();
                    receiverInformationMenu.execute();
                }
                else if(command.equalsIgnoreCase("n")){
                    this.superMenu.show();
                    this.superMenu.execute();
                }
                else{
                    System.out.println("invalid command");
                    this.show();
                    this.execute();
                }
            }


            private Menu addReceiverInformation() {
                return new Menu(this, "receiver information") {
                    @Override
                    public void show() {
                        super.show();
                        System.out.println("ok. now you must enter your information");
                        System.out.println("do you want to proceed?[y/n]");
                    }

                    @Override
                    public void execute() {
                        super.execute();
                        if(!command.equalsIgnoreCase("y") && !command.equalsIgnoreCase("n")) {
                            System.out.println("invalid command");
                            this.show();
                            this.execute();
                        }
                        else if(command.equalsIgnoreCase("n")){
                            super.show();
                            super.execute();
                        }
                        else if (command.equalsIgnoreCase("y")) {
                            ArrayList<String> receiverInformation = new ArrayList<>();
                            System.out.println("enter your address");
                            receiverInformation.add(scanner.nextLine());
                            System.out.println("enter your phone number");
                            receiverInformation.add(scanner.nextLine());
                            BuyerController.getInstance().setReceiverInformation(receiverInformation);
                            Menu discountCodeMenu = addDiscountCode();
                            discountCodeMenu.show();
                            discountCodeMenu.execute();
                        }
                        else{
                            System.out.println("unexpected error");
                            this.show();
                            this.execute();
                        }

                    }
                };
            }
            private Menu addDiscountCode() {
                return new Menu(addReceiverInformation(), "discountCode") {
                    @Override
                    public void show() {
                        super.show();
                        System.out.println("do you have any discount codes?[y/n]");

                    }

                    @Override
                    public void execute() {
                        super.execute();
                        Menu paymentMenu = addPayment();
                        if(!command.equalsIgnoreCase("y") && !command.equalsIgnoreCase("n")) {
                            System.out.println("invalid command");
                            this.show();
                            this.execute();
                        }
                        else if(command.equalsIgnoreCase("n")){
                            paymentMenu.show();
                            paymentMenu.execute();

                        }
                        else if (command.equalsIgnoreCase("y")) {
                            System.out.println("ok. what is it?");
                            addDiscountCode(scanner.nextLine());
                            paymentMenu.show();
                            paymentMenu.execute();
                        }
                        else{
                            System.out.println("unexpected error");
                            this.show();
                            this.execute();
                        }

                    }
                    private void addDiscountCode(String discountCode){
                        try {
                            BuyerController.getInstance().addCodedDiscountToCart(discountCode);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            this.show();
                            this.execute();
                        }
                    }


                };
            }
            private Menu addPayment() {
                return new Menu(addDiscountCode(), "payment") {
                    @Override
                    public void show() {
                        super.show();
                        System.out.println("you must pay " + BuyerController.getInstance().calculateTotalPrice() + "Rials");
                        System.out.println("do you want to pay or not?[y/n]");
                    }

                    @Override
                    public void execute() {
                        super.execute();
                        if(command.equalsIgnoreCase("n")){
                            System.out.println("if you want it,you must pay for it");
                            this.show();
                            this.execute();
                        }
                        else if(command.equalsIgnoreCase("y")){
                            try {
                                BuyerSection buyerSectionMenu = new BuyerSection(MainMenu.getInstance());
                                BuyerController.getInstance().payForTheShop(true);
                                System.out.println("congratulation! you bought the products");
                                buyerSectionMenu.show();
                                buyerSectionMenu.execute();
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                this.show();
                                this.execute();
                            }
                        }
                        else{
                            System.out.println("invalid command");
                        }

                    }
                };
            }
        };
    }

    private Menu addViewOrders() {
        return new Menu(this, "view order") {
            private void showOrder(String orderId){
                try {
                    System.out.println(BuyerController.getInstance().showTheOrder(orderId));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                this.show();
                this.execute();

            }
            private void rate(String productId,int rate){
                try {
                    BuyerController.getInstance().rateTheProduct(productId,rate);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                this.show();
                this.execute();

            }
            @Override
            public void show() {
                super.show();

                int i = 1;
                for (TradeLog tradeLog : BuyerController.getInstance().getTradeLogs()) {
                    System.out.println(i + " : ");
                    System.out.println(tradeLog.getLogId());
                }
                System.out.println();
                System.out.println("commands : ");
                System.out.println("view <orderId>");
                System.out.println("rate <productId [1-5]>");
            }

            @Override
            public void execute() {
                super.execute();
                if(command.startsWith("view")){
                    try {
                        showOrder(command.split(" ")[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("invalid command");
                            this.show();
                            this.execute();

                    }
                }
                else if(command.startsWith("rate")) {
                    try {
                        rate(command.split(" ")[1], Integer.parseInt(command.split(" ")[2]));
                    } catch (Exception e) {
                        if(e instanceof ArrayIndexOutOfBoundsException) {
                            System.out.println("invalid command");
                            this.show();
                            this.execute();
                        }
                        else if(e instanceof NumberFormatException) {
                            System.out.println("invalid number");
                            this.show();
                            this.execute();
                        }
                        else{
                            System.out.println(e.getMessage());
                            this.show();
                            this.execute();
                        }

                    }
                }
                System.out.println("invalid command");
                this.show();
                this.execute();

            }
        };
    }

    private void viewBalance(){
        System.out.println("your current balance is : " + BuyerController.getInstance().getBalance());
        this.show();
        this.execute();
    }
    private void viewDiscountCodes(){
        try {
            for (String codedDiscount : BuyerController.getInstance().getCodedDiscounts()) {
                System.out.println(codedDiscount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}