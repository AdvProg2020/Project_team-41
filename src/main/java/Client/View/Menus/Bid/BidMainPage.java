package Client.View.Menus.Bid;

import Client.View.Menus.Menu;
import javafx.event.ActionEvent;
import org.example.App;
import java.io.IOException;

public class BidMainPage extends Menu {


    public void back(ActionEvent actionEvent) throws IOException {
        App.setRoot("UserSection/buyerSection/buyer section");
    }
}
