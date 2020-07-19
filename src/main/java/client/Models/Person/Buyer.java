package Client.Models.Person;

import Client.Models.Cart;
import Server.Controller.AllCommands;
import Server.Controller.ServerSaver;

import java.util.Objects;

public class Buyer extends Person {
    Cart cart = new Cart(this);

    public Cart getCart() {
        return cart;
    }
    public void renewCart(){
        cart = new Cart(this);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Buyer buyer = (Buyer) o;
        return Objects.equals(cart, buyer.cart);
    }

}
