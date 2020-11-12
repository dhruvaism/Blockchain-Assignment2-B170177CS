package io.dhruv.blockchain.model;

public class Node {
    private int index;
    private String name;
    private int balance;

    public Node(int index, String name, int balance) {
        this.index = index;
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
