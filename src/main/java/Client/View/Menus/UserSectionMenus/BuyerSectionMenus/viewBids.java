package Client.View.Menus.UserSectionMenus.BuyerSectionMenus;

import Client.Models.*;
import Server.Controller.TimeControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class viewBids {
    public TableView<BidsToShow> tableView;
    public TableColumn<BidsToShow, Button> participate;
    public TableColumn<BidsToShow, String> product;
    public TableColumn<BidsToShow, String> endDate;
    public TableColumn<BidsToShow, String> bidId;
    public TableColumn<BidsToShow, String> seller;

    public void initialize() {
        tableView.setItems(getBids());
        participate.setCellValueFactory(new PropertyValueFactory<>("participate"));
        product.setCellValueFactory(new PropertyValueFactory<>("product"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        bidId.setCellValueFactory(new PropertyValueFactory<>("bidId"));
        seller.setCellValueFactory(new PropertyValueFactory<>("seller"));
    }

    public ObservableList<BidsToShow> getBids(){
        ObservableList<BidsToShow> bids= FXCollections.observableArrayList();
        try {
            for (Bid bid : Bid.getAllBids()) {
                bids.add(new BidsToShow(bid.getBidId() , bid.getProduct().getName() ,
                        TimeControl.getJalaliDateAndTimeForPrint(bid.getEndDate()) , bid.getSeller().getUserName() , new Button("Participate")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bids;
    }
}