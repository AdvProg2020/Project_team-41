package Client.Models;

import Server.Controller.AllCommands;
import Server.Controller.ServerSaver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Category implements Serializable {
    private String name;
    private ArrayList<String> specialFeatures;
    private final ArrayList<Product>products;

    public Category(String name, ArrayList<String> specialFeatures) throws Exception {
        this.products = new ArrayList<>();
        this.setName(name);
        this.setSpecialFeatures(specialFeatures);
    }

    public void setSpecialFeatures(ArrayList<String> specialFeatures) throws Exception {
        for (String specialFeature : specialFeatures) {
            if(specialFeature.isBlank())
                throw new Exception("special features can't be blank!");
        }
        this.specialFeatures = specialFeatures;

    }

    public void setName(String name) throws Exception {
        if(name.isBlank())
            throw new Exception("category name can't be blank!");
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
        Off off = product.getOff();
        product.getSeller().removeProduct(product);
        if (off != null)
            off.removeProduct(product);
        products.remove(product);


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) &&
                Objects.equals(specialFeatures, category.specialFeatures) &&
                Objects.equals(products, category.products);
    }

}
