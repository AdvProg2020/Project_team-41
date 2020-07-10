package Client.Models.Message;

import Client.Controller.UserSectionController.UserSectionController;


public class Message {
    private String sender;
    private Object[] inputs;
    private Object output;
    protected MessageTypes messageType;

    public Message(Object[] inputs) {
        this.inputs = inputs;
        this.sender = UserSectionController.getLoggedInPerson().getUserName();
    }
    public Message(Object output) {
        this.output = output;
    }

    public void checkForException() throws Exception {
        if (output instanceof Exception) {
            throw (Exception) output;
        }
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

    public MessageTypes getMessageType() {
        return messageType;
    }
}
