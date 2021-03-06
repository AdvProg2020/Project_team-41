package Client.View.Menus.AllProductsPage;

import Client.Controller.FilterController;
import Client.Controller.SortController;
import Client.Controller.UserSectionController.SellerController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Category;
import Client.Models.Person.Buyer;
import Client.Models.Person.Manager;
import Client.Models.Person.Seller;
import Client.Models.Product;
import Client.Models.ProductToShowInAllProducts;
import Client.View.Menus.Menu;
import Client.View.Menus.Offs.FilterRange;
import Client.View.Menus.Offs.FilterValue;
import Client.View.Menus.UserSectionMenus.BuyerSectionMenus.BuyerSectionMenu;
import Client.View.Menus.UserSectionMenus.ManagerSectionMenus.ManagerSectionMenu;
import Client.View.Menus.UserSectionMenus.SellerSectionMenu.SellerSectionMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.example.App;
import org.example.Music;
import org.example.SimpleAudioPlayer;

import java.io.IOException;

public class allProductsMenu extends Menu {
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
    public Label filterInfoLabel;
    public ChoiceBox<String> allCategories;
    public ChoiceBox<String> specialFeaturesChoice;
    public Button loginLogout;
    public VBox vBox;
    public BorderPane mainBorderPane;
    public AnchorPane categoryAnchorPane;

