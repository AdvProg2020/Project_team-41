import Client.Controller.StartProgram;
import Client.View.Menus.MainMenu;
import java.util.ArrayList;
import java.util.HashMap;
import Client.Models.Category;
import Client.Models.Person.Seller;
import Client.Models.Product;
import Client.Models.SpecialFeature;
import Server.Database;

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
//        specialFeatures.add("edition");
//        Category books = new Category("books" , specialFeatures);
//        Category shirts = new Category("shirts" , new ArrayList<>());
//        Database.getAllCategory().add(books);
//        Database.getAllCategory().add(shirts);
//
//        Product biganeh = new Product();
//        biganeh.setName("biganeh");
//        biganeh.setCompanyName("company_1");
//        biganeh.setPrice(10000);
//        biganeh.setSeller(seller_1);
//        biganeh.setQuantity(2);
//        biganeh.setProductId("b");
//        SpecialFeature biganehWriter =new SpecialFeature("alberkamo");
//        SpecialFeature biganehPages =new SpecialFeature("100");
//        SpecialFeature biganehEdition =new SpecialFeature("1");
//        biganeh.getSpecialFeatures().put("writer" , biganehWriter);
//        biganeh.getSpecialFeatures().put("pages" , biganehPages);
//        biganeh.getSpecialFeatures().put("edition" , biganehEdition);
//        biganeh.setCategory(books);
//
//        Product maskh = new Product();
//        maskh.setName("maskh");
//        maskh.setCompanyName("company_2");
//        maskh.setPrice(5000);
//        maskh.setSeller(seller_1);
//        maskh.setQuantity(2);
//        maskh.setProductId("m");
//        SpecialFeature maskhWriter =new SpecialFeature("kafka");
//        SpecialFeature maskhPages =new SpecialFeature("200");
//        SpecialFeature maskhEdition =new SpecialFeature("2");
//        maskh.getSpecialFeatures().put("writer" , maskhWriter);
//        maskh.getSpecialFeatures().put("pages" , maskhPages);
//        maskh.getSpecialFeatures().put("edition" , maskhEdition);
//        maskh.setCategory(books);
//
//        Product adl = new Product();
//        adl.setName("adl");
//        adl.setCompanyName("company_3");
//        adl.setPrice(20000);
//        adl.setSeller(seller_2);
//        adl.setQuantity(2);
//        adl.setProductId("a");
//        SpecialFeature adlWriter =new SpecialFeature("motahari");
//        SpecialFeature adlPages =new SpecialFeature("300");
//        SpecialFeature adlEdition =new SpecialFeature("2");
//        adl.getSpecialFeatures().put("writer" , adlWriter);
//        adl.getSpecialFeatures().put("pages" , adlPages);
//        adl.getSpecialFeatures().put("edition" , adlEdition);
//        adl.setCategory(books);
//
//        Product T_shirt = new Product();
//        T_shirt.setName("T_shirt");
//        T_shirt.setCompanyName("company_3");
//        T_shirt.setPrice(10000);
//        T_shirt.setSeller(seller_2);
//        T_shirt.setQuantity(2);
//        T_shirt.setProductId("t");
//        T_shirt.setCategory(shirts);
//
//        Product kimiagar = new Product("kimiagar" , "company_2" , 20000 , seller_1
//                , 3 , books , new HashMap<String, SpecialFeature>() , "roman khobie");
//        kimiagar.setProductId("k");
//
//        shirts.addProduct(T_shirt);
//        books.addProduct(biganeh);
//        books.addProduct(maskh);
//        books.addProduct(adl);
//        books.addProduct(kimiagar);

            StartProgram.startProgram();
            MainMenu.getInstance().show();
            MainMenu.getInstance().execute();
    }


}