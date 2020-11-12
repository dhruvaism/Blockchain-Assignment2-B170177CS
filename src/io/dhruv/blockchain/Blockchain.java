/*
Author: Dhrubajit Sarkar
        B170177CS
*/

package io.dhruv.blockchain;

import io.dhruv.blockchain.model.Block;
import io.dhruv.blockchain.model.Node;
import io.dhruv.blockchain.model.Transaction;

import java.util.ArrayList;

//Singleton class
public class Blockchain {

    ArrayList<Transaction> currentTransactions;
    ArrayList<Block> chain;
    ArrayList<Node> nodes;
    private static Blockchain blockchain=null;

    //singleton object
    public static Blockchain getInstance(){
        if(blockchain==null)
            blockchain = new Blockchain();
        return blockchain;
    }

    public Blockchain() {
        this.currentTransactions = new ArrayList<>();
        this.chain = new ArrayList<>();
        this.nodes = new ArrayList<>();
    }

    public void registerNode(Node node){
        this.nodes.add(node);
    }

    public void addBlock(Block block){
        block.setTransactions(this.currentTransactions);
        block.setHash(block.calculateHash());
        this.currentTransactions = new ArrayList<>();
        chain.add(block);
    }

    public boolean createTransaction(Transaction transaction){
        if(!transaction.isValid())
            return false;
        this.currentTransactions.add(transaction);
        int s_index=transaction.getSender().getIndex();
        int r_index=transaction.getReceiver().getIndex();

        //reflect Debit in Sender's balance
        this.nodes.get(s_index)
                .setBalance(this.nodes.get(s_index).getBalance()-transaction.getAmount());

        //reflect Credit in Receiver's balance
        this.nodes.get(r_index)
                .setBalance(this.nodes.get(r_index).getBalance()+transaction.getAmount());
        return true;
    }

    public int length(){
        return this.chain.size();
    }

    public Block getLastBlock(){
        return this.chain.size()==0?null:this.chain.get(this.chain.size()-1);
    }
}
