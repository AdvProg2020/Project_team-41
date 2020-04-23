package Client.Models.Person;

import Client.Models.Off;
import Client.Models.Product;

import java.util.ArrayList;

public class Seller extends Person {

    private String factoryName;
    private ArrayList<Product> products;
    private ArrayList<Off> offs;

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }
}
