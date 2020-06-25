package Client.View.Menus.UserSectionMenus.BuyerSectionMenus.viewDiscountCodesMenu;

import Client.Controller.UserSectionController.ManagerController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class ViewDiscountCodeController {
    static String code;
    public AnchorPane discountCodeInformation;
    public TextField discountCodeTextField;
    public TextField startDateTextField;
    public TextField endDateTextField;
    public TextField discountPercentageTextField;
    public TextField maximumDiscountTextField;
    public TextField discountRepeatsForEachUserTextField;
    public TextField peopleWhoCanUseItTextField;

    @FXML
    public void initialize(){
        StringBuilder peopleWhoCanUseIt = new StringBuilder();
        ArrayList<String> discountCodeInfo;
        try {
            discountCodeInfo = ManagerController.getInstance().viewDiscountCode(code);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        String discountCode = discountCodeInfo.get(0).split(":")[1].trim();
        String startDate = dateFormatConverter(discountCodeInfo.get(1).split(":",2)[1].trim());
        String endDate = dateFormatConverter(discountCodeInfo.get(2).split(":",2)[1].trim());
        String discountPercentage = discountCodeInfo.get(3).split(":")[1].trim();
        String maximumDiscount = discountCodeInfo.get(4).split(":")[1].trim();
        String discountRepeatsForEachUser = discountCodeInfo.get(5).split(":")[1].trim();
        for (int i = 7; i < discountCodeInfo.size(); i++) {
            peopleWhoCanUseIt.append(",").append(discountCodeInfo.get(i));
        }
        discountCodeTextField.setText(discountCode);
        startDateTextField.setText(startDate);
        endDateTextField.setText(endDate);
        discountPercentageTextField.setText(discountPercentage);
        maximumDiscountTextField.setText(maximumDiscount);
        discountRepeatsForEachUserTextField.setText(discountRepeatsForEachUser);
        peopleWhoCanUseItTextField.setText(peopleWhoCanUseIt.toString().substring(1).trim());
    }
    private String dateFormatConverter(String dateFormat){
        String correctDataFormatForEditing;
        String correctDate;
        String[] date;
        date = dateFormat.split("\\s+")[0].split("-");
        correctDate = date[2]+"/"+date[1]+"/"+date[0];
        correctDataFormatForEditing = correctDate + "," + dateFormat.split("\\s+")[1];
        return correctDataFormatForEditing;
    }

}
