package Client.View.Menus.Offs;

import Client.Controller.FilterController;
import Client.Controller.SortController;
import Client.Controller.UserSectionController.SellerController;
import Client.Models.Category;
import Client.Models.OffProductsToShow;
import Client.Models.Product;
import Server.Controller.TimeControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class OffMenuController {

    public TableView<OffProductsToShow> tableView;
    public TableColumn<OffProductsToShow, Button > button;
    public TableColumn<OffProductsToShow, String > name;
    public TableColumn<OffProductsToShow, Integer> price;
    public TableColumn<OffProductsToShow, Integer> priceWithDiscount;
    public TableColumn<OffProductsToShow, String > startDate;
    public TableColumn<OffProductsToShow, String > endDate;
    public TableColumn<OffProductsToShow, Integer> score;
    public ChoiceBox<String> SortVariables;
    public CheckBox enableNameFilter;
    public TextField productName;
    public CheckBox enableCompanyFilter;
    public TextField companyName;
    public CheckBox enableSellerFilter;
    public TextField sellerUsername;
    public TextField minPrice;
    public TextField maxPrice;
    public CheckBox enablePriceRangeFilter;
    public TextField definitePrice;
    public CheckBox enablePriceDefiniteFilter;
    public CheckBox enableExistenceFilter;
    public CheckBox existenceNo;
    public CheckBox existenceYes;
    public CheckBox enableCategoryFilter;
    public AnchorPane specialFeatureAnchorPane;
    public Label filterInfoLabel;
    public ChoiceBox<String> allCategories;
    public ChoiceBox<String> specialFeaturesChoice;

    public void initialize(){
        tableView.setItems(getProducts());

        button.setCellValueFactory(new PropertyValueFactory<>("button"));
        name.setCellValueFactory(new PropertyValueFactory<>("productName"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceWithDiscount.setCellValueFactory(new PropertyValueFactory<>("priceWithDiscount"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));


        SortVariables.getItems().addAll("product name","company name","price","seller username","score","view");
        SortVariables.setValue("view");
//        specialFeatureAnchorPane.setVisible(false);
        for (Category category : SellerController.getInstance().getCategories()) {
            allCategories.getItems().addAll(category.getName());

        }

    }

    public ObservableList<OffProductsToShow> getProducts(){
        ObservableList<OffProductsToShow> products= FXCollections.observableArrayList();
        for (Product product : SortController.getInstance().getSortedProducts(true)) {
            products.add(new OffProductsToShow(product.getProductId(),product.getName(),product.getPrice(),
                    (product.getPrice()*(100-product.getOff().getAmountOfDiscount()))/100,
                    TimeControl.getJalaliDateAndTimeForPrint(product.getOff().getStartDate()),
                    TimeControl.getJalaliDateAndTimeForPrint(product.getOff().getEndDate()),product.calculateAverageScore(),new Button(product.getProductId())));
        }
        return products;
    }
    public void sort(){
        SortController.getInstance().setSortFeature(SortVariables.getValue());
        tableView.setItems(getProducts());

    }
    public void filterProductName(){
        if(enableNameFilter.isSelected()) {
            if (productName.getText().length() == 0) {
                filterInfoLabel.setText("Please first enter information!");
                filterInfoLabel.setTextFill(Color.RED);
                enableNameFilter.setSelected(false);
            } else {
                FilterController.getInstance().setName(productName.getText());
                tableView.setItems(getProducts());
            }
        }else{
            try {
                FilterController.getInstance().disableFilter("product name");
                tableView.setItems(getProducts());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void filterSeller(){
        if(enableSellerFilter.isSelected()){
            if(sellerUsername.getText().length()==0){
                filterInfoLabel.setText("Please first enter information!");
                filterInfoLabel.setTextFill(Color.RED);
                enableSellerFilter.setSelected(false);
            }else {
                try {
                    FilterController.getInstance().setSellerUserName(sellerUsername.getText());
                    tableView.setItems(getProducts());
                } catch (Exception e) {
                    filterInfoLabel.setText(e.getMessage());
                    filterInfoLabel.setTextFill(Color.RED);
                    enableSellerFilter.setSelected(false);
                }

            }
        }else{
            try {
                FilterController.getInstance().disableFilter("seller");
                tableView.setItems(getProducts());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void filterCompanyName(){
        if(enableCompanyFilter.isSelected()){
            if(companyName.getText().length()==0){
                filterInfoLabel.setText("Please first enter information!");
                filterInfoLabel.setTextFill(Color.RED);
                enableCompanyFilter.setSelected(false);
            }else{
                FilterController.getInstance().setCompanyName(companyName.getText());
                tableView.setItems(getProducts());
            }
        }else{
            try {
                FilterController.getInstance().disableFilter("company name");
                tableView.setItems(getProducts());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void filterPriceRange(){

    }
    public void filterPriceDefinite(){

    }
    public void filterCategory(){

        if(enableCategoryFilter.isSelected()) {
            if (allCategories.getValue() == null) {
                filterInfoLabel.setText("Please first enter information!");
                filterInfoLabel.setTextFill(Color.RED);
                enableCategoryFilter.setSelected(false);
            }else{
                try {
                    FilterController.getInstance().setFilterCategory(allCategories.getValue());
                    tableView.setItems(getProducts());
                    specialFeaturesChoice.getItems().addAll(FilterController.getInstance().getFilterCategory().getSpecialFeatures());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }else{
            try {
                FilterController.getInstance().disableFilter("category name");
                tableView.setItems(getProducts());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
    public void filterSpecialFeature(){

    }
    public void filterExistence(){
        if(enableExistenceFilter.isSelected()){
            if(!existenceYes.isSelected()&&!existenceNo.isSelected()){
                filterInfoLabel.setText("Please first enter information!");
                filterInfoLabel.setTextFill(Color.RED);
                enableExistenceFilter.setSelected(false);
            }else{
                if(existenceYes.isSelected()){
                    FilterController.getInstance().setExistence(1);
                }else{
                    FilterController.getInstance().setExistence(0);

                }
                tableView.setItems(getProducts());

            }
        }else{
            try {
                FilterController.getInstance().disableFilter("existence");
                tableView.setItems(getProducts());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public void noExistence() {
        if(existenceYes.isSelected()){
            existenceYes.setSelected(false);
        }

    }

    public void yesExistence() {
        if(existenceNo.isSelected()){
            existenceNo.setSelected(false);
        }
    }
}
