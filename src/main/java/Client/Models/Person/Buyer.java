package Client.Models.Person;

import Client.Models.Cart;
import Server.Controller.AllCommands;
import Server.Controller.ServerSaver;

public class Buyer extends Person {
    Cart cart = new Cart(this);

    public Cart getCart() {
        return cart;
    }
    public void renewCart(){
        cart = new Cart(this);
        ServerSaver.write(AllCommands.allData);
    }

}
