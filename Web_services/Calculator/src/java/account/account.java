/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import java.util.ArrayList;

/**
 *
 * @author sherm
 */
public class account {
    
    public int balance = 0;
    public int number = 0;
    public String name = "";
    ArrayList<Integer> deposit_history = new ArrayList<>();
    ArrayList<Integer> withdrawal_history = new ArrayList<>();
    
    public void add_deposit(int deposit) {
        deposit_history.add(deposit);
    }

    public void add_withdrawal(int withdrawal) {
        withdrawal_history.add(withdrawal);
    }

    public ArrayList<Integer> getDeposit_history() {
        return deposit_history;
    }

    public ArrayList<Integer> getWithdrawal_history() {
        return withdrawal_history;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void deposit(int amount) {
        this.balance += amount; 
    }
    
    public boolean withdrawal(int amount) {
        if(this.balance - amount < 0){
            return false;
        }else{
            this.balance -= amount;
            return true;
        }   
    }
    
    public int balance_value() {
        return this.balance;  
    }
}
