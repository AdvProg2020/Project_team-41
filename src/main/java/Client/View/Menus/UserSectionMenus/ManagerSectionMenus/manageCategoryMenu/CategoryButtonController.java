package Client.View.Menus.UserSectionMenus.ManagerSectionMenus.manageCategoryMenu;

import Client.Controller.UserSectionController.ManagerController;
import Client.View.Menus.MessageTypeShow;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CategoryButtonController {
    public AnchorPane categoryNameAnchorPane;
    public GridPane gridPane;
    public TextField categoryNameTextField;
    public Button removeCategoryButton;
    public Button editCategoryButton;
    ArrayList<String> categoriesShown = new ArrayList<>();

    public void editCategoryClicked(MouseEvent mouseEvent) throws IOException {
        if(categoriesShown.contains(categoryNameTextField.getText())){
            categoriesShown.remove(categoryNameTextField.getText());
            finishEditingCategory();
        }
        else{
            categoriesShown.add(categoryNameTextField.getText());
            editCategory();

        }
    }


    public void removeCategoryClicked(MouseEvent mouseEvent) throws Exception {

        try{
            ManagerController.getInstance().removeCategory(categoryNameTextField.getText());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        if(categoriesShown.contains(categoryNameTextField.getText()))
            vBox.getChildren().remove(getIndexOfCategory()+1);
        vBox.getChildren().remove(getIndexOfCategory());
        AnchorPane anchorPane = (AnchorPane) vBox.getParent().getParent().getParent().getParent();
        Text text = (Text) anchorPane.getChildren().get(1);
        showMessage(text, MessageTypeShow.SUCCESS,"successfully removed category");


    }
    private void editCategory() throws IOException {
        editCategoryController.globalManageCategoryMenu = (AnchorPane) categoryNameAnchorPane.getParent().getParent().getParent().getParent().getParent();
        editCategoryController.globalCategoryName = categoryNameTextField.getText();
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        vBox.getChildren().add(getIndexOfCategory() + 1, App.loadFXML("userSection/managerSection/manageCategoriesMenu/edit category"));
        AnchorPane anchorPane = (AnchorPane) vBox.getChildren().get(getIndexOfCategory() + 1);
        VBox vBox1 = (VBox) anchorPane.getChildren().get(0);
        double anchorPaneHeight = anchorPane.getPrefHeight();
        double vBoxHeight = vBox1.getPrefHeight();
        anchorPane.setPrefHeight(0);
        anchorPane.setMaxHeight(0);
        vBox1.setPrefHeight(0);
        vBox1.setMaxHeight(0);

        Timer animTimer = new Timer();
        animTimer.scheduleAtFixedRate(new TimerTask() {
            int i=0;

            @Override
            public void run() {
                if (i<100) {
                    anchorPane.setPrefHeight(anchorPane.getPrefHeight()+anchorPaneHeight/100);
                    anchorPane.setMaxHeight(anchorPane.getMaxHeight()+anchorPaneHeight/100);
                    vBox1.setPrefHeight(vBox1.getPrefHeight()+vBoxHeight/100);
                    vBox1.setMaxHeight(vBox1.getMaxHeight()+vBoxHeight/100);

                } else {
                    this.cancel();
                }
                i++;
            }

        }, 0, 5);

    }
    private void finishEditingCategory() {
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        AnchorPane headCategoryMenu = (AnchorPane) vBox.getParent().getParent().getParent().getParent();
        Text informationText = (Text) headCategoryMenu.getChildren().get(1);

        AnchorPane editCategoryAnchorPane = (AnchorPane)vBox.getChildren().get(getIndexOfCategory()+1);
        VBox editCategoryVBox = (VBox) editCategoryAnchorPane.getChildren().get(0);
        GridPane editCategoryGridPane = (GridPane) editCategoryVBox.getChildren().get(0);
        TextField editCategoryNameTextField = (TextField) editCategoryGridPane.getChildren().get(2);
        TextField editCategorySpecialFeaturesTextField = (TextField) editCategoryGridPane.getChildren().get(3);
        String[] previousFields = getPreviousFields(categoryNameTextField.getText());
        boolean errorWhileEditing = false;
        StringBuilder errorBuilder = new StringBuilder();
        try {
            if(!previousFields[0].equals(editCategoryNameTextField.getText())) {
                ManagerController.getInstance().editCategoryName(categoryNameTextField.getText(), editCategoryNameTextField.getText());
                categoryNameTextField.setText(editCategoryNameTextField.getText());
                showMessage(informationText, MessageTypeShow.SUCCESS, "fields edited successfully");
            }
        } catch (Exception e) {
            errorBuilder.append("-").append(e.getMessage());
            showMessage(informationText, MessageTypeShow.ERROR, e.getMessage());
            errorWhileEditing = true;
        }
        try {
            if(!previousFields[1].equals(editCategorySpecialFeaturesTextField.getText())) {
                ManagerController.getInstance().editCategorySpecialFeatures(categoryNameTextField.getText(), editCategorySpecialFeaturesTextField.getText());
                showMessage(informationText, MessageTypeShow.SUCCESS, "fields edited successfully");
            }
        } catch (Exception e) {
            errorBuilder.append("-").append(e.getMessage());
            errorWhileEditing = true;
        }
        if(errorWhileEditing)
            showMessage(informationText, MessageTypeShow.ERROR, errorBuilder.toString().substring(1));



        vBox.getChildren().remove(getIndexOfCategory()+1);

    }
    private int getIndexOfCategory(){
        String categoryName = categoryNameTextField.getText();
        editCategoryController.globalManageCategoryMenu = (AnchorPane) categoryNameAnchorPane.getParent().getParent().getParent().getParent().getParent();
        editCategoryController.globalCategoryName = categoryName;
        VBox vBox = (VBox) gridPane.getParent().getParent().getParent();
        for (int i = 0; i < vBox.getChildren().size(); i++) {
            AnchorPane anchorPane = (AnchorPane) vBox.getChildren().get(i);
            VBox innerVBox = (VBox) anchorPane.getChildren().get(0);
            GridPane gridPane = (GridPane) innerVBox.getChildren().get(0);
            if (gridPane.getChildren().get(0) instanceof Text)
                continue;
            TextField textField = (TextField) gridPane.getChildren().get(0);
            if (textField.getText().equals(categoryName)) {
                return i;
            }
        }
        return -2;
    }
    private void showMessage(Text text, MessageTypeShow messageTypeShow, String message){
        text.setFill(messageTypeShow.getLinearGradient());
        text.setText(message);

    }
    private String[] getPreviousFields(String categoryName){
        String[] previousFields = new String[2];
        StringBuilder specialFeaturesString = new StringBuilder();
        ArrayList<String> specialFeatures = null;
        try {
            specialFeatures = ManagerController.getInstance().getCategorySpecialFeatures(categoryName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String specialFeature : specialFeatures) {
            specialFeaturesString.append(",").append(specialFeature);
        }
        if (!specialFeaturesString.toString().isEmpty()) {
            specialFeaturesString = new StringBuilder(specialFeaturesString.substring(1));
        }
        previousFields[0] = categoryName;
        previousFields[1] = specialFeaturesString.toString();
        return previousFields;
    }

}
