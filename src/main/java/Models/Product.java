package Models;

import java.util.ArrayList;

public class Product {

    private String productId;
    private Situation productSituation;
    private String name;
    private String companyName;
    private int price;
    private Seller seller;
    private boolean isThereMore;
    private Category category;
    private String description;
    private ArrayList<Score>scores;
    private ArrayList<Comment>comments;
}
