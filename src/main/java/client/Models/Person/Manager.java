package Client.Models.Person;

import Client.Controller.bankController.BankAPI;


public class Manager extends Person {
    private static int accountId;
    private static String accountUsername;
    private static String accountPassword;

    private static int wage;
    
    public void setUpManagerAccountId(Manager manager,String username,String password) throws Exception {
        BankAPI.makeInstance();
        accountId = BankAPI.getInstance().createAccount(manager.getFirstName(), manager.getLastName(), username, password, password);
        Manager.accountUsername = username;
        Manager.accountPassword = password;
    }

    public static int getAccountId() {
        return accountId;
    }

    public static int getWage() {
        return wage;
    }

    public static void setWage(int wage) throws Exception {
        if((wage<0)||wage>100)
            throw new Exception("invalid wage number");
        Manager.wage = wage;
    }

    public static String getAccountUsername() {
        return accountUsername;
    }

    public static String getAccountPassword() {
        return accountPassword;
    }
}
