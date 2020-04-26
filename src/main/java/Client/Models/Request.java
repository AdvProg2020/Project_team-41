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

    }

