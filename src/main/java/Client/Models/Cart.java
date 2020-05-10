package Client.Models;

import Client.Models.Person.Buyer;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    private Buyer buyer;
    private HashMap<Product,Integer> products;

    public Cart(Buyer buyer) {
        this.buyer = buyer;
        products = new HashMap<Product, Integer>();
    }

    public HashMap<Product,Integer> getProducts() {
        return products;
    }
    public void increaseProductQuantity(Product product){
        int productQuantity = products.get(product);
        products.put(product,productQuantity+1);
    }
    public void decreaseProductQuantity(Product product){
        int productQuantity = products.get(product);
        products.put(product,productQuantity-1);
    }
    public int totalPrice(){
        int totalPrice = 0;
        for (Product product : products.keySet()) {
            totalPrice += (product.getPrice()*products.get(product));
        }
        return totalPrice;
    }

}
