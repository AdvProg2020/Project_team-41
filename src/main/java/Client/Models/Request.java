package Client.Models;

import Client.Models.Person.Seller;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;



public class Request implements Serializable {

    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    //for generating token
    private HashMap<String,String> editRequest;
    private RequestType requestType;
    private Product product;
    private Seller seller;
    private Off off;
    private String requestId;

    public Request(HashMap<String, String> editRequest, RequestType requestType, Product product, Seller seller, Off off) {
        this.editRequest = editRequest;
        this.requestType = requestType;
        this.product = product;
        this.seller = seller;
        this.off = off;
        this.requestId = generateNewToken();
    }

    public String getRequestId() {
        return requestId;
    }

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


    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

}

