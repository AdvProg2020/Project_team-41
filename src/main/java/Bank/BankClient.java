package Bank;

import Server.Controller.RandomNumberGenerator;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;

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
            sendMessage(account.getId());
        }

    }
    public void getToken(String input){
        String [] split=input.split(" ");
        for (Account account : BankDatabase.getInstance().getAccounts()) {
            if(account.getUsername().equals(split[1])&&account.getPassword().equals(split[2])){
                String token=RandomNumberGenerator.getToken(5);
                Main.getTokens().put(new TokenAndDate(token,new Date()),account);
                sendMessage(token);
                return;
            }
        }
        sendMessage("invalid username or password");
    }
    public void createReceipt(String input){

    }
    public void getTransactions(String input){

    }
    public void pay(String input){

    }
    public void getBalance(String input){
        String [] split=input.split(" ");
        HashMap<TokenAndDate,Account> tokens= Main.getTokens();
        for (TokenAndDate tokenAndDate : tokens.keySet()) {
            if(tokenAndDate.getToken().equals(split[1])){
                sendMessage(String.valueOf(tokens.get(tokenAndDate).getCredit()));
                return;
            }
        }
        sendMessage("token is invalid");
    }
}
