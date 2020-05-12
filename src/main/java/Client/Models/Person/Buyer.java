package Client.Models.Person;

import Client.Models.Cart;
import Client.Models.Product;

import java.util.ArrayList;

public class Buyer extends Person {
    Cart cart = new Cart(this);

    public Cart getCart() {
        return cart;
    }
    public void decreaseCredit(int money) throws Exception {
        if(money<getCredit())
            setCredit(getCredit()-money);
        else
            throw new Exception("there isn't enough credit");
    }
}
