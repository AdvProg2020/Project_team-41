package Client.Models.Message;

import Client.Controller.UserSectionController.UserSectionController;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Message implements Serializable {
    private String sender;
    private Object[] inputs;
    private Object output;
    protected MessageType messageType;
    private final Date date = new Date();

    public Message(Object[] inputs, MessageType messageType) {
        this.messageType = messageType;
        this.inputs = inputs;
        try {
            this.sender = UserSectionController.getLoggedInPersonUserName();
        } catch (Exception ignored) {
        }
    }
    public Message(Object output) {
        this.output = output;
    }

    public Message(MessageType messageType) {
        this.messageType = messageType;
    }

    public void checkForException() throws Exception {
        if (output instanceof Exception) {
            throw (Exception) output;
        }
    }

    public Date getDate() {
        return date;
    }

    public String getSender() {
        return sender;
    }

    public Object[] getInputs() {
        return inputs;
    }

    public Object getOutput() {
        return output;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", inputs=" + Arrays.toString(inputs) +
                ", output=" + output +
                ", messageType=" + messageType +
                '}';
    }
    public boolean isLarge(){
        final int MAX_SIZE = 1000;
        if(this.getOutput() instanceof List) {
            return ((List) this.getOutput()).size() > MAX_SIZE;
        }
        else if (this.getInputs() != null) {
            for (Object input : this.getInputs()) {
                if (input instanceof List) {
                    if (((List) input).size() > MAX_SIZE) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
