import Client.Controller.StartProgram;
import Client.Models.Category;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;
import Client.Models.Product;
import Client.Models.SpecialFeature;
import Client.View.Menus.MainMenu;
import Server.Database;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args)  {

//        String a = "ali";
//        if(a != null){
//            System.out.println("not null");
//        }
//         if(!a.isEmpty()){
//            System.out.println("not empty");
//        }
//
//         a = "";
//        if(a == null){
//            System.out.println("11111111111");
//        }
//        if(a.isEmpty()){
//            System.out.println("2222222222");
//        }
//
//        a = null;
//        if(a == null){
//            System.out.println("null");
//        }
//         if(a.isEmpty()){
//            System.out.println("empty");
//        }
//
//        ArrayList<Integer> a = new ArrayList<>();
//        if(a == null){
//            System.out.println("null");
//        }
//         if(a.isEmpty()){
//            System.out.println("empty");
//        }
//
//         a.add(20);
//        if(a != null){
//            System.out.println("not null");
//        }
//        if(!a.isEmpty()){
//            System.out.println("not empty");
//        }
//
//        a.clear();
//        if(a == null){
//            System.out.println("1111111");
//        }
//        if(a.isEmpty()){
//            System.out.println("222222");
//        }

        StartProgram.startProgram();
        MainMenu.getInstance().show();
        MainMenu.getInstance().execute();

    }
}
