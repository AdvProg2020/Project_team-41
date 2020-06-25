package Client.Models;

import Client.Controller.FilterController;
import Client.Controller.UserSectionController.ManagerController;
import Client.View.Menus.ProductPage.ProductPageGeneralButtons;
import javafx.scene.control.Button;
import org.example.App;

import java.util.Objects;

public class ProductToShowInAllProducts {
    private String productId;
    private String productName;
    private int price;
    private String category;
    private String company;
    private String seller;
    private int view;
    private int score;
    private int quantity;
    private Button button;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductToShowInAllProducts(String productId, String productName, int price, String category, String company, String seller, int view, int score,int quantity, Button button) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.company = company;
        this.seller = seller;
        this.view = view;
        this.score = score;
        this.quantity = quantity;
        this.button = button;
        button.setStyle("-fx-background-color:transparent");
        button.setOnAction(e ->{
            try {
                Product product = ManagerController.getInstance().getProductById(this.productId);
                ProductPageGeneralButtons.setTheProduct(product);
                product.setViews(product.getViews()+1);
                ProductPageGeneralButtons.parentFxmlAddress = "AllProducts";
                FilterController.resetFilterController();
                App.setRoot("ProductPage/ProductPageGeneral");
            } catch (Exception ioException) {
                ioException.printStackTrace();
            }
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductToShowInAllProducts that = (ProductToShowInAllProducts) o;
        return price == that.price &&
                view == that.view &&
                score == that.score &&
                quantity == that.quantity &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(category, that.category) &&
                Objects.equals(company, that.company) &&
                Objects.equals(seller, that.seller) &&
                Objects.equals(button, that.button);
    }

}
