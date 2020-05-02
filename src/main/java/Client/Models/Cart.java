package Client.Models;

import Client.Models.Person.Buyer;

import java.util.ArrayList;

public class Cart {
    private Buyer buyer;
    private ArrayList<Product> products;

    public Cart(Buyer buyer) {
        this.buyer = buyer;
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
