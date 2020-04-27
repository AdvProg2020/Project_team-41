import Client.Models.Category;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;
import Client.Models.Product;
import Client.View.Menus.MainMenu;
import Server.Database;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
//        for (Category category : Database.getAllCategory()) {
//            System.out.println("name:" +category.getName());
//        }
//        for (Product product : Database.getAllProducts()) {
//            System.out.println(product.getName());
//        }
//        for (Person allUser : Database.getAllUsers()) {
//            System.out.println(allUser.getUserName());
//        }

      //  makeSomeProducts_CategoriesForTest();
        startProgram();
        MainMenu.getInstance().show();
        MainMenu.getInstance().execute();

    }

    private static void makeSomeProducts_CategoriesForTest() {
        Seller seller_1 = new Seller();

        ArrayList<String> specialFeatures = new ArrayList<>();
        specialFeatures.add("writer");
        Category books = new Category("books", specialFeatures);
        Category shirts = new Category("shirts", new ArrayList<>());

        Product biganeh = new Product();
        biganeh.setName("biganeh");
        biganeh.setCompanyName("company_1");
        biganeh.setPrice(10000);
        biganeh.setSeller(seller_1);
        biganeh.setThereMore(true);
        biganeh.getSpecialFeatures().put("writer", "alberkamo");
        Database.getAllProducts().add(biganeh);

        Product maskh = new Product();
        maskh.setName("maskh");
        maskh.setCompanyName("company_2");
        maskh.setPrice(5000);
        maskh.setSeller(seller_1);
        maskh.setThereMore(true);
        maskh.getSpecialFeatures().put("writer", "kafka");
        Database.getAllProducts().add(maskh);

        books.getProducts().add(biganeh);
        books.getProducts().add(maskh);
    }

    private static void startProgram() {
        //todo read files from resources
    }

    private static void endProgram() {

    }
}