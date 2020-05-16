package Client.Models.Person;

import Client.Models.Cart;

public class Buyer extends Person {
    Cart cart = new Cart(this);

    public Cart getCart() {
        return cart;
    }

}
