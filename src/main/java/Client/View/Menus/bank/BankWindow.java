package Client.View.Menus.bank;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static org.example.App.loadFXML;

public class BankWindow {
    public static Scene scene;
    public static Stage window;

    public static void openBank() throws IOException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("THE BANK");
        window.setHeight(500.0);
        window.setWidth(887.0);

        scene = new Scene(loadFXML("bank/bankLoginMenu"));
        window.setScene(scene);
        window.showAndWait();
    }
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
}
