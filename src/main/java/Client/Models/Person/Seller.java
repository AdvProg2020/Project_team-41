package Client.Models.Person;

import Client.Models.Off;
import Client.Models.Product;
import Client.Models.TradeLog;
import Server.Controller.AllCommands;
import Server.Controller.ServerSaver;

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
        ServerSaver.write(AllCommands.allData);
    }

    public ArrayList<Off> getOffs() {
        return offs;
    }

    public void setOffs(ArrayList<Off> offs) {
        this.offs = offs;
        ServerSaver.write(AllCommands.allData);
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
        ServerSaver.write(AllCommands.allData);
    }

    public void addCredit(int money) throws Exception {
        this.setCredit(getCredit()+money);
        ServerSaver.write(AllCommands.allData);
    }
    public void addTradeLog(TradeLog tradeLog){
        getTradeLogs().add(tradeLog);
        ServerSaver.write(AllCommands.allData);
    }
    public void addProduct(Product product){
        products.add(product);
        ServerSaver.write(AllCommands.allData);
    }
    public void addOff(Off off){
        offs.add(off);
        ServerSaver.write(AllCommands.allData);
    }
    public void removeProduct(Product product){
        products.remove(product);
        ServerSaver.write(AllCommands.allData);
    }
    public void removeOff(Off off){
        offs.remove(off);
        ServerSaver.write(AllCommands.allData);
    }

}
