package Client.Controller.UserSectionController;

import Client.Models.TradeLogs;

import java.util.ArrayList;

public class SellerController extends UserSectionController{
        private static SellerController single_instance = null;
        public static SellerController getInstance()
        {
                if (single_instance == null)
                        single_instance = new SellerController();

                return single_instance;
        }
        private SellerController(){

        }
        public String getFactoryName(){

        }
        public ArrayList<TradeLogs> getLogs(){

        }

}
