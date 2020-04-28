package Client.Models;

import Client.Models.Person.Seller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

enum RequestType{
    ADD_PRODUCT,EDIT_PRODUCT,REMOVE_PRODUCT,ADD_OFF,EDIT_OFF,REGISTER_SELLER
}

public class Request implements Serializable {
    private HashMap<String,String> editRequest;
    private RequestType requestType;
    private Product product;
    private Seller seller;
    private Off off;

    public HashMap<String, String> getEditRequest() {
        return editRequest;
    }

    public void setEditRequest(HashMap<String, String> editRequest) {
        this.editRequest = editRequest;
    }

    public String getRequestType() {
        return requestType.toString();
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Off getOff() {
        return off;
    }

    public void setOff(Off off) {
        this.off = off;
    }
}

