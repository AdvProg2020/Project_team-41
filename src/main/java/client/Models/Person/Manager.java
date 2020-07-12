package Client.Models.Person;

import Client.Controller.bankController.BankAPI;


public class Manager extends Person {
    private static int accountId;
    public void setUpManagerAccountId(Manager manager,String username,String password) throws Exception {
        BankAPI.makeInstance();
        accountId = BankAPI.getInstance().createAccount(manager.getFirstName(), manager.getLastName(), username, password, password);
    }

    public static int getAccountId() {
        return accountId;
    }
}
