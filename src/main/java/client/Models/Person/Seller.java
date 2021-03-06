package Client.Models.Person;

import Client.Controller.bankController.BankAPI;
import Client.Models.Off;
import Client.Models.Product;
import Server.Controller.AllCommands;
import Server.Controller.ServerSaver;
import Server.Database;

import java.util.ArrayList;
import java.util.Objects;

public class Seller extends Person {

    private String factoryName;
    private ArrayList<Product> products;
    private final ArrayList<Off> offs;

    public Seller() {
        products = new ArrayList<>();
        offs = new ArrayList<>();
    }

    public String getFactoryName() {
        return factoryName;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;

    }

    public ArrayList<Off> getOffs() {
        return offs;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;

    }
    //only use it in server
    public void increaseCreditWithWage(int money) throws Exception {
        
        this.setCredit(getCredit()+money*(100- Database.getInstance().getWage())/100);

    }
    public void addProduct(Product product){
        products.add(product);

    }
    public void addOff(Off off){
        offs.add(off);

    }
    public void removeProduct(Product product){
        products.remove(product);

    }
    public void removeOff(Off off){
        offs.remove(off);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Seller seller = (Seller) o;
        return Objects.equals(factoryName, seller.factoryName);
    }


}
