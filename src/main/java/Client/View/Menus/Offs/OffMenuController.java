package Client.View.Menus.Offs;

import Client.Controller.SortController;
import Client.Models.OffProductsToShow;
import Client.Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class OffMenuController {

    public TableView<OffProductsToShow> tableView;
    public TableColumn<OffProductsToShow, String > productId;
    public TableColumn<OffProductsToShow, String > name;
    public TableColumn<OffProductsToShow, Integer> price;
    public TableColumn<OffProductsToShow, Integer> priceWithDiscount;
    public TableColumn<OffProductsToShow, Date> startDate;
    public TableColumn<OffProductsToShow, Date> endDate;
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

        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        name.setCellValueFactory(new PropertyValueFactory<>("productName"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceWithDiscount.setCellValueFactory(new PropertyValueFactory<>("priceWithDiscount"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        SortVariables.getItems().addAll("product name","company name","price","seller username","score","view");
        SortVariables.setValue("view");
    }

    public ObservableList<OffProductsToShow> getProducts(){
        ObservableList<OffProductsToShow> products= FXCollections.observableArrayList();
        for (Product product : SortController.getInstance().getSortedProducts(true)) {
            products.add(new OffProductsToShow(product.getProductId(),product.getName(),product.getPrice(),
                    (product.getPrice()*(100-product.getOff().getAmountOfDiscount()))/100,
                    product.getOff().getStartDate(),product.getOff().getEndDate()));
        }
        return products;
    }
}
