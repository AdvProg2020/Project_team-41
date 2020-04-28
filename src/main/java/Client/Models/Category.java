package Client.Models;

import Server.Database;

import java.util.ArrayList;

public class Category {

    private String name;
    private ArrayList<String> specialFeatures;
    private ArrayList<Product>products;

    public Category(String name, ArrayList<String> specialFeatures) {
        this.products = new ArrayList<>();
        this.name = name;
        this.specialFeatures = specialFeatures;
        Database.getAllCategory().add(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getSpecialFeatures() {
        return specialFeatures;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public String getName() {
        return name;
    }
    public void removeProduct(Product product){
        products.remove(product);

    }
}
