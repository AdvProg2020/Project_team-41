package Client.View.Menus.UserSectionMenus.ManagerSectionMenus;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.example.App;
import java.io.IOException;

public class userButtonController {

    public AnchorPane usernameAnchorPane;
    public Button removeUserButton;
    public Button viewUserButton;
    public GridPane gridPane;

    public void viewUserClicked(MouseEvent mouseEvent) throws IOException {
        if (gridPane == null) {
            System.out.println("hi");
        }
        GridPane gridPane = (GridPane) viewUserButton.getParent();
        gridPane.getChildren().add(App.loadFXML("userSection/view user information"));
    }

    public void removeUserClicked(MouseEvent mouseEvent) {

    }
}
