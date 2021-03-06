package Client.View.Menus.ProductPage;

import Client.Controller.ProductController;
import Client.View.Menus.MessageTypeShow;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import static Client.View.Menus.ProductPage.ProductPageGeneralButtons.showMessage;


public class AddComment {
    public TextField titleTextField;
    public TextArea contentTextArea;
    public Text commentResultLabel;

    public void addComment(MouseEvent mouseEvent) {
        try {
            ProductController.addComment(titleTextField.getText() , contentTextArea.getText() , ProductPageGeneralButtons.getTheProduct());
            showMessage(commentResultLabel , MessageTypeShow.SUCCESS , "Thanks for your comment!");
        }
        catch(Exception e){
           showMessage(commentResultLabel , MessageTypeShow.ERROR , e.getMessage());
        }
//        catch (Exception e) {
//            showMessage(commentResultLabel , MessageTypeShow.ERROR , "Please fill all fields!");
//        }

      }
}
