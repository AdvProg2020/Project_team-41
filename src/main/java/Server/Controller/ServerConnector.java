package Server.Controller;

import Client.Models.Bid;
import Client.Models.BidChat.BidChatComment;
import Client.Models.Chat.ChatComment;
import Client.Models.Comment;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.Models.Person.*;
import Client.Models.Product;
import Client.Models.Score;
import Client.View.Menus.Menu;
import Server.Controller.UserSectionController.BuyerServerController;
import Server.Controller.UserSectionController.ManagerServerController;
import Server.Controller.UserSectionController.SellerServerController;
import Server.Controller.UserSectionController.UserSectionServerController;
import Server.Database;
import Server.Main;

import java.io.File;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerConnector extends Thread {
    private static ServerSocket serverSocket;
    Socket socket;
    Person person;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    Backup backup;

    public ServerConnector(ServerSocket serverSocket, Socket socket) {
        if (ServerConnector.serverSocket == null) {
            ServerConnector.serverSocket = serverSocket;
        }
        this.socket = socket;
        this.backup=new Backup();
    }

    @Override
    public void run() {
        try {
            try {
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.flush();
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    objectOutputStream.reset();
                    Message message = (Message) objectInputStream.readObject();
                    System.out.println("message received");
                    if(!message.isLarge())
                        System.out.println(message);
                    System.err.println("----------------------");
                    processMessage(message);
                    ServerSaver.write(AllCommands.allData);

                } catch (IOException | ClassNotFoundException e) {
                    ServerSaver.write(AllCommands.allData);
                    Main.getConnectedPeople().remove(person);
                    System.out.println("client disconnected(i guess)");
                    System.out.println(e.getMessage());

                    break;
                } catch (Exception e) {
                    try {
                        e.printStackTrace();
                        objectOutputStream.writeObject(new Message(e));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                        break;
                    }
                }


            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void processMessage(Message message) throws Exception {
        MessageType.ClassTypes classTypes = message.getMessageType().getClassTypes();
        switch (classTypes) {
            case USER_SECTION: {
                processUserMessage(message);
                break;
            }
            case MANAGER_SECTION: {
                processManagerMessage(message);
                break;
            }
            case SELLER_SECTION: {
                processSellerMessage(message);
                break;
            }
            case BUYER_SECTION: {
                processBuyerMessage(message);
                break;
            }
            case LOGIN_REGISTER: {
                processLoginRegister(message);
                break;
            }
            case OFFS: {
                processOffs(message);
                break;
            }
            case ALL_PRODUCTS: {
                processAllProducts(message);
                break;
            }
            case PRODUCT: {
                processProduct(message);
                break;
            }
            case BID: {
                processBid(message);
            }
            case BACKUP:{
                processBackup(message);
                break;
            }

        }
    }

    private void processBid(Message message) throws Exception {
        Object[] inputs = message.getInputs();
        switch (message.getMessageType()) {
            case ADD_BID:{
                BidServerController.getInstance().addBid((String)inputs[0] , (String) inputs[1] , (Seller) inputs[2]);
                sendSuccessful();
                break;
            }
            case GET_ALL_BIDS:{
                objectOutputStream.writeObject(new Message(Database.getInstance().getAllBids()));
                break;
            }
            case GET_BID_BY_ID:{
                objectOutputStream.writeObject(new Message(Database.getInstance().getBidById((String)inputs[0])));
                break;
            }
            case ADD_PARTICIPANT:{
                BidServerController.getInstance().addParticipant((String) inputs[0] , (String) inputs[1] , (int) inputs[2]);
                sendSuccessful();
                break;
            }
            case INCREASE_PRICE:{
                BidServerController.getInstance().IncreasePrice((String) inputs[0] , (String)inputs[1] , (int)inputs[2]);
                sendSuccessful();
                break;
            }
            case GET_BID_CHAT_BOX:{
                objectOutputStream.writeObject(new Message(Database.getInstance().getBidChatBox((String)inputs[0])));
                break;
            }
            case ADD_BID_COMMENT:{
                Database.getInstance().addBidChatComment((BidChatComment)inputs[0]);
                sendSuccessful();
                break;
            }
            case GET_BIDS_HE_WON:{
                objectOutputStream.writeObject(new Message((Database.getInstance().getBidsHeWon((String)inputs[0]))));
                break;
            }
            case PAY_FOR_WON_PRODUCT_IN_BID:{
                BidServerController.getInstance().payForProduct((String)inputs[0] , (int)inputs[1]);
                sendSuccessful();
                break;
            }
        }
    }


    private void processProduct(Message message) throws Exception {
        Object[] inputs = message.getInputs();
        switch (message.getMessageType()) {
            case ADD_COMMENT: {
                ProductServerController.getInstance().addComment((Comment) inputs[0]);
                sendSuccessful();
                break;
            }
            case AMOUNT_OF_DISCOUNT: {
                objectOutputStream.writeObject(new Message(ProductServerController.getInstance().amountOfDiscount((String) inputs[0])));
                break;
            }
            case ADD_PRODUCT_TO_CART:{
                ProductServerController.getInstance().addToCart((Buyer)inputs[0] , (Product)inputs[1]);
                sendSuccessful();
                break;
            }
        }
    }

    private void processAllProducts(Message message) throws Exception {
        Object[] inputs = message.getInputs();
        switch (message.getMessageType()) {
            case GET_ALL_CATEGORIES: {
                objectOutputStream.writeObject(new Message(AllProductsServerController.getInstance().getAllCategories()));
                break;
            }
            case GET_PRODUCT: {
                objectOutputStream.writeObject(new Message(AllProductsServerController.getInstance().getProduct((String) inputs[0])));
                break;
            }
            case GET_ALL_PRODUCTS_FOR_FILTER:{
                objectOutputStream.writeObject(new Message(AllProductsServerController.getInstance().getAllProducts()));
                break;
            }
            case GET_CATEGORY_BY_NAME:{
                objectOutputStream.writeObject(new Message(Database.getInstance().getCategoryByName((String)inputs[0])));
                break;
            }
        }
    }

    private void processUserMessage(Message message) throws Exception {
        Object[] inputs = message.getInputs();
        switch (message.getMessageType()) {
            case GET_PERSONAL_INFO: {
                objectOutputStream.writeObject(new Message(UserSectionServerController.getPersonalInfo((Person) inputs[0])));
                break;
            }
            case EDIT: {
                UserSectionServerController.edit((Person) inputs[0], (String) inputs[1], (String) inputs[2]);
                sendSuccessful();
                break;
            }
            case GET_MANAGER_ACCOUNT_ID:{
                objectOutputStream.writeObject(new Message(UserSectionServerController.getManagerAccountId()));
                break;
            }
            case GET_WAGE:{
                objectOutputStream.writeObject(new Message(Database.getInstance().getWage()));
                break;
            }
            case GET_LOGGED_IN_PERSON:{
                objectOutputStream.writeObject(new Message(UserSectionServerController.getLoggedInPerson((Person) inputs[0])));
                break;
            }
            case INCREASE_CREDIT:{
                UserSectionServerController.increaseCredit((Person) inputs[0], (Integer) inputs[1]);
                sendSuccessful();
                break;
            }
            case GET_MINIMUM_CREDIT:{
                objectOutputStream.writeObject(new Message(UserSectionServerController.getMinimumCredit()));
                break;
            }
        }
    }

    private void processManagerMessage(Message message) throws Exception {
        Object[] inputs = message.getInputs();
        switch (message.getMessageType()) {
            case ACCEPT_ALL_REQUESTS: {
                ManagerServerController.getInstance().acceptAllRequests();
                sendSuccessful();
                break;
            }
            case EDIT_CATEGORY_SPECIAL_FEATURES: {
                ManagerServerController.getInstance().editCategorySpecialFeatures((String) inputs[0], (String) inputs[1]);
                sendSuccessful();
                break;
            }
            case GET_ALL_PRODUCTS: {
                objectOutputStream.writeObject(new Message(ManagerServerController.getInstance().getAllProducts()));
                break;
            }
            case GET_ALL_USERS: {
                objectOutputStream.writeObject(new Message(ManagerServerController.getInstance().getAllUsers()));
                break;
            }
            case GET_USER_BY_USERNAME: {
                objectOutputStream.writeObject(new Message(ManagerServerController.getInstance().getUserByUsername((String) inputs[0])));
                break;
            }
            case DELETE_USER: {
                ManagerServerController.getInstance().deleteUser((String) inputs[0]);
                sendSuccessful();
                break;
            }
            case CREATE_MANAGER_PROFILE: {
                ManagerServerController.getInstance().createManagerProfile((ArrayList<String>) inputs[0]);
                sendSuccessful();
                break;
            }
            case REMOVE_PRODUCT_MANAGER_SECTION: {
                ManagerServerController.getInstance().removeProduct((String) inputs[0]);
                sendSuccessful();
                break;
            }
            case CREATE_DISCOUNT_CODE: {
                ManagerServerController.getInstance().createDiscountCode((ArrayList<String>) inputs[0]);
                sendSuccessful();
                break;
            }
            case VIEW_DISCOUNT_CODE: {
                objectOutputStream.writeObject(new Message(ManagerServerController.getInstance().viewDiscountCode((String) inputs[0])));
                break;
            }
            case EDIT_DISCOUNT_CODE: {
                ManagerServerController.getInstance().editDiscountCode((String) inputs[0], (HashMap<String, String>) inputs[1]);
                sendSuccessful();
                break;
            }
            case REMOVE_DISCOUNT_CODE: {
                ManagerServerController.getInstance().removeDiscountCode((String) inputs[0]);
                sendSuccessful();
                break;
            }
            case SHOW_REQUEST: {
                objectOutputStream.writeObject(new Message(ManagerServerController.getInstance().showRequest()));
                break;
            }
            case GET_REQUEST_DETAILS: {
                objectOutputStream.writeObject(new Message(ManagerServerController.getInstance().getRequestDetails((String) inputs[0])));
                break;
            }
            case ACCEPT_REQUEST: {
                ManagerServerController.getInstance().acceptRequest((String) inputs[0]);
                sendSuccessful();
                break;
            }
            case DECLINE_REQUEST: {
                ManagerServerController.getInstance().declineRequest((String) inputs[0]);
                sendSuccessful();
                break;
            }
            case SHOW_CATEGORIES: {
                objectOutputStream.writeObject(new Message(ManagerServerController.getInstance().showCategories()));
                break;
            }
            case EDIT_CATEGORY_NAME: {
                ManagerServerController.getInstance().editCategoryName((String) inputs[0], (String) inputs[1]);
                sendSuccessful();
                break;
            }
            case ADD_CATEGORY: {
                ManagerServerController.getInstance().addCategory((String) inputs[0], (String) inputs[1]);
                sendSuccessful();
                break;
            }
            case REMOVE_CATEGORY: {
                ManagerServerController.getInstance().removeCategory((String) inputs[0]);
                sendSuccessful();
                break;
            }
            case GET_CATEGORY_SPECIAL_FEATURES_MANAGER_SECTION: {
                objectOutputStream.writeObject(new Message(ManagerServerController.getInstance().getCategorySpecialFeatures((String) inputs[0])));
                break;
            }
            case VIEW_ALL_DISCOUNT_CODES: {
                objectOutputStream.writeObject(new Message(ManagerServerController.getInstance().viewAllDiscountCodes()));
                break;
            }
            case GET_PRODUCT_BY_ID: {
                objectOutputStream.writeObject(new Message(ManagerServerController.getInstance().getProductById((String) inputs[0])));
                break;
            }
            case CHANGE_TRADE_LOG_TO_SENT:{
                ManagerServerController.getInstance().changeTradeLogToSent((String) inputs[0]);
                sendSuccessful();
                break;
            }
            case SET_WAGE:{
                Database.getInstance().setWage((Integer) inputs[0]);
                sendSuccessful();
                break;
            }
            case SET_UP_MANAGER_ACCOUNT_ID:{
                Database.getInstance().setUpManagerAccountId((Manager) inputs[0], (String) inputs[1], (String) inputs[2]);
                sendSuccessful();
                break;
            }
            case VIEW_TRADE_LOGS:{
                objectOutputStream.writeObject(new Message(ManagerServerController.getInstance().getTradeLogs()));
                break;
            }
            case SET_MINIMUM_CREDIT:{
                ManagerServerController.getInstance().setMinimumCredit((Integer) inputs[0]);
                sendSuccessful();
                break;
            }

        }
    }

    private void processSellerMessage(Message message) throws Exception {
        Object[] inputs = message.getInputs();
        switch (message.getMessageType()) {
            case GET_CATEGORY_SPECIAL_FEATURES_SELLER_SECTION: {
                objectOutputStream.writeObject(new Message(SellerServerController.getInstance().getCategorySpecialFeatures((String) inputs[0])));
                break;
            }
            case GET_PRODUCT_BUYERS: {
                objectOutputStream.writeObject(new Message(SellerServerController.getInstance().getProductBuyers((String) inputs[0])));
                break;
            }
            case GET_FACTORY_NAME: {
                objectOutputStream.writeObject(new Message(SellerServerController.getInstance().getFactoryName((Seller) inputs[0])));
                break;
            }
            case GET_SALES_HISTORY: {
                objectOutputStream.writeObject(new Message(SellerServerController.getInstance().getSalesHistory((Seller) inputs[0])));
                break;
            }
            case GET_LOGS: {
                objectOutputStream.writeObject(new Message(SellerServerController.getInstance().getLogs((Seller) inputs[0])));
                break;
            }
            case GET_PRODUCTS: {
                objectOutputStream.writeObject(new Message(SellerServerController.getInstance().getProducts((Seller) inputs[0])));
                break;
            }
            case GET_PRODUCT_SELLER_SECTION: {
                objectOutputStream.writeObject(new Message(SellerServerController.getInstance().getProduct((Seller) inputs[0], (String) inputs[1])));
                break;
            }
            case GET_BUYERS: {
                objectOutputStream.writeObject(new Message(SellerServerController.getInstance().getBuyers((Seller) inputs[0], (String) inputs[1])));
                break;
            }
            case EDIT_PRODUCT: {
                SellerServerController.getInstance().editProduct((Seller) inputs[0], (String) inputs[1], (HashMap<String, String>) inputs[2]);
                sendSuccessful();
                break;
            }
            case ADD_PRODUCT: {
                SellerServerController.getInstance().addProduct((Seller) inputs[0], (ArrayList<String>) inputs[1], (List<Byte>) inputs[2]);
                sendSuccessful();
                break;
            }
            case REMOVE_PRODUCT_SELLER_SECTION: {
                SellerServerController.getInstance().removeProduct((Seller) inputs[0], (String) inputs[1]);
                sendSuccessful();
                break;
            }
            case GET_CATEGORIES: {
                objectOutputStream.writeObject(new Message(SellerServerController.getInstance().getCategories()));
                break;
            }
            case GET_OFFS: {
                objectOutputStream.writeObject(new Message(SellerServerController.getInstance().getOffs((Seller) inputs[0])));
                break;
            }
            case GET_OFF: {
                objectOutputStream.writeObject(new Message(SellerServerController.getInstance().getOff((Seller) inputs[0], (String) inputs[1])));
                break;
            }
            case EDIT_OFF: {
                SellerServerController.getInstance().editOff((String) inputs[0], (Seller) inputs[1], (HashMap<String, String>) inputs[2]);
                sendSuccessful();
                break;
            }
            case ADD_OFF: {
                SellerServerController.getInstance().addOff((Seller) inputs[0], (ArrayList<String>) inputs[1]);
                sendSuccessful();
                break;
            }
            case TRANSFER_MONEY_TO_SELLER:{
                SellerServerController.getInstance().transferMoneyToSeller((Integer) inputs[0], (Integer) inputs[1], (Seller) inputs[2]);
                sendSuccessful();
                break;
            }
        }
    }

    private void processBuyerMessage(Message message) throws Exception {
        Object[] inputs = message.getInputs();
        switch (message.getMessageType()) {
            case GET_CODED_DISCOUNTS: {
                objectOutputStream.writeObject(new Message(BuyerServerController.getInstance().getCodedDiscounts((Person) inputs[0])));
                break;
            }
            case PAY_FOR_THE_SHOP: {
                BuyerServerController.payForTheShop((Buyer) inputs[0]);
                sendSuccessful();
                break;
            }
            case ADD_CODED_DISCOUNT_TO_CART: {
                BuyerServerController.getInstance().addCodedDiscountToCart((Buyer) inputs[0], (String) inputs[1]);
                sendSuccessful();

                break;
            }
            case REMOVE_CODED_DISCOUNT_FROM_CART: {
                BuyerServerController.getInstance().removeCodedDiscountFromCart((Buyer) inputs[0]);
                sendSuccessful();

                break;
            }
            case RATE_THE_PRODUCT: {
                BuyerServerController.getInstance().rateTheProduct((Buyer) inputs[0], (String) inputs[1], (Score) inputs[2]);
                sendSuccessful();

                break;
            }
            case GET_PRODUCT_BUYER_SECTION: {
                objectOutputStream.writeObject(new Message(BuyerServerController.getInstance().getProduct((String) inputs[0])));
                break;
            }
            case GET_CODED_DISCOUNT: {
                objectOutputStream.writeObject(new Message(BuyerServerController.getInstance().getCodedDiscount((String) inputs[0], (Person) inputs[1])));
                break;
            }
            case INCREASE_PRODUCT: {
                BuyerServerController.getInstance().increaseProduct((Buyer) inputs[0], (Integer) inputs[1], (String) inputs[2]);
                sendSuccessful();
                break;
            }
            case DECREASE_PRODUCT: {
                BuyerServerController.getInstance().decreaseProduct((Buyer) inputs[0], (Integer) inputs[1], (String) inputs[2]);
                sendSuccessful();

                break;
            }
            case GET_ALL_BOUGHT_FILES:{
                objectOutputStream.writeObject(new Message(BuyerServerController.getInstance().getAllBoughtFiles((Buyer) inputs[0])));
                break;
            }
            case DOWNLOAD_FILE:{
                objectOutputStream.writeObject(new Message(BuyerServerController.getInstance().downloadFile((Product) inputs[0])));
                break;
            }
            case SET_RECEIVER_INFORMATION:{
                BuyerServerController.getInstance().setReceiverInformation((Buyer) inputs[0], (ArrayList<String>) inputs[1]);
                sendSuccessful();
                break;
            }
        }
    }

    //send after every void method
    private void sendSuccessful() {
        try {
            objectOutputStream.writeObject(new Message(MessageType.SUCCESSFUL));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLoginRegister(Message message) throws Exception {
        Object[] inputs = message.getInputs();

        switch (message.getMessageType()) {
            case LOGIN: {
                Person loginPerson;
                objectOutputStream.writeObject(new Message(loginPerson=LoginRegisterServerController.getInstance().login((String) inputs[0], (String) inputs[1])));
                person=loginPerson;
                Main.getConnectedPeople().add(loginPerson);
                break;
            }
            case REGISTER: {
                LoginRegisterServerController.getInstance().createAccount((Person) inputs[0]);
                sendSuccessful();
                break;
            }
            case CHECK_MANAGER:{
                objectOutputStream.writeObject(new Message(LoginRegisterServerController.getInstance().checkIfManagerExists()));
                break;

            }
            case LOGOUT:{
                Main.getConnectedPeople().remove(person);
                person=null;
                sendSuccessful();
                break;
            }
        }
    }

    private void processOffs(Message message) throws IOException {
        switch (message.getMessageType()) {
            case GET_OFF_PRODUCTS: {
                objectOutputStream.writeObject(new Message(OffsServerController.getInstance().getAllOffProducts()));
                break;
            }
        }
    }
    public void processBackup(Message message) throws IOException {
        Object[] inputs = message.getInputs();
        switch (message.getMessageType()){
            case GET_BACKUPS:{
                objectOutputStream.writeObject(new Message(Main.backup.getBackupPeople()));
                break;
            }
            case SEND_COMMENT:{
                Main.backup.addComment((ChatComment)inputs[0]);
                sendSuccessful();
                break;
            }
            case GET_CHAT_BOX:{
                objectOutputStream.writeObject(new Message(Main.backup.getChatBox((String)inputs[0],(String)inputs[1])));
                break;
            }
            case GET_ALL_CHAT_BOXES:{
                objectOutputStream.writeObject(new Message(Main.backup.getAllChatBoxes((String)inputs[0])));
                break;
            }
        }
    }

}
