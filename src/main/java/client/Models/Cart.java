package Client.Models;

import Client.Models.Person.Buyer;
import Server.Controller.AllCommands;
import Server.Controller.ServerSaver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Cart implements Serializable {
    private Buyer buyer;
    private HashMap<Product,Integer> products;
    private ArrayList<String> receiverInformation;
    private CodedDiscount codedDiscount;
    public Cart(Buyer buyer) {
        this.buyer = buyer;
        products = new HashMap<>();
    }

    public ArrayList<String> getReceiverInformation() {
        return receiverInformation;
    }

    public CodedDiscount getCodedDiscount() {
        return codedDiscount;
    }

    public void setCodedDiscount(CodedDiscount codedDiscount) {
        this.codedDiscount = codedDiscount;
        ServerSaver.write(AllCommands.allData);
    }

    public void setReceiverInformation(ArrayList<String> receiverInformation) {
        this.receiverInformation = receiverInformation;
        ServerSaver.write(AllCommands.allData);
    }

    public HashMap<Product,Integer> getProducts() {
        return products;
    }
    public void increaseProductQuantity(Product product) throws Exception {
        int productQuantity;
        try {
            productQuantity = products.get(product);
        } catch (Exception e) {
            throw new Exception("there is no such product in the cart");
        }
        products.put(product,productQuantity+1);
        ServerSaver.write(AllCommands.allData);
    }
    public void decreaseProductQuantity(Product product) throws Exception {
        int productQuantity;
        try {
            productQuantity = products.get(product);
        } catch (Exception e) {
            throw new Exception("there is no such product in the cart");
        }
        if(productQuantity == 1)
            products.remove(product,productQuantity);
        products.put(product,productQuantity-1);
        ServerSaver.write(AllCommands.allData);
    }

    public int totalPrice(){
        int totalPrice = 0;
        for (Product product : products.keySet()) {
            totalPrice += (product.getPriceWithOff()*products.get(product));
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder products = new StringBuilder();
        for (Product product : this.products.keySet()) {
            products.append("\nname: ").append(product.getName());
            products.append("\nid: ").append(product.getProductId());
            products.append("\nquantity: ").append(this.products.get(product));
        }
        return "Cart : " +
                "\nproducts : " + products;
    }
    public void addProduct(Product product){
        products.put(product,1);
        ServerSaver.write(AllCommands.allData);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(buyer, cart.buyer) &&
                Objects.equals(products, cart.products) &&
                Objects.equals(receiverInformation, cart.receiverInformation) &&
                Objects.equals(codedDiscount, cart.codedDiscount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, receiverInformation, codedDiscount);
    }
}
