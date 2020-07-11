package Client.Models.Message;


public enum MessageTypes {
    LOGIN(ClassTypes.LOGIN_REGISTER),REGISTER(ClassTypes.LOGIN_REGISTER),
    ACCEPT_ALL_REQUESTS(ClassTypes.MANAGER_SECTION),EDIT_CATEGORY_SPECIAL_FEATURES(ClassTypes.MANAGER_SECTION),GET_ALL_PRODUCTS(ClassTypes.MANAGER_SECTION),GET_ALL_USERS(ClassTypes.MANAGER_SECTION),
    ;

    ClassTypes classTypes;
    MessageTypes(ClassTypes classTypes) {
        this.classTypes = classTypes;
    }

    public ClassTypes getClassTypes() {
        return classTypes;
    }
    public enum  ClassTypes {
        MANAGER_SECTION,SELLER_SECTION,BUYER_SECTION,LOGIN_REGISTER,ALL_PRODUCTS;
    }
}

