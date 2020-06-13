package Client.Models;

import java.util.Date;

public class OffProductsToShow {
    private String productId;
    private String productName;
    private int price;
    private int priceWithDiscount;
    private Date startDate;
    private Date endDate;

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
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

    public int getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public OffProductsToShow(String productId, String productName, int price, int priceWithDiscount, Date startDate, Date endDate) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.priceWithDiscount = priceWithDiscount;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
