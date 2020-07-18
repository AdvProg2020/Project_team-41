package Client.Controller.bankController;

import Client.Models.Message.Message;
import Client.Models.bank.Receipt;
import com.sun.nio.sctp.SendFailedNotification;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class handles initiating connection to bankAPI ,sending requests to Bank server
 * and also responses from Bank server.
 */
public class BankAPI {
    private static BankAPI bankAPI;
    public final int PORT = 11920;
    public final String IP = "tcp://2.tcp.ngrok.io";
    private Date tokenCreationDate;
    private int accountId;
    private String token;

    private DataOutputStream outputStream;
    private DataInputStream inputStream;

    public static void makeInstance() throws IOException {
        if (bankAPI == null) {
            bankAPI = new BankAPI();
        }
    }
    public static BankAPI getInstance() {

        return bankAPI;
    }

    private BankAPI() throws IOException {
        this.connectToBankServer();
    }
    /**
     * This method is used to add initiating socket and IN/OUT data stream .
     *
     * @throws IOException when IP/PORT hasn't been set up properly.
     */
    public void connectToBankServer() throws IOException {
        try {
            Socket socket = new Socket(IP, PORT);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new IOException("Exception while initiating connection:");
        }
    }

    /**
     * This method is used to start a Thread ,listening on IN data stream.
     */
    public void startListeningOnInput() {
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println(inputStream.readUTF());
                } catch (IOException e) {
                    System.out.println("disconnected");
                }
            }
        }).start();
    }

    /**
     * This method is used to send message with value
     *
     * @param msg to Bank server.
     * @throws IOException when OUT data stream been interrupted.
     */
    public void sendMessage(String msg) {
        try {
            outputStream.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() {
        try {
            String message = inputStream.readUTF();
            System.out.println("received message from bank:");
            System.out.println(message);
            return message;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int createAccount(String firstName,String lastName,String username,String password,String repeatPassword) throws Exception {
        sendMessage("create_account"+" "+firstName+" "+lastName+" "+username+" "+password+" "+repeatPassword);
        String message = receiveMessage();
        try {
            return Integer.parseInt(message);
        } catch (NumberFormatException e) {
            throw new Exception(message);
        }
    }
    public int howMuchTimeLeft(){
        return (int) ((3600 * 1000) - (new Date().getTime() - tokenCreationDate.getTime()));
    }
    public void updateToken(String username,String password) throws Exception {
        sendMessage("get_token " + username + " " + password);
        String message = receiveMessage();
        if (message.equals("invalid username or password")) {
            throw new Exception(message);
        }
        token = message;

    }
    public int deposit(String money,String id,String description) throws Exception {
        sendMessage("create_receipt"+" "+token+" "+"deposit"+" "+money+" "+(-1)+" "+id+(description.equals("") ?"":" "+description));
        String message = receiveMessage();
        try {
            return Integer.parseInt(message);
        } catch (NumberFormatException e) {
            throw new Exception(message);
        }
    }
    public int withDraw(String money,String id,String description) throws Exception {
        sendMessage("create_receipt"+" "+token+" "+"withdraw"+" "+money+" "+id+" "+(-1)+(description.equals("") ?"":" "+description));
        String message = receiveMessage();
        try {
            return Integer.parseInt(message);
        } catch (NumberFormatException e) {
            throw new Exception(message);
        }
    }
    public int move(String money,String sourceID,String destID,String description) throws Exception {
        sendMessage("create_receipt"+" "+token+" "+"move"+" "+money+" "+sourceID+" "+destID+(description.equals("") ?"":" "+description));
        String message = receiveMessage();
        try {
            return Integer.parseInt(message);
        } catch (NumberFormatException e) {
            throw new Exception(message);
        }
    }
    public ArrayList<Receipt> getReceipts(TransactionType transactionType) throws Exception {
        sendMessage("get_transactions " + token + " " + transactionType.getSign());
        String message = receiveMessage();
        if (!message.startsWith("{")) {
            throw new Exception(message);
        }
        ArrayList<Receipt> receipts = new ArrayList<>();
        for (String s : message.split("\\*")) {
            receipts.add(Receipt.getFromJson(s));
        }
        return receipts;
    }

    public void pay(String receiptID) throws Exception {
        sendMessage("pay " + receiptID);
        String message = receiveMessage();
        if (!message.equals("done successfully")) {
            throw new Exception(message);
        }
    }
    public int getBalance() throws Exception {
        sendMessage("get_balance " + token);
        String message = receiveMessage();
        try {
            return Integer.parseInt(message);
        } catch (NumberFormatException e) {
            throw new Exception(message);
        }
    }
    public void exit(){
        sendMessage("exit");
    }

    public enum TransactionType{
        INCREMENT('+'),ALL_HISTORY('*'),DECREMENT('-');
        char sign;

        TransactionType(char sign) {
            this.sign = sign;
        }

        public char getSign() {
            return sign;
        }
    }


}
