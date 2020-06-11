package Client.View.Menus.ProductPage;

import Client.Models.Comment;
import Client.Models.CommentSituation;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javafx.scene.layout.VBox;
import org.example.App;

import java.io.IOException;

public class ViewComments {
    public Label titleLabel;
    public TextArea contentTextArea;
    public Label usernameLabel;
    public VBox commentsVBox;

    @FXML
    public void initialize(){
        for (Comment comment : ProductPageGeneralButtons.getTheProduct().getComments()) {
            if (comment.getCommentSituation().equals(CommentSituation.CONFIRMED))
            addEachComment(comment.getTitle() , comment.getContent() , comment.getPerson().getUserName());
        }
    }

    private void addEachComment(String title , String content , String username) {
        Parent root = null;
        try {
            root = App.loadFXML("ProductPage/EachComment");
        } catch (IOException e) {
            e.printStackTrace();
        }
        titleLabel.setText(title);
        contentTextArea.setText(content);
        usernameLabel.setText(username);
        //TODO show hasHeBought (text or image)
        commentsVBox.getChildren().add(root);
    }
}
