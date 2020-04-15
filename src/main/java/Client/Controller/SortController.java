package Client.Controller;

import java.util.ArrayList;

public class SortController {
    private static SortController single_instance = null;
    public static SortController getInstance()
    {
        if (single_instance == null)
            single_instance = new SortController();

        return single_instance;
    }
    private SortController(){
    }
    public ArrayList<String> getAvailableSorts(){

    }
    public void sortProducts(String sort){

    }
    public String getCurrentSort(){

    }
    public void disableCurrentSort(){

    }
}
