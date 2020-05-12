package Client.Models.Person;

import Client.Models.Off;
import Client.Models.Product;
import Client.Models.TradeLog;

import java.util.ArrayList;

public class Seller extends Person {

    private String factoryName;
    private ArrayList<Product> products;
    private ArrayList<Off> offs;

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

    public void setOffs(ArrayList<Off> offs) {
        this.offs = offs;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public void addCredit(int money) throws Exception {
        this.setCredit(getCredit()+money);
    }
    public void addTradeLog(TradeLog tradeLog){
        getTradeLogs().add(tradeLog);
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

}
