package io.dhruv.blockchain;

import io.dhruv.blockchain.model.Block;
import io.dhruv.blockchain.model.Node;
import io.dhruv.blockchain.model.Transaction;
import javafx.scene.control.cell.CheckBoxListCell;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;

public class Test {

    static ArrayList<Transaction> pendingTransactions;
    public static void main(String args[]){
        System.out.println("\n-------in Test main()-------");
        //singleton object of Blockchain
        Blockchain blockchain = Blockchain.getInstance();
        System.out.println("\n----Blockchain object is created---");

        //register 5 five nodes in blockchain
        registerNodes();
        System.out.println("\n----5 Nodes are added---\n");


        //show balances initially
        showBalances();

        //generate 100 pending Transactions
        generate_100_pendingTransactions();


        //
        for(Transaction pt:pendingTransactions){
            //append the transaction to the ledger
            if(blockchain.createTransaction(pt)){
                //transaction granted and if add new block if required
                if(blockchain.currentTransactions.size()==10) {
                    //add new block with 10 transaction
                    if (blockchain.length() == 0) {
                        //add genesis block
                        blockchain.addBlock(new Block("-1", getCurrentTimeStamp()));
                    } else {
                        //append new block
                        Block lastBlock = blockchain.getLastBlock();
                        String prevHash = lastBlock.getHash();
                        blockchain.addBlock(new Block(prevHash, getCurrentTimeStamp()));
                    }
                }

            }else{
                System.out.println("Invalid Transaction: { sender : "+pt.getSender().getName()+", receiver: "+pt.getReceiver().getName()+", amount: "+pt.getAmount()+" }");
            }
        }


        //Show Block Information
        showBlockInformation();

        //Show final balances
        showBalances();


    }

    public static void showBlockInformation(){
        System.out.println("\n-------Block information-------\n");
        Blockchain blockchain = Blockchain.getInstance();
        System.out.println("Total no of blocks: "+blockchain.length());
        int i=0;
        if(blockchain.length()>0){
            for(Block block:blockchain.chain){
                System.out.println(i+"th Block: "+convert2String(block));
                i++;
            }
        }
    }

    public static String getCurrentTimeStamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime()+"";

    }

    public static String convert2String(Block block){
        ArrayList<Transaction> transactions = block.getTransactions();
        String tr = "";
        for(Transaction t:transactions){
            tr+="{ sender : "+t.getSender().getName()+", receiver : "+t.getReceiver().getName()+", amount : "+t.getAmount()+" }\n";
        }
        String ob="{ \nprevHash: "+block.getPrevHash()+"\n Hash: "+block.getHash()+"\n TimeStamp: "+block.getTimeStamp()+" \n Transactions: { \n"+tr+" }\n}\n";

        return ob;
    }

    static void registerNodes(){
        Blockchain blockchain = Blockchain.getInstance();
        blockchain.registerNode(new Node(0,"Rajesh",100));
        blockchain.registerNode(new Node(1,"Rohan",200));
        blockchain.registerNode(new Node(2,"Gauri",300));
        blockchain.registerNode(new Node(3,"Tez",500));
        blockchain.registerNode(new Node(4,"Mohit",600));
    }

    static void generate_100_pendingTransactions(){
        Blockchain blockchain = Blockchain.getInstance();
        pendingTransactions = new ArrayList<>();
        Random rand = new Random();
        for(int i=0;i<100;i++){
            int sender_index = rand.nextInt(5);
            int receiver_index = rand.nextInt(5);
            int amount = rand.nextInt(200)+50;
            pendingTransactions.add(new Transaction(blockchain.nodes.get(sender_index),blockchain.nodes.get(receiver_index),amount));

        }

    }

    static void showBalances(){
        System.out.println("\n----Balances of Nodes----\n");
        Blockchain blockchain = Blockchain.getInstance();
        for(Node node:blockchain.nodes){
            System.out.println("\t"+node.getName()+" = "+node.getBalance());
        }
        System.out.println();
    }
}
