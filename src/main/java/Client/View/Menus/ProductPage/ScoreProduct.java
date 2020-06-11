package Client.View.Menus.ProductPage;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Product;
import Client.Models.Score;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ScoreProduct {
    public TextField scoreTextField;
    public Label scoreResult;


    public void scoreTheProduct() {
        try {
            int score = Integer.parseInt(scoreTextField.getText());
            Score scoreObj = new Score(UserSectionController.getLoggedInPerson(), score, ProductPageGeneralButtons.getTheProduct());
            for (Product tradedProduct : UserSectionController.getLoggedInPerson().getAllProductsHeTraded()) {
                if (ProductPageGeneralButtons.getTheProduct().equals(tradedProduct)) {
                    ProductPageGeneralButtons.getTheProduct().getScores().add(scoreObj);
                    scoreResult.setText("Thanks, we got the score");
                }
            }
            scoreResult.setText("Oh sorry, only buyers can rate");
        } catch (Exception e) {
            scoreResult.setText("invalid score!");
        }
    }
}
