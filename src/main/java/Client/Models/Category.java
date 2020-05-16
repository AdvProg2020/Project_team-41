package Client.Models;

import Server.Database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Category implements Serializable {
    private String name;
    private ArrayList<String> specialFeatures;
    private ArrayList<Product>products;

    public Category(String name, ArrayList<String> specialFeatures) {
        this.products = new ArrayList<>();
        this.name = name;
        this.specialFeatures = specialFeatures;
    }

    public void setSpecialFeatures(ArrayList<String> specialFeatures) {
        this.specialFeatures = specialFeatures;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return name.equals(category.name) &&
                specialFeatures.equals(category.specialFeatures) &&
                products.equals(category.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, specialFeatures, products);
    }
}
