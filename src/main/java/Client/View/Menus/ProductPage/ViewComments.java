package Client.View.Menus.ProductPage;

import Client.Models.Comment;
import Client.Models.CommentSituation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.App;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ViewComments {
    public VBox commentsVBox;
    public Text commentsInfo;


    @FXML
    public void initialize(){

        if(ProductPageGeneralButtons.getTheProduct().getComments().size() == 0)
            commentsInfo.setText("No comments yet");
        for (Comment comment : ProductPageGeneralButtons.getTheProduct().getComments()) {
            if (comment.getCommentSituation().equals(CommentSituation.CONFIRMED)) {
                addEachComment(comment.getTitle(), comment.getContent(), comment.getPerson().getUserName() , comment.isHasHeBought());
            }
        }
    }

    private void addEachComment(String title , String content , String username , boolean bought) {
        Parent root = null;
        try {
            root = App.loadFXML("ProductPage/EachComment");
        } catch (IOException e) {
            e.printStackTrace();
        }

        AnchorPane anchorPane = (AnchorPane) root;
        Text titleText = (Text) anchorPane.getChildren().get(0);
        titleText.setText(title);
        TextArea contentTextArea = (TextArea) anchorPane.getChildren().get(1);
        contentTextArea.setText(content);
        TextField usernameTextField = (TextField) anchorPane.getChildren().get(3);
        usernameTextField.setText(username);
        if(bought){
        Label boughtLabel = (Label) anchorPane.getChildren().get(4);
        boughtLabel.setVisible(true);
        }

        commentsVBox.getChildren().add(root);
    }
}
