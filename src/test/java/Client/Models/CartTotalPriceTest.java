package Client.Models;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTotalPriceTest {

    @Test
    void totalPrice() {
        Cart cart = new Cart(null); // MyClass is tested
        // assert statements
        assertEquals(0, cart.totalPrice(), "totalPrice should be 0");
    }
}