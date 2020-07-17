package Client.Models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.App;

import java.util.Date;
import java.util.Objects;

import static Client.View.Menus.Menu.loadFXML;

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
                Stage window = new Stage();

                window.initModality(Modality.APPLICATION_MODAL);
                window.setTitle("Offer price");

                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( "Bid/OfferPrice.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                window.setScene(scene);
                window.showAndWait();
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
