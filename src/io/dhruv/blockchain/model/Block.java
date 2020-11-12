package io.dhruv.blockchain.model;

import io.dhruv.blockchain.utility.Hash;
import java.util.ArrayList;


public class Block {
    private String hash;
    private ArrayList<Transaction> transactions;
    private String prevHash;
    private String timeStamp;

    public Block(String prevHash, String timeStamp) {
        this.prevHash = prevHash;
        this.timeStamp = timeStamp;
        this.hash = calculateHash();
    }

    public String calculateHash(){
        Hash hash = new Hash(); //SHA 256
        return hash.calculate(this.prevHash  + this.timeStamp);
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
