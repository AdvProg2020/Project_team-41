package Client.View.Menus.Bid;

import javafx.scene.input.MouseEvent;
import org.example.App;

import java.io.IOException;

public class BidMainPage {
    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        App.setRoot("UserSection/buyerSection/buyer section");
    }
}
