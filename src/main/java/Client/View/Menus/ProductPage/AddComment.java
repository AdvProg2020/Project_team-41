package Client.View.Menus.ProductPage;

import Client.Controller.ProductController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Comment;
import Client.Models.CommentSituation;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class AddComment {
    public TextField titleTextField;
    public TextArea contentTextArea;
    public Label commentResultLabel;

    public void addComment(MouseEvent mouseEvent) {
        try {
            ProductController.addComment(titleTextField.getText() , contentTextArea.getText() , ProductPageGeneralButtons.getTheProduct());
            commentResultLabel.setText("Thanks for your comment!");
        }
        catch(NullPointerException e){
            commentResultLabel.setText(e.getMessage());
        }
        catch (Exception e) {
           commentResultLabel.setText("Please fill all fields!");
        }

      }
}
