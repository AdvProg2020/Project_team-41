package Client.View.Menus.Offs;

import Client.Controller.FilterController;
import Client.Controller.SortController;
import Client.Models.OffProductsToShow;
import Client.Models.Product;
import Client.View.Menus.NodeFinder;
import Server.Controller.TimeControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class FilterValue {
    public static String filterName;
    public CheckBox enableFilter;
    public Label information;
    public TextField value;
    public AnchorPane anchorPane;

    public void initialize() {
        information.setText(filterName);
    }

    public void filterValue(ActionEvent actionEvent) {
        if (enableFilter.isSelected()) {
            if (value.getText().length() == 0) {
                enableFilter.setSelected(false);
            } else {
                FilterController.getInstance().getDefiniteStringFeatures().put(information.getText(), value.getText());
                BorderPane borderPane= (BorderPane) NodeFinder.getParentById(anchorPane,"mainBorderPane");
                TableView tableView=(TableView)NodeFinder.getChildById(borderPane,"tableView");
                tableView.setItems(getProducts());


            }
        } else {
            try {
                FilterController.getInstance().disableSpecialFeature(information.getText() , true);
                BorderPane borderPane= (BorderPane) NodeFinder.getParentById(anchorPane,"mainBorderPane");
                TableView tableView=(TableView)NodeFinder.getChildById(borderPane,"tableView");
                tableView.setItems(getProducts());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
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
