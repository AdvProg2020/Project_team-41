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
    private RequestType requestType;
    private Product product;
    private Product editedProduct;
    private Seller seller;
    private Off off;
    private Off editedOff;
    private String requestId;
    private Comment comment;

    public Request(Seller seller,Product product, Product editedProduct) {
        requestId = generateNewToken();
        requestType = RequestType.EDIT_PRODUCT;
        this.product = product;
        this.editedProduct = editedProduct;
        this.seller = seller;

    }

    public Request(Seller seller, Off off, Off editedOff) {
        requestId = generateNewToken();
        requestType = RequestType.EDIT_OFF;
        this.seller = seller;
        this.off = off;
        this.editedOff = editedOff;
    }

    public Request(Seller seller,Product product, RequestType requestType) {
        requestId = generateNewToken();
        this.requestType = requestType;
        this.product = product;
        this.seller = seller;
    }

    public Request(Seller seller) {
        requestId = generateNewToken();
        requestType = RequestType.REGISTER_SELLER;
        this.seller = seller;
    }

    public Request(Seller seller, Off off) {
        requestId = generateNewToken();
        requestType = RequestType.ADD_OFF;
        this.seller = seller;
        this.off = off;
    }

    public Request(Comment comment){
        this.requestId = generateNewToken();
        this.requestType = RequestType.ADD_COMMENT;
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getRequestId() {
        return requestId;
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

    public Product getEditedProduct() {
        return editedProduct;
    }

    public void setEditedProduct(Product editedProduct) {
        this.editedProduct = editedProduct;
    }

    public Off getEditedOff() {
        return editedOff;
    }

    public void setEditedOff(Off editedOff) {
        this.editedOff = editedOff;
    }

    public static String generateNewToken() {
        byte[] randomBytes = new byte[2];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

}

