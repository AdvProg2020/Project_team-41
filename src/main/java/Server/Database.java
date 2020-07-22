package Server;

import Client.Controller.bankController.BankAPI;
import Client.Models.*;
import Client.Models.BidChat.BidChatBox;
import Client.Models.BidChat.BidChatComment;
import Client.Models.Chat.ChatBox;
import Client.Models.Person.Buyer;
import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;
import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class Database implements Serializable {
    private static Database database;
    private ArrayList<Category> allCategory = new ArrayList<>();
    private ArrayList<Request> allRequest = new ArrayList<>();
    private ArrayList<Person> allUsers = new ArrayList<>();
    private ArrayList<CodedDiscount> allDiscountCodes = new ArrayList<>();
    private ArrayList<Off> allOffs = new ArrayList<>();
    private ArrayList<ChatBox> chatBoxes = new ArrayList<>();
    private ArrayList<Product> files = new ArrayList<>();
    private ArrayList<Bid> allBids = new ArrayList<>();
    private int accountId;
    private String accountUsername;
    private String accountPassword;
    private int wage;
    private int minimumCredit;

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public static void setInstance(Database newDatabase) {
        database = newDatabase;
        new File("src/main/resources/data/files").mkdir();
        new File("downloadedFiles").mkdir();

    }

    private Database() {

    }

    public ArrayList<ChatBox> getChatBoxes() {
        return chatBoxes;
    }

    public void addChatBox(ChatBox chatBox) {
        chatBoxes.add(chatBox);

    }

    public BidChatBox getBidChatBox(String bidId) {
        for (Bid bid : allBids) {
            if (bid.getBidId().equals(bidId)) {
                return bid.getBidChatBox();
            }
        }
        return null;
    }

    public void addBidChatComment(BidChatComment bidChatComment) {
        for (Bid bid : allBids) {
            if (bid.getBidId().equals(bidChatComment.getBidId())) {
                bid.addComment(bidChatComment);
            }
        }
    }

    public void addBid(Bid newBid) {
        allBids.add(newBid);

    }

    public ArrayList<Off> getAllOffs() {
        return allOffs;
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> allProducts = new ArrayList<>();
        for (Category category : allCategory) {
            allProducts.addAll(category.getProducts());
        }
        return allProducts;
    }

    public ArrayList<Manager> getAllManagers() {
        ArrayList<Manager> allManagers = new ArrayList<>();
        for (Person user : allUsers) {
            if (user instanceof Manager)
                allManagers.add((Manager) user);
        }
        return allManagers;
    }

    public ArrayList<Seller> getAllSellers() {
        ArrayList<Seller> allSellers = new ArrayList<>();
        for (Person user : allUsers) {
            if (user instanceof Seller)
                allSellers.add((Seller) user);
        }
        return allSellers;
    }

    public ArrayList<Buyer> getAllBuyers() {
        ArrayList<Buyer> allBuyers = new ArrayList<>();
        for (Person user : allUsers) {
            if (user instanceof Buyer)
                allBuyers.add((Buyer) user);
        }
        return allBuyers;
    }

    public Category getCategoryByName(String name) throws Exception {
        for (Category category : allCategory) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        throw new Exception("No category found with this name");
    }

    public Person getPersonByUsername(String username) throws Exception {
        for (Person user : allUsers) {
            if (user.getUserName().equalsIgnoreCase(username))
                return user;
        }
        throw new Exception("wrong username");
    }

    public CodedDiscount getCodedDiscountByCode(String code) throws Exception {
        for (CodedDiscount codedDiscount : allDiscountCodes) {
            if (codedDiscount.getDiscountCode().equals(code))
                return codedDiscount;
        }
        throw new Exception("wrong discount code");
    }

    public Product getProductById(String id) throws Exception {
        for (Product product : getAllProducts()) {
            if (product.getProductId().equals(id))
                return product;
        }
        throw new Exception("No product found with this id");
    }

    public ArrayList<CodedDiscount> getAllDiscountCodes() {
        return allDiscountCodes;
    }

    public void addDiscountCodes(CodedDiscount codedDiscount) {
        allDiscountCodes.add(codedDiscount);

    }

    public void setAllUsers(ArrayList<Person> allUsers) {
        this.allUsers = allUsers;
    }

    public void setAllCategory(ArrayList<Category> allCategory) {
        this.allCategory = allCategory;
    }

    public void setAllRequest(ArrayList<Request> allRequest) {
        this.allRequest = allRequest;
    }

    public void setAllDiscountCodes(ArrayList<CodedDiscount> allDiscountCodes) {
        this.allDiscountCodes = allDiscountCodes;
    }

    public void setAllOffs(ArrayList<Off> allOffs) {
        this.allOffs = allOffs;
    }

    public void deleteUser(String username) throws Exception {

        for (Person user : allUsers) {
            if (user.getUserName().equals(username)) {
                allUsers.remove(user);
                return;
            }
        }
        throw new Exception("no user found");
    }

    public ArrayList<Person> getAllUsers() {
        return allUsers;
    }

    public void deleteCodedDiscount(String code) throws Exception {
        for (CodedDiscount discountCode : allDiscountCodes) {
            if (discountCode.getDiscountCode().equals(code)) {
                allDiscountCodes.remove(discountCode);

                return;
            }
        }
        throw new Exception("invalid discount code");
    }

    public void addUser(Person person) throws Exception {
        for (Person user : allUsers) {
            if (user.getUserName().equalsIgnoreCase(person.getUserName()))
                throw new Exception("username Exists");
        }
        allUsers.add(person);
    }

    public void deleteCategory(String categoryName) throws Exception {
        for (Category category : allCategory) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                for (Product product : category.getProducts()) {
                    product.removeProduct();
                }

                allCategory.remove(category);

                return;
            }
        }
        throw new Exception("no category found");

    }

    public void addCategory(Category category) {

        allCategory.add(category);

    }

    public ArrayList<Category> getAllCategory() {
        return allCategory;
    }

    public ArrayList<Request> getAllRequest() {
        return allRequest;
    }

    public void addProduct(Product product) throws Exception {
        for (Category category : allCategory) {
            if (category.getName().equals(product.getCategory().getName())) {
                category.addProduct(product);

                return;
            }
        }
        throw new Exception("no category found while adding product to database");
    }

    public void removeProduct(Product product) throws Exception {
        for (Category category : allCategory) {
            if (category.getName().equals(product.getCategory().getName())) {
                category.removeProduct(product);

                return;
            }
        }
        throw new Exception("no category found while adding product to database");
    }

    public Seller getSellerByUsername(String username) throws NullPointerException {
        for (Person user : allUsers) {
            if (user instanceof Seller) {
                if (user.getUserName().equals(username))
                    return (Seller) user;
            }
        }
        throw new NullPointerException("No seller found with this userName");
    }

    public void addOff(Off off) {
        allOffs.add(off);
    }

    public Request getRequestByRequestId(String requestId) throws Exception {
        for (Request request : allRequest) {
            if (request.getRequestId().equals(requestId))
                return request;
        }
        throw new Exception("no request matched");
    }

    public void addRequest(Request request) {
        allRequest.add(request);
    }

    public ArrayList<Bid> getAllBids() throws Exception {
        Date date = new Date();
        int maxPrice = 0;
        Buyer winner = null;
        for (Bid bid : allBids) {
            for (Buyer buyer : bid.getBuyer_recommendedPrice().keySet()) {
                if (maxPrice < bid.getBuyer_recommendedPrice().get(buyer)) {
                    maxPrice = bid.getBuyer_recommendedPrice().get(buyer);
                    winner = (Buyer) getPersonByUsername(buyer.getUserName());
                }
            }
            bid.setWinnerInfo(new Pair<>(winner, maxPrice));
            maxPrice= 0;
            winner = null;
        }

        ArrayList<Bid> allNewBids = new ArrayList<>();
        for (Bid bid : allBids) {
            if (date.before(bid.getEndDate())) {
                allNewBids.add(bid);
            }
        }

        return allNewBids;
    }

    public ArrayList<Bid> getBidsHeWon(String username) throws Exception {
        ArrayList<Bid> bidsHeWon = new ArrayList<>();
        Date date = new Date();
        getAllBids();
        for (Bid bid : allBids) {
            if (date.after(bid.getEndDate())) {
                if (bid.getWinnerInfo().getKey().getUserName().equals(username)) {
                    bidsHeWon.add(bid);
                }
            }
        }
        return bidsHeWon;
    }

    public ArrayList<Product> getAllOffProducts() {
        ArrayList<Product> allOffProducts = new ArrayList<>();
        ArrayList<Off> offsToDelete = new ArrayList<>();
        Date date = new Date();
        for (Off off : allOffs) {
            if (date.after(off.getEndDate())) {
                offsToDelete.add(off);
            }
        }
        deleteOutOfDateOffs(offsToDelete);
        for (Off off : allOffs) {
            allOffProducts.addAll(off.getProducts());
        }
        return allOffProducts;
    }

    public void deleteOutOfDateOffs(ArrayList<Off> offsToDelete) {
        allOffs.removeAll(offsToDelete);
    }

    public Off getOffById(String Id) throws Exception {
        for (Off off : allOffs) {
            if (off.getOffId().equals(Id))
                return off;
        }
        throw new Exception("wrong off Id");
    }

    public Bid getBidById(String Id) throws Exception {
        for (Bid bid : allBids) {
            if (bid.getBidId().equals(Id))
                return bid;
        }
        throw new Exception("wrong bid Id");
    }

    public void removeRequest(Request request) throws Exception {
        if (!allRequest.remove(request))
            throw new Exception("no request exists like this anymore");
    }

    public void setUpManagerAccountId(Manager manager, String username, String password) throws Exception {
        BankAPI.makeInstance();
        accountId = BankAPI.getInstance().createAccount(manager.getFirstName(), manager.getLastName(), username, password, password);
        System.out.println(accountId);
        this.accountUsername = username;
        this.accountPassword = password;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public String getAccountUsername() {
        return accountUsername;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) throws Exception {
        if ((wage < 0) || wage > 100)
            throw new Exception("invalid wage number");
        this.wage = wage;
    }

    public void addFile(Product product, List<Byte> file) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/data/files/" + product.getName());
            fileOutputStream.write(convertBytes(file));
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        files.add(product);


    }

    public List<Byte> getFile(Product product) throws IOException {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("src/main/resources/data/files/" + product.getName());
        } catch (FileNotFoundException e) {
            return null;
        }
        byte[] bytes = fileInputStream.readAllBytes();
        List<Byte> byteList = new ArrayList<>(bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            byteList.add(i, bytes[i]);
        }
        fileInputStream.close();
        return byteList;
    }

    public void removeFile(Product product) throws Exception {
        File file = new File("src/main/resources/data/files/" + product.getName());
        if (!file.delete()) {
            throw new Exception("file is not removed");
        }
        files.remove(product);

    }

    private byte[] convertBytes(List<Byte> Byte) {
        byte[] file = new byte[Byte.size()];
        Iterator<Byte> iterator = Byte.iterator();
        for (int i = 0; i < file.length; i++) {
            file[i] = iterator.next();
        }
        return file;
    }

    public int getMinimumCredit() {
        return minimumCredit;
    }

    public void setMinimumCredit(int minimumCredit) throws Exception {
        if (minimumCredit < 0) {
            throw new Exception("invalid credit");
        }
        this.minimumCredit = minimumCredit;
    }
}