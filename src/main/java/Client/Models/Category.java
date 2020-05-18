package Client.Models;

import Server.Controller.AllCommands;
import Server.Controller.ServerSaver;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {
    private String name;
    private ArrayList<String> specialFeatures;
    private ArrayList<Product>products;

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
        ServerSaver.write(AllCommands.allData);
    }

    public void setName(String name) throws Exception {
        if(name.isBlank())
            throw new Exception("category name can't be blank!");
        this.name = name;
        ServerSaver.write(AllCommands.allData);
    }

    public ArrayList<String> getSpecialFeatures() {
        return specialFeatures;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
        ServerSaver.write(AllCommands.allData);
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
        ServerSaver.write(AllCommands.allData);

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

}
