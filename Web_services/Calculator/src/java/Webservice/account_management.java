/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Webservice;

import account.account;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author sherm
 */
@WebService(serviceName = "account_management")
public class account_management {
    
    ArrayList<account> account_list = new ArrayList<>();
    account current_acc = null;
    
    @WebMethod(operationName = "connection")
    public boolean connection(@WebParam(name = "name") String name, @WebParam(name = "number") int number) {
        return get_account(name, number); 
    }
    
    @WebMethod(operationName = "create_acc")
    public void create_acc(@WebParam(name = "name") String name, @WebParam(name = "number") int number) {
        account acc = new account();
        acc.setName(name);
        acc.setNumber(number);
        account_list.add(acc);
        current_acc = acc;
    }
    
    @WebMethod(operationName = "deposit")
    public void deposit(@WebParam(name = "amount") int amount) {
        current_acc.deposit(amount);
        current_acc.add_deposit(amount);
    }
    
    @WebMethod(operationName = "withdrawal")
    public boolean withdrawal(@WebParam(name = "amount") int amount) {
        boolean good = current_acc.withdrawal(amount);
        if(good){
            current_acc.add_withdrawal(amount);
            return true;
        }else{
            return false;
        }
           
    }
    
    @WebMethod(operationName = "balance_value")
    public int balance_value() {
        return current_acc.getBalance();  
    }
    
    @WebMethod(operationName = "get_depo_history")
    public ArrayList<Integer> get_depo_history() {
        return current_acc.getDeposit_history();  
    }
    
    @WebMethod(operationName = "get_with_history")
    public ArrayList<Integer> get_with_history() {
        return current_acc.getWithdrawal_history();
    }
    
    public boolean get_account(String name, int number){
        boolean good = false;
        for(int i = 0; i < account_list.size(); i++){
            if(account_list.get(i).name.equals(name) && account_list.get(i).number == number){
                current_acc = account_list.get(i);
                good = true;
                break;
            }
        }
        return good;
    }
}
