package io.dhruv.blockchain.model;

public class Transaction {
    private Node sender;
    private Node receiver;
    private int amount;


    public Transaction(Node sender, Node receiver, int amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    //checking validity of transaction
    public boolean isValid(){
        if(this.sender.getBalance()>=amount)
            return true;
        return false;
    }

    public void setSender(Node sender) {
        this.sender = sender;
    }

    public void setReceiver(Node receiver) {
        this.receiver = receiver;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Node getSender() {
        return sender;
    }

    public Node getReceiver() {
        return receiver;
    }

    public int getAmount() {
        return amount;
    }


}
