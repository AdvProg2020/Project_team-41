package Client.View.Menus.Offs;

import Client.Controller.FilterController;
import Client.Controller.SortController;
import Client.Models.OffProductsToShow;
import Client.Models.Product;
import Client.View.Menus.AllProductsPage.allProductsMenu;
import Client.View.Menus.NodeFinder;
import Server.Controller.TimeControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Pair;

import java.io.FileNotFoundException;

public class FilterRange {
    public static String filterName;
    public CheckBox enablePriceDefiniteFilter;
    public CheckBox enablePriceRangeFilter;
    public Label information;
    public TextField minPrice;
    public TextField maxPrice;
    public TextField definitePrice;
    public AnchorPane anchorPane;
    public static boolean offMenu;

    public void initialize() {
        information.setText(filterName);
    }

    public void filterRange(ActionEvent actionEvent) {
        if (enablePriceRangeFilter.isSelected()) {
            if (minPrice.getText().length() == 0 || maxPrice.getText().length() == 0) {
                enablePriceRangeFilter.setSelected(false);
            } else {
                FilterController.getInstance().setRangeIntFeatures(information.getText(), new Pair<>(
                        Integer.parseInt(minPrice.getText()), Integer.parseInt(maxPrice.getText())));

                BorderPane borderPane = (BorderPane) NodeFinder.getParentById(anchorPane, "mainBorderPane");
                TableView tableView = (TableView) NodeFinder.getChildById(borderPane, "tableView");
                if (offMenu)
                    tableView.setItems(getProducts());
                else
                    tableView.setItems(allProductsMenu.getProducts());
            }
        } else {
            try {
                FilterController.getInstance().disableSpecialFeature(information.getText(), false);
                BorderPane borderPane = (BorderPane) NodeFinder.getParentById(anchorPane, "mainBorderPane");
                TableView tableView = (TableView) NodeFinder.getChildById(borderPane, "tableView");
                if (offMenu)
                    tableView.setItems(getProducts());
                else
                    tableView.setItems(allProductsMenu.getProducts());
            } catch (ClassNotFoundException | FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void filterDefinite(ActionEvent actionEvent) {
        if (enablePriceDefiniteFilter.isSelected()) {
            if (definitePrice.getText().length() == 0) {
                enablePriceDefiniteFilter.setSelected(false);
            } else {
                FilterController.getInstance().setDefiniteIntFeatures(information.getText(), Integer.parseInt(definitePrice.getText()));
                BorderPane borderPane = (BorderPane) NodeFinder.getParentById(anchorPane, "mainBorderPane");
                TableView tableView = (TableView) NodeFinder.getChildById(borderPane, "tableView");
                if (offMenu)
                    tableView.setItems(getProducts());
                else
                    tableView.setItems(allProductsMenu.getProducts());
            }
        } else {
            try {
                FilterController.getInstance().disableSpecialFeature(information.getText(), true);
                BorderPane borderPane = (BorderPane) NodeFinder.getParentById(anchorPane, "mainBorderPane");
                TableView tableView = (TableView) NodeFinder.getChildById(borderPane, "tableView");
                if (offMenu)
                    tableView.setItems(getProducts());
                else
                    tableView.setItems(allProductsMenu.getProducts());
            } catch (ClassNotFoundException | FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public ObservableList<OffProductsToShow> getProducts() {
        ObservableList<OffProductsToShow> products = FXCollections.observableArrayList();
        for (Product product : SortController.getInstance().getSortedProducts(true)) {
            products.add(new OffProductsToShow(product.getProductId(), product.getName(), product.getPrice(),
                    (product.getPrice() * (100 - product.getOff().getAmountOfDiscount())) / 100,
                    TimeControl.getJalaliDateAndTimeForPrint(product.getOff().getStartDate()),
                    TimeControl.getJalaliDateAndTimeForPrint(product.getOff().getEndDate()), product.calculateAverageScore(), new Button(product.getProductId())));
        }
        return products;
    }
}
