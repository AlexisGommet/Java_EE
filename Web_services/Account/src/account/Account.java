/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author sherm
 */
public class Account {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean run = true;
        Scanner myObj = new Scanner(System.in);
        while(run){
            System.out.println("Choose an action\n1. Log in\n2. Sign in\n3. Exit");
            int choix = Integer.valueOf(myObj.nextLine());
            switch(choix) {
                case 1:                
                    System.out.println("Enter your name");
                    String name = myObj.nextLine();
                    System.out.println("Enter your account number");
                    int number = Integer.valueOf(myObj.nextLine());
                    while(!connection(name, number)){
                        System.out.println("Incorrect credentials");
                        System.out.println("Enter your name");
                        name = myObj.nextLine();
                        System.out.println("Enter your account number");
                        number = Integer.valueOf(myObj.nextLine());
                    }
                    System.out.println("Successful connection");
                    break;
                case 2:
                    System.out.println("Enter your name");
                    String name2 = myObj.nextLine();
                    System.out.println("Enter your account number");
                    int number2 = Integer.valueOf(myObj.nextLine());
                    createAcc(name2, number2);
                    System.out.println("Account successfully created");
                    break;
                default:
                    run = false;
                    System.out.println("See you soon!");
                    break;
            }
            while(run){
                System.out.println("Choose an action\n1. See account balance\n2. Make a deposit\n"
                        + "3. Make a withdrawal\n4. See deposit history\n5. See withdrawal history\n6. Exit");
                choix = Integer.valueOf(myObj.nextLine());
                switch(choix) {
                    case 1:                
                        System.out.println("\nAccount balance : " + balanceValue() + "\n");
                        break;
                    case 2:
                        System.out.println("Choose an amount to deposit");
                        int amount = Integer.valueOf(myObj.nextLine());         
                        deposit(amount);
                        System.out.println("Deposit successful");
                        System.out.println("Account balance is now : " + balanceValue());
                        break;
                    case 3:
                        System.out.println("Choose an amount to withdraw");
                        int amount2 = Integer.valueOf(myObj.nextLine());
                        boolean bool = withdrawal(amount2);
                        if(!bool){
                            System.out.println("Withdrawal failed, account balance too low");
                            System.out.println("Account balance is : " + balanceValue());
                        }else{
                            System.out.println("Withdrawal successful");
                            System.out.println("Account balance is now : " + balanceValue());
                        }
                        break;
                    case 4:
                        List<Integer> depo = getDepoHistory();
                        int index = 1;
                            if(depo.size() > 0){
                                System.out.println("\nDeposit history");
                                for(int i = 0; i < depo.size(); i++){
                                    System.out.println(index + ". " + depo.get(i));
                                    index++;
                                }
                                System.out.println("");
                            }else{
                                System.out.println("\nNo Deposit History\n");
                            }
                        break;
                    case 5:
                        List<Integer> with = getWithHistory();
                        int index2 = 1;
                            if(with.size() > 0){
                                System.out.println("\nWithdrawal history");
                                for(int i = 0; i < with.size(); i++){
                                    System.out.println(index2 + ". " + with.get(i));
                                    index2++;
                                }
                                System.out.println("");
                            }else{
                                System.out.println("\nNo Withdrawal History\n");
                            }
                        break;
                    default:
                        run = false;
                        System.out.println("See you soon!");
                        break;
                }
            } 
        }
    }

    private static int balanceValue() {
        webservice.AccountManagement_Service service = new webservice.AccountManagement_Service();
        webservice.AccountManagement port = service.getAccountManagementPort();
        return port.balanceValue();
    }

    private static void deposit(int amount) {
        webservice.AccountManagement_Service service = new webservice.AccountManagement_Service();
        webservice.AccountManagement port = service.getAccountManagementPort();
        port.deposit(amount);
    }

    private static boolean withdrawal(int amount) {
        webservice.AccountManagement_Service service = new webservice.AccountManagement_Service();
        webservice.AccountManagement port = service.getAccountManagementPort();
        return port.withdrawal(amount);
    }

    private static boolean connection(java.lang.String name, int number) {
        webservice.AccountManagement_Service service = new webservice.AccountManagement_Service();
        webservice.AccountManagement port = service.getAccountManagementPort();
        return port.connection(name, number);
    }

    private static void createAcc(java.lang.String name, int number) {
        webservice.AccountManagement_Service service = new webservice.AccountManagement_Service();
        webservice.AccountManagement port = service.getAccountManagementPort();
        port.createAcc(name, number);
    }

    private static java.util.List<java.lang.Integer> getDepoHistory() {
        webservice.AccountManagement_Service service = new webservice.AccountManagement_Service();
        webservice.AccountManagement port = service.getAccountManagementPort();
        return port.getDepoHistory();
    }

    private static java.util.List<java.lang.Integer> getWithHistory() {
        webservice.AccountManagement_Service service = new webservice.AccountManagement_Service();
        webservice.AccountManagement port = service.getAccountManagementPort();
        return port.getWithHistory();
    }
    
}
