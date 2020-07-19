package Client.Models.Message;


public enum MessageType {
    SUCCESSFUL(null),
    GET_PERSONAL_INFO(ClassTypes.USER_SECTION),EDIT(ClassTypes.USER_SECTION),GET_MANAGER_ACCOUNT_ID(ClassTypes.USER_SECTION), GET_WAGE(ClassTypes.USER_SECTION), GET_LOGGED_IN_PERSON(ClassTypes.USER_SECTION), INCREASE_CREDIT(ClassTypes.USER_SECTION),
    LOGIN(ClassTypes.LOGIN_REGISTER),REGISTER(ClassTypes.LOGIN_REGISTER),CHECK_MANAGER(ClassTypes.LOGIN_REGISTER),LOGOUT(ClassTypes.LOGIN_REGISTER),
    ACCEPT_ALL_REQUESTS(ClassTypes.MANAGER_SECTION),CHANGE_TRADE_LOG_TO_SENT(ClassTypes.MANAGER_SECTION),VIEW_TRADE_LOGS(ClassTypes.MANAGER_SECTION),EDIT_CATEGORY_SPECIAL_FEATURES(ClassTypes.MANAGER_SECTION),GET_ALL_PRODUCTS(ClassTypes.MANAGER_SECTION),GET_ALL_USERS(ClassTypes.MANAGER_SECTION),GET_USER_BY_USERNAME(ClassTypes.MANAGER_SECTION),DELETE_USER(ClassTypes.MANAGER_SECTION),CREATE_MANAGER_PROFILE(ClassTypes.MANAGER_SECTION),REMOVE_PRODUCT_MANAGER_SECTION(ClassTypes.MANAGER_SECTION),CREATE_DISCOUNT_CODE(ClassTypes.MANAGER_SECTION),VIEW_DISCOUNT_CODE(ClassTypes.MANAGER_SECTION),EDIT_DISCOUNT_CODE(ClassTypes.MANAGER_SECTION),REMOVE_DISCOUNT_CODE(ClassTypes.MANAGER_SECTION),SHOW_REQUEST(ClassTypes.MANAGER_SECTION),GET_REQUEST_DETAILS(ClassTypes.MANAGER_SECTION),ACCEPT_REQUEST(ClassTypes.MANAGER_SECTION),DECLINE_REQUEST(ClassTypes.MANAGER_SECTION),SHOW_CATEGORIES(ClassTypes.MANAGER_SECTION),EDIT_CATEGORY_NAME(ClassTypes.MANAGER_SECTION),ADD_CATEGORY(ClassTypes.MANAGER_SECTION),REMOVE_CATEGORY(ClassTypes.MANAGER_SECTION),GET_CATEGORY_SPECIAL_FEATURES_MANAGER_SECTION(ClassTypes.MANAGER_SECTION),VIEW_ALL_DISCOUNT_CODES(ClassTypes.MANAGER_SECTION),GET_PRODUCT_BY_ID(ClassTypes.MANAGER_SECTION), SET_WAGE(ClassTypes.MANAGER_SECTION), SET_UP_MANAGER_ACCOUNT_ID(ClassTypes.MANAGER_SECTION),
    GET_CODED_DISCOUNTS(ClassTypes.BUYER_SECTION),PAY_FOR_THE_SHOP(ClassTypes.BUYER_SECTION),ADD_CODED_DISCOUNT_TO_CART(ClassTypes.BUYER_SECTION),REMOVE_CODED_DISCOUNT_FROM_CART(ClassTypes.BUYER_SECTION),RATE_THE_PRODUCT(ClassTypes.BUYER_SECTION),GET_PRODUCT_BUYER_SECTION(ClassTypes.BUYER_SECTION),GET_CODED_DISCOUNT(ClassTypes.BUYER_SECTION),INCREASE_PRODUCT(ClassTypes.BUYER_SECTION),DECREASE_PRODUCT(ClassTypes.BUYER_SECTION),GET_ALL_BOUGHT_FILES(ClassTypes.BUYER_SECTION), DOWNLOAD_FILE(ClassTypes.BUYER_SECTION),
    GET_CATEGORY_SPECIAL_FEATURES_SELLER_SECTION(ClassTypes.SELLER_SECTION),GET_PRODUCT_BUYERS(ClassTypes.SELLER_SECTION),GET_FACTORY_NAME(ClassTypes.SELLER_SECTION),GET_SALES_HISTORY(ClassTypes.SELLER_SECTION),GET_LOGS(ClassTypes.SELLER_SECTION),GET_PRODUCTS(ClassTypes.SELLER_SECTION),GET_PRODUCT_SELLER_SECTION(ClassTypes.SELLER_SECTION),GET_BUYERS(ClassTypes.SELLER_SECTION),EDIT_PRODUCT(ClassTypes.SELLER_SECTION),ADD_PRODUCT(ClassTypes.SELLER_SECTION),REMOVE_PRODUCT_SELLER_SECTION(ClassTypes.SELLER_SECTION),GET_CATEGORIES(ClassTypes.SELLER_SECTION),GET_OFFS(ClassTypes.SELLER_SECTION),GET_OFF(ClassTypes.SELLER_SECTION),EDIT_OFF(ClassTypes.SELLER_SECTION),ADD_OFF(ClassTypes.SELLER_SECTION), TRANSFER_MONEY_TO_SELLER(ClassTypes.SELLER_SECTION),
    GET_ALL_CATEGORIES(ClassTypes.ALL_PRODUCTS), GET_PRODUCT(ClassTypes.ALL_PRODUCTS),GET_ALL_PRODUCTS_FOR_FILTER(ClassTypes.ALL_PRODUCTS),GET_CATEGORY_BY_NAME(ClassTypes.ALL_PRODUCTS),
    ADD_COMMENT(ClassTypes.PRODUCT),AMOUNT_OF_DISCOUNT(ClassTypes.PRODUCT),ADD_PRODUCT_TO_CART(ClassTypes.PRODUCT),
    GET_OFF_PRODUCTS(ClassTypes.OFFS),
    GET_BACKUPS(ClassTypes.BACKUP),GET_CHAT_BOX(ClassTypes.BACKUP),SEND_COMMENT(ClassTypes.BACKUP),GET_ALL_CHAT_BOXES(ClassTypes.BACKUP),
    ADD_BID(ClassTypes.BID),GET_ALL_BIDS(ClassTypes.BID),GET_BID_BY_ID(ClassTypes.BID),ADD_PARTICIPANT(ClassTypes.BID),INCREASE_PRICE(ClassTypes.BID),GET_BID_CHAT_BOX(ClassTypes.BID),ADD_BID_COMMENT(ClassTypes.BID);

    ClassTypes classTypes;
    MessageType(ClassTypes classTypes) {
        this.classTypes = classTypes;
    }

    public ClassTypes getClassTypes() {
        return classTypes;
    }
    public enum  ClassTypes {
        USER_SECTION,MANAGER_SECTION,SELLER_SECTION,BUYER_SECTION,LOGIN_REGISTER,OFFS,ALL_PRODUCTS,PRODUCT,BACKUP,BID
    }
}

