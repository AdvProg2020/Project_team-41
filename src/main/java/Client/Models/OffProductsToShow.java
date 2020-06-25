package Client.Models;

import Client.Controller.FilterController;
import Client.Controller.UserSectionController.ManagerController;
import Client.View.Menus.ProductPage.ProductPageGeneralButtons;
import ir.huri.jcal.JalaliCalendar;

import javafx.scene.control.*;
import org.example.App;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class OffProductsToShow {
    private String productId;
    private String productName;
    private int price;
    private int score;
    private Button button;
    private double priceWithDiscount;
    private String  startDate;
    private String  endDate;


    public void setButton(Button button) {
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setStartDate(String  startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String  endDate) {
        this.endDate = endDate;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String  getStartDate() {
        return startDate;
    }

    public String  getEndDate() {
        return endDate;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPriceWithDiscount(int priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public int getPrice() {
        return price;
    }

    public double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public OffProductsToShow(String productId, String productName, int price, double priceWithDiscount, String  startDate, String  endDate,int score,Button button) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.priceWithDiscount = priceWithDiscount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.score=score;
        this.button=button;
        button.setStyle("-fx-background-color:transparent");
        button.setOnAction(e ->{
            try {
                Product product = ManagerController.getInstance().getProductById(this.productId);
                ProductPageGeneralButtons.setTheProduct(product);
                product.setViews(product.getViews()+1);
                ProductPageGeneralButtons.parentFxmlAddress = "offs";
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
        OffProductsToShow that = (OffProductsToShow) o;
        return price == that.price &&
                priceWithDiscount == that.priceWithDiscount &&
                score == that.score &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(button, that.button);
    }

}
