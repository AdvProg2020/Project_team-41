import Client.Controller.EndProgram;
import Client.Controller.StartProgram;
import Client.Models.Category;
import Client.Models.Off;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;
import Client.Models.Product;
import Client.Models.SpecialFeature;
import Client.View.Menus.MainMenu;
import Server.Database;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

//        Seller seller_1 = new Seller();
//        seller_1.setUserName("seller_1");
//        Seller seller_2 = new Seller();
//        seller_2.setUserName("seller_2");
//        Database.getAllUsers().add(seller_1);
//        Database.getAllUsers().add(seller_2);
//
//        ArrayList<String> specialFeatures = new ArrayList<>();
//        specialFeatures.add("writer");
//        specialFeatures.add("pages");
//        Category books = new Category("books" , specialFeatures);
//        Category shirts = new Category("shirts" , new ArrayList<>());
//
//        Product biganeh = new Product();
//        biganeh.setProductId("b");
//        biganeh.setName("biganeh");
//        biganeh.setCompanyName("company_1");
//        biganeh.setPrice(10000);
//        biganeh.setSeller(seller_1);
//        biganeh.setQuantity(2);
//        SpecialFeature biganehWriter =new SpecialFeature();
//        biganehWriter.setSpecialFeatureString("alberkamo");
//        SpecialFeature biganehPages =new SpecialFeature();
//        biganehPages.setSpecialFeatureInt(100);
//        biganeh.getSpecialFeatures().put("writer" , biganehWriter);
//        biganeh.getSpecialFeatures().put("pages" , biganehPages);
//        biganeh.setCategory(books);
//
//        Product maskh = new Product();
//        maskh.setProductId("m");
//        maskh.setName("maskh");
//        maskh.setCompanyName("company_2");
//        maskh.setPrice(5000);
//        maskh.setSeller(seller_1);
//        maskh.setQuantity(2);
//        SpecialFeature maskhWriter =new SpecialFeature();
//        maskhWriter.setSpecialFeatureString("kafka");
//        SpecialFeature maskhPages =new SpecialFeature();
//        maskhPages.setSpecialFeatureInt(200);
//        maskh.getSpecialFeatures().put("writer" , maskhWriter);
//        maskh.getSpecialFeatures().put("pages" , maskhPages);
//        maskh.setCategory(books);
//
//        Product adl = new Product();
//        adl.setProductId("a");
//        adl.setName("adl");
//        adl.setCompanyName("company_3");
//        adl.setPrice(20000);
//        adl.setSeller(seller_2);
//        adl.setQuantity(2);
//        SpecialFeature adlWriter =new SpecialFeature();
//        adlWriter.setSpecialFeatureString("motahari");
//        SpecialFeature adlPages =new SpecialFeature();
//        adlPages.setSpecialFeatureInt(300);
//        adl.getSpecialFeatures().put("writer" , adlWriter);
//        adl.getSpecialFeatures().put("pages" , adlPages);
//        adl.setCategory(books);
//
//        Product T_shirt = new Product();
//        T_shirt.setProductId("t");
//        T_shirt.setName("T_shirt");
//        T_shirt.setCompanyName("company_3");
//        T_shirt.setPrice(10000);
//        T_shirt.setSeller(seller_2);
//        T_shirt.setQuantity(2);
//        T_shirt.setCategory(shirts);
//
//        shirts.addProduct(T_shirt);
//        books.addProduct(biganeh);
//        books.addProduct(maskh);
//        books.addProduct(adl);



        try {
            StartProgram.startProgram();

            for (Category category : Database.getAllCategory()) {
                System.out.println(category.getName());
            }
            System.out.println("");

            for (Person user : Database.getAllUsers()) {
                System.out.println(user.getUserName());
            }
            System.out.println("");

            for (Product product : Database.getAllProducts()) {
                System.out.println(product.getName());
            }
            System.out.println("");

            MainMenu.getInstance().show();
            MainMenu.getInstance().execute();
        } catch (Exception e) {
            e.printStackTrace();
            EndProgram.endProgram();
        }
    }


}
//todo: check every over ride to ensure super is called first