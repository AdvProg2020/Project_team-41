package Bank;

import Server.Controller.RandomNumberGenerator;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BankClient extends Thread{
    Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public BankClient(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        try {
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true){
            try {
                String input = receiveMessage();
                if(input.startsWith("create_account")){
                    createAccount(input);
                }else if(input.startsWith("get_token")){
                    getToken(input);
                }else if(input.startsWith("create_receipt")){
                    createReceipt(input);
                }else if(input.startsWith("get_transactions")){
                    getTransactions(input);
                }else if(input.startsWith("pay")){
                    pay(input);
                }else if(input.startsWith("get_balance")){
                    getBalance(input);
                }else if(input.equals("exit")){
                    socket.close();
                    break;

                }else{
                    System.out.println("what?");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void sendMessage(String message) {
        try {
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
            System.out.println("sent: "+message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String receiveMessage() {
        try {
            String message = dataInputStream.readUTF();
            System.out.println("received: "+message);
            return message;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createAccount(String input) throws IOException {
        String [] split=input.split(" ");
        if(split.length!=6){
            sendMessage("invalid input");
        }else{
            if(!split[4].equals(split[5])){
                sendMessage("passwords do not match");
                return;
            }
            for (Account account : BankDatabase.getInstance().getAccounts()) {
                if(account.getUsername().equals(split[3])){
                    sendMessage("username is not available");
                    return;
                }
            }
            Account account=new Account(split[1],split[2],split[3],split[4]);
            BankDatabase.getInstance().addAccount(account);
            sendMessage(String.valueOf(account.getId()));
        }

    }
    public void getToken(String input){
        String [] split=input.split(" ");
        for (Account account : BankDatabase.getInstance().getAccounts()) {
            if(account.getUsername().equals(split[1])&&account.getPassword().equals(split[2])){
                String token=RandomNumberGenerator.getToken(5);
                Main.getTokens().put(account,new TokenAndDate(token,new Date()));
                sendMessage(token);
                return;
            }
        }
        sendMessage("invalid username or password");
    }
    public void createReceipt(String input){
        String [] split=input.split(" ");
        if(split.length==7||split.length==6) {
            if(!validInt(split[3])){
                sendMessage("invalid money");
                return;
            }
            if (split[2].equals("deposit")) {
                deposit(split);
            } else if (split[2].equals("withdraw")) {
                withdraw(split);
            } else if (split[2].equals("move")) {
                move(split);
            } else {
                sendMessage("invalid receipt type");
            }
        }else{
            sendMessage("invalid parameters passed");
        }

    }
    public boolean validInt(String money){
        if(!money.matches("\\d+")){
            return false;
        }else if(Integer.parseInt(money)<0){
            return false;
        }else {
            return true;
        }
    }
    public void deposit(String[] split){
        if(!split[4].equals("-1")){
            sendMessage("invalid parameters passed");
            return;
        }
        if(!validInt(split[5])){
            sendMessage("dest account id is invalid");
            return;
        }
        for (Account account : BankDatabase.getInstance().getAccounts()) {
            if(account.getId()==Integer.parseInt(split[5])){
                makeTheReceipt(split);
                return;
            }
        }
        sendMessage("dest account id is invalid");
    }
    public void withdraw(String[] split){
        if(!split[5].equals("-1")){
            sendMessage("invalid parameters passed");
            return;
        }
        if(!validInt(split[4])){
            sendMessage("source account id is invalid");
            return;
        }
        for (Account account : BankDatabase.getInstance().getAccounts()) {
            if(account.getId()==Integer.parseInt(split[4])){
                if(Main.getTokens().get(account).getToken().equals(split[1])){
                    makeTheReceipt(split);
                }else{
                    sendMessage("token is invalid");
                }
                return;
            }
        }
        sendMessage("source account id is invalid");
    }
    public void move(String[] split){
        if(split[4].equals(split[5])){
            sendMessage("equal source and dest account");
            return;
        }
        if(!validInt(split[4])){
            sendMessage("source account id is invalid");
            return;
        }
        if(!validInt(split[5])){
            sendMessage("dest account id is invalid");
            return;
        }
        for (Account account : BankDatabase.getInstance().getAccounts()) {
            if(account.getId()==Integer.parseInt(split[5])){
                for (Account account1 : BankDatabase.getInstance().getAccounts()) {
                    if(account1.getId()==Integer.parseInt(split[4])){
                        if(Main.getTokens().get(account1).getToken().equals(split[1])){
                            makeTheReceipt(split);
                        }else{
                            sendMessage("token is invalid");
                        }
                        return;
                    }
                }
                sendMessage("source account id is invalid");
                return;
            }
        }
        sendMessage("dest account id is invalid");


    }
    public void makeTheReceipt(String[] split){
        Transaction transaction;
        if(split.length==6){
            transaction=new Transaction(split[2],Integer.parseInt(split[3]),Integer.parseInt(split[4]),Integer.parseInt(split[5]),"");
        }else{
            transaction=new Transaction(split[2],Integer.parseInt(split[3]),Integer.parseInt(split[4]),Integer.parseInt(split[5]),split[6]);
        }
        BankDatabase.getInstance().addTransaction(transaction);
        sendMessage(String.valueOf(transaction.getId()));
    }
    public void getTransactions(String input){

    }
    public void pay(String input){
        String []split =input.split(" ");
        if(!validInt(split[1])){
            sendMessage("invalid receipt id");
            return;
        }
        for (Transaction transaction : BankDatabase.getInstance().getTransactions()) {
            if(transaction.getId()==Integer.parseInt(split[1])){
                if(transaction.getPaid()==1){
                    sendMessage("receipt is paid before");
                    return;
                }else{
                    if(transaction.getReceiptType().equals("deposit")){
                        payDeposit(transaction);
                        return;
                    }else if(transaction.getReceiptType().equals("move")){
                        payMove(transaction);
                        return;
                    }else if(transaction.getReceiptType().equals("withdraw")){
                        payWithdraw(transaction);
                        return;
                    }

                }
            }
        }
        sendMessage("invalid receipt id");
    }
    public void payDeposit(Transaction transaction){
        Account desAccount=BankDatabase.getInstance().getAccountById(transaction.getDestAccountID());
        if(desAccount==null){
            sendMessage("invalid account id");
        }else{
            desAccount.addCredit(transaction.getMoney());
            transaction.setPaid(1);
            sendMessage("done successfully");
        }
    }
    public void payWithdraw(Transaction transaction){
        Account sourceAccount=BankDatabase.getInstance().getAccountById(transaction.getSourceAccountID());
        if(sourceAccount==null){
            sendMessage("invalid account id");
        }else{
            if(sourceAccount.getCredit()<transaction.getMoney()){
                sendMessage("source account does not have enough money");
            }else {
                sourceAccount.removeCredit(transaction.getMoney());
                transaction.setPaid(1);
                sendMessage("done successfully");
            }
        }
    }
    public void payMove(Transaction transaction){
        Account sourceAccount=BankDatabase.getInstance().getAccountById(transaction.getSourceAccountID());
        Account desAccount=BankDatabase.getInstance().getAccountById(transaction.getDestAccountID());

        if(sourceAccount==null||desAccount==null){
            sendMessage("invalid account id");
        }else{
            if(sourceAccount.getCredit()<transaction.getMoney()){
                sendMessage("source account does not have enough money");
            }else {
                desAccount.addCredit(transaction.getMoney());
                sourceAccount.removeCredit(transaction.getMoney());
                transaction.setPaid(1);
                sendMessage("done successfully");
            }
        }

    }
    public void getBalance(String input){
        String [] split=input.split(" ");
        HashMap<Account,TokenAndDate> tokens= Main.getTokens();
        for (Map.Entry<Account, TokenAndDate> accountTokenAndDateEntry : tokens.entrySet()) {
            if(accountTokenAndDateEntry.getValue().getToken().equals(split[1])){
                sendMessage(String.valueOf(accountTokenAndDateEntry.getKey().getCredit()));
                return;
            }
        }

        sendMessage("token is invalid");
    }
}
