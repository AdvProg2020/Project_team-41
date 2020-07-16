package Client.Models;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import org.example.App;

import java.util.Date;
import java.util.Objects;

public class BidsToShow {
    private String bidId;
    private String product;
    private String endDate;
    private String seller;
    private Button participate;

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Button getParticipate() {
        return participate;
    }

    public void setParticipate(Button participate) {
        this.participate = participate;
    }

    public BidsToShow(String bidId, String product, String endDate, String seller, Button participate) {
        this.bidId = bidId;
        this.product = product;
        this.endDate = endDate;
        this.seller = seller;
        this.participate = participate;
        participate.setPrefWidth(107);
        participate.setTextFill(Color.WHITE);
        participate.setStyle("-fx-background-color:#DC143C");
        participate.setOnAction(e ->{
            try {
                App.setRoot("Bid/OfferPrice");
            } catch (Exception ioException) {
                ioException.printStackTrace();
            }
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidsToShow that = (BidsToShow) o;
        return Objects.equals(bidId, that.bidId) &&
                Objects.equals(product, that.product) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(seller, that.seller) &&
                Objects.equals(participate, that.participate);
    }

}
