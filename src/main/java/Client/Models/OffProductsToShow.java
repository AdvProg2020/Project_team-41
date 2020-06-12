package Client.Models;

import java.util.Date;

public class OffProductsToShow {
    private String productId;
    private String productName;
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

    public OffProductsToShow(String productId, String productName, Date startDate, Date endDate) {
        this.productId = productId;
        this.productName = productName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
