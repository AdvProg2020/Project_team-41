package Client.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {

    private String name;
    private ArrayList<String> specialFeatures;
    private ArrayList<Product>products;

    public Category(String name, ArrayList<String> specialFeatures) {
        this.products = new ArrayList<>();
        this.name = name;
        this.specialFeatures = specialFeatures;
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
