package Client.Models;

import Client.Models.Person.Seller;
import Server.Controller.RandomNumberGenerator;

import java.io.Serializable;
import java.util.Objects;


public class Request implements Serializable {

    private RequestType requestType;
    private Product product;
    private Product editedProduct;
    private Seller seller;
    private Off off;
    private Off editedOff;
    private String requestId;
    private Comment comment;

    public Request(Seller seller,Product product, Product editedProduct) {
        requestId = RandomNumberGenerator.getToken(5);
        requestType = RequestType.EDIT_PRODUCT;
        this.product = product;
        this.editedProduct = editedProduct;
        this.seller = seller;

    }

    public Request(Seller seller, Off off, Off editedOff) {
        requestId = RandomNumberGenerator.getToken(5);
        requestType = RequestType.EDIT_OFF;
        this.seller = seller;
        this.off = off;
        this.editedOff = editedOff;
    }

    public Request(Seller seller,Product product, RequestType requestType) {
        requestId = RandomNumberGenerator.getToken(5);
        this.requestType = requestType;
        this.product = product;
        this.seller = seller;
    }

    public Request(Seller seller) {
        requestId = RandomNumberGenerator.getToken(5);
        requestType = RequestType.REGISTER_SELLER;
        this.seller = seller;
    }

    public Request(Seller seller, Off off) {
        requestId = RandomNumberGenerator.getToken(5);
        requestType = RequestType.ADD_OFF;
        this.seller = seller;
        this.off = off;
    }

    public Request(Comment comment){
        this.requestId = RandomNumberGenerator.getToken(5);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return requestType == request.requestType &&
                Objects.equals(product, request.product) &&
                Objects.equals(editedProduct, request.editedProduct) &&
                Objects.equals(seller, request.seller) &&
                Objects.equals(off, request.off) &&
                Objects.equals(editedOff, request.editedOff) &&
                Objects.equals(requestId, request.requestId) &&
                Objects.equals(comment, request.comment);
    }

}

