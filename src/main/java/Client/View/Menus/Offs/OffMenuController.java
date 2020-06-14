package Client.View.Menus.Offs;

import Client.Controller.SortController;
import Client.Models.OffProductsToShow;
import Client.Models.Product;
import Server.Controller.TimeControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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


}