    public void initialize() {
        SimpleAudioPlayer.getInstance().playMusic(Music.All_Products);

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

        SortVariables.getItems().addAll("product name", "company name", "price", "seller username", "score", "view");
        SortVariables.setValue("view");
//        specialFeatureAnchorPane.setVisible(false);
        try {
            for (Category category : SellerController.getInstance().getCategories()) {
                allCategories.getItems().addAll(category.getName());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (UserSectionController.getLoggedInPerson() == null) {
            loginLogout.setText("Register/Login");
        } else {
            loginLogout.setText("Logout");
        }

    }

    public static ObservableList<ProductToShowInAllProducts> getProducts() {
        ObservableList<ProductToShowInAllProducts> products = FXCollections.observableArrayList();

        for (Product product : SortController.getInstance().getSortedProducts(false)) {
            products.add(new ProductToShowInAllProducts(product.getProductId(), product.getName(), product.getPrice(),
                    product.getCategory().getName(), product.getCompanyName(), product.getSeller().getUserName(),
                    product.getViews(), product.calculateAverageScore(), product.getQuantity() ,new Button(product.getProductId())));
        }
        return products;
    }

    public void sort() {
        SortController.getInstance().setSortFeature(SortVariables.getValue());
        tableView.setItems(getProducts());

    }

    public void filterProductName() {
        if (enableNameFilter.isSelected()) {
            if (productName.getText().length() == 0) {
                filterInfoLabel.setText("Please first enter information!");
                filterInfoLabel.setTextFill(Color.RED);
                enableNameFilter.setSelected(false);
            } else {
                FilterController.getInstance().setName(productName.getText());
                tableView.setItems(getProducts());
            }
        } else {
            try {
                FilterController.getInstance().disableFilter("product name");
                tableView.setItems(getProducts());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void filterSeller() {
        if (enableSellerFilter.isSelected()) {
            if (sellerUsername.getText().length() == 0) {
                filterInfoLabel.setText("Please first enter information!");
                filterInfoLabel.setTextFill(Color.RED);
                enableSellerFilter.setSelected(false);
            } else {
                try {
                    FilterController.getInstance().setSellerUserName(sellerUsername.getText());
                    tableView.setItems(getProducts());
                } catch (Exception e) {
                    filterInfoLabel.setText(e.getMessage());
                    filterInfoLabel.setTextFill(Color.RED);
                    enableSellerFilter.setSelected(false);
                }

            }
        } else {
            try {
                FilterController.getInstance().disableFilter("seller");
                tableView.setItems(getProducts());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void filterCompanyName() {
        if (enableCompanyFilter.isSelected()) {
            if (companyName.getText().length() == 0) {
                filterInfoLabel.setText("Please first enter information!");
                filterInfoLabel.setTextFill(Color.RED);
                enableCompanyFilter.setSelected(false);
            } else {
                FilterController.getInstance().setCompanyName(companyName.getText());
                tableView.setItems(getProducts());
            }
        } else {
            try {
                FilterController.getInstance().disableFilter("company name");
                tableView.setItems(getProducts());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void filterPriceRange() {
        if (enablePriceRangeFilter.isSelected()) {
            if (!minPrice.getText().matches("\\d+") || !maxPrice.getText().matches("\\d+")) {
                filterInfoLabel.setText("Please first enter information!");
                filterInfoLabel.setTextFill(Color.RED);
                enablePriceRangeFilter.setSelected(false);
            } else {
                FilterController.getInstance().setPriceMinMax(new Pair<>(Integer.parseInt(minPrice.getText()), Integer.parseInt(maxPrice.getText())));
                tableView.setItems(getProducts());
            }

        } else {
            try {
                FilterController.getInstance().disableFilter("rangePrice");
                tableView.setItems(getProducts());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void filterPriceDefinite() {
        if (enablePriceDefiniteFilter.isSelected()) {
            if (!definitePrice.getText().matches("\\d+")) {
                filterInfoLabel.setText("Please first enter information!");
                filterInfoLabel.setTextFill(Color.RED);
                enablePriceDefiniteFilter.setSelected(false);
            } else {
                try {
                    FilterController.getInstance().setDefinitePrice(Integer.parseInt(definitePrice.getText()));
                    tableView.setItems(getProducts());

                } catch (Exception e) {
                    filterInfoLabel.setText(e.getMessage());
                    filterInfoLabel.setTextFill(Color.RED);
                    enablePriceDefiniteFilter.setSelected(false);
                }
            }

        } else {
            try {
                FilterController.getInstance().disableFilter("definitePrice");
                tableView.setItems(getProducts());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void filterCategory() {

        if (enableCategoryFilter.isSelected()) {
            if (allCategories.getValue() == null) {
                filterInfoLabel.setText("Please first enter information!");
                filterInfoLabel.setTextFill(Color.RED);
                enableCategoryFilter.setSelected(false);
            } else {
                try {
                    FilterController.getInstance().setFilterCategory(allCategories.getValue());
                    tableView.setItems(getProducts());
                    filterSpecialFeature();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } else {
            try {
                FilterController.getInstance().disableFilter("category name");
                vBox.getChildren().remove(vBox.getChildren().indexOf(categoryAnchorPane) + 1, vBox.getChildren().size());
                tableView.setItems(getProducts());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void filterSpecialFeature() throws IOException {
        FilterValue.offMenu = false;
        FilterRange.offMenu = false;
        for (String specialFeature : FilterController.getInstance().getFilterCategory().getSpecialFeatures()) {
            try {
                if (FilterController.getInstance().isTheFeatureNumeric(specialFeature)) {
                    FilterRange.filterName = specialFeature;
                    vBox.getChildren().add(App.loadFXML("filterRange"));

                } else {
                    FilterValue.filterName = specialFeature;
                    vBox.getChildren().add(App.loadFXML("filterValue"));
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


    }

    public void filterExistence() {
        if (enableExistenceFilter.isSelected()) {
            if (!existenceYes.isSelected() && !existenceNo.isSelected()) {
                filterInfoLabel.setText("Please first enter information!");
                filterInfoLabel.setTextFill(Color.RED);
                enableExistenceFilter.setSelected(false);
            } else {
                if (existenceYes.isSelected()) {
                    FilterController.getInstance().setExistence(1);
                } else {
                    FilterController.getInstance().setExistence(0);

                }
                tableView.setItems(getProducts());

            }
        } else {
            try {
                FilterController.getInstance().disableFilter("existence");
                tableView.setItems(getProducts());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void noExistence() {
        if (existenceYes.isSelected()) {
            existenceYes.setSelected(false);
        }

    }

    public void yesExistence() {
        if (existenceNo.isSelected()) {
            existenceNo.setSelected(false);
        }
    }

    public void back() throws IOException {
        FilterController.resetFilterController();
        App.setRoot("mainMenu");
    }

    public void registerOrLogin() throws IOException {
        if (UserSectionController.getLoggedInPerson() == null) {
            login("AllProducts");
        } else {
            logout("AllProducts");
        }
    }

    public void userSection() throws IOException {
        if (UserSectionController.getLoggedInPerson() == null) {
            login("ProductPage/ProductPageGeneral");
        } else {
            if (UserSectionController.getLoggedInPerson() instanceof Manager) {
                ManagerSectionMenu.parentFxmlAddress = "AllProducts";
                App.setRoot("userSection/managerSection/manager section");
            } else if (UserSectionController.getLoggedInPerson() instanceof Seller) {
                SellerSectionMenu.parentFxmlAddress = "AllProducts";
                App.setRoot("userSection/sellerSection/seller section");

            } else if (UserSectionController.getLoggedInPerson() instanceof Buyer) {
                BuyerSectionMenu.parentFxmlAddress = "AllProducts";
                App.setRoot("userSection/buyerSection/buyer section");


            }
        }
    }
}
