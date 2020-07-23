package Client.View.Menus.ProductPage;

import Client.Controller.Connector;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Message.Message;
import Client.Models.Message.MessageType;
import Client.Models.Person.Buyer;
import Client.Models.Product;
import Client.Models.Score;
import Client.View.Menus.MessageTypeShow;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import static Client.View.Menus.ProductPage.ProductPageGeneralButtons.showMessage;

public class ScoreProduct {
    public TextField scoreTextField;
    public Text scoreResult;


    public void scoreTheProduct() {
        try {
            int score = Integer.parseInt(scoreTextField.getText());
            if (UserSectionController.getLoggedInPerson() == null)
                showMessage(scoreResult, MessageTypeShow.ERROR, "please first log in");
            else {
                if (UserSectionController.getLoggedInPerson() instanceof Buyer) {
                    boolean error = true;
                    Score scoreObj = new Score(UserSectionController.getLoggedInPerson(), score, ProductPageGeneralButtons.getTheProduct());
                    for (Product tradedProduct : UserSectionController.getLoggedInPerson().getAllProductsHeTraded()) {
                        if (ProductPageGeneralButtons.getTheProduct().getProductId().equals(tradedProduct.getProductId())) {
                            Connector.getInstance().initializeMessage(new Message(new Object[]
                                    {UserSectionController.getLoggedInPerson().getUserName(), score, ProductPageGeneralButtons.getTheProduct().getProductId()}, MessageType.ADD_SCORE));
                            showMessage(scoreResult, MessageTypeShow.SUCCESS, "Thanks, we got the score");
                            error = false;
                        }
                    }
                    if (error)
                        showMessage(scoreResult, MessageTypeShow.ERROR, "Oh sorry, only those who has bought the product can rate it");
                } else {
                    showMessage(scoreResult, MessageTypeShow.ERROR, "Oh sorry, only those who has bought the product can rate it");

                }
            }

        } catch (Exception e) {
            showMessage(scoreResult, MessageTypeShow.ERROR, "invalid score!");

        }
    }
}
