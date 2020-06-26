package Client.View.Menus.ProductPage;

import Client.Controller.FilterController;
import Client.Controller.SortController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Person.Buyer;
import Client.Models.Person.Manager;
import Client.Models.Person.Seller;
import Client.Models.Product;
import Client.Models.ProductToShowInAllProducts;
import Client.View.Menus.Menu;
import Client.View.Menus.UserSectionMenus.BuyerSectionMenus.BuyerSectionMenu;
import Client.View.Menus.UserSectionMenus.ManagerSectionMenus.ManagerSectionMenu;
import Client.View.Menus.UserSectionMenus.SellerSectionMenu.SellerSectionMenu;
import Server.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.App;

import java.io.IOException;
import java.util.ArrayList;

public class SimilarProducts extends Menu {
    public TableView<ProductToShowInAllProducts> tableView;
    public TableColumn<ProductToShowInAllProducts, Button> button;
    public TableColumn<ProductToShowInAllProducts, String> name;
    public TableColumn<ProductToShowInAllProducts, Integer> price;
    public TableColumn<ProductToShowInAllProducts, Integer> score;
    public TableColumn<ProductToShowInAllProducts, String> category;
    public TableColumn<ProductToShowInAllProducts, String> company;
    public TableColumn<ProductToShowInAllProducts, String> seller;
    public TableColumn<ProductToShowInAllProducts, Integer> view;
    public TableColumn<ProductToShowInAllProducts, Integer> quantity;

    public void initialize() throws Exception {
        tableView.setItems(getProducts());
//          filterInfoLabel.setText("");
        button.setCellValueFactory(new PropertyValueFactory<>("button"));
        name.setCellValueFactory(new PropertyValueFactory<>("productName"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        view.setCellValueFactory(new PropertyValueFactory<>("view"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        seller.setCellValueFactory(new PropertyValueFactory<>("seller"));
        company.setCellValueFactory(new PropertyValueFactory<>("company"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    public static ObservableList<ProductToShowInAllProducts> getProducts() throws Exception {
        ObservableList<ProductToShowInAllProducts> products = FXCollections.observableArrayList();

        FilterController.getInstance().setFilterCategory(ProductPageGeneralButtons.getTheProduct().getCategory().getName());
        for (Product product : SortController.getInstance().getSortedProducts(false)) {
            if(product != ProductPageGeneralButtons.getTheProduct()){
            products.add(new ProductToShowInAllProducts(product.getProductId(), product.getName(), product.getPrice(),
                    product.getCategory().getName(), product.getCompanyName(), product.getSeller().getUserName(),
                    product.getViews(), product.calculateAverageScore(), product.getQuantity(), new Button(product.getProductId())));
        }}
        products.remove(ProductPageGeneralButtons.getTheProduct());
        FilterController.resetFilterController();
        return products;
    }


}