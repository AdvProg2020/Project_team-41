package Bank;

import Server.Database;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class BankDatabase implements Serializable {
    private static BankDatabase bankDatabase;
    private ArrayList<Account>accounts=new ArrayList<>();
    private ArrayList<Transaction>transactions=new ArrayList<>();

    public static BankDatabase getInstance() {
        if (bankDatabase == null) {
            bankDatabase = new BankDatabase();
        }
        return bankDatabase;
    }

    public static void setInstance(BankDatabase newBankDatabase) {
        bankDatabase=newBankDatabase;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    public void addAccount(Account account){
        accounts.add(account);
        Main.writeDatabase();
    }
    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
        Main.writeDatabase();
    }
    public Account getAccountById(int id){
        for (Account account : accounts) {
            if(account.getId()==id){
                return account;
            }
        }
        return null;
    }
}
