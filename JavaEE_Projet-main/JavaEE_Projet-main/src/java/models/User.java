/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author hgoyat.ir2023
 */
public class User {
    
    protected int id;
    protected String firstname;
    protected String lastname;
    protected String username;

    public User(){
        
    }
    
    public User(int newID, String newFirstname, String newLastname, String newUsername){
        this.id = newID;
        this.firstname = newFirstname;
        this.lastname = newLastname;
        this.username = newUsername;
    }
    
    public String getFirstname(){
        return this.firstname;
    }
    
    public String getLastname(){
        return this.lastname;
    }
        
    public String getUsername(){
        return this.username;
    }
    
    public int getID(){
        return this.id;
    }
    
    
    public void setFirstname(String newFirstname){
        this.firstname= newFirstname;
    }
    
    public void setLastname(String newLastname){
        this.lastname = newLastname;
    }
        
    public void setUsername(String newUsername){
        this.username = newUsername;
    }
    
    /**
     * Create an SQL Table for the model class
     * Handles itself if the table already exists (spam as you want)
     * @param con 
     */
    public static void createTable(Connection con){
        Statement statement;
        String sql;
        try {
            statement = con.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS Users (\n" +
            "     ID_PK INTEGER NOT NULL AUTO_INCREMENT\n" +
            "    ,username VARCHAR(50) NOT NULL\n" +
            "    ,firstname VARCHAR(50) NOT NULL\n" +
            "    ,lastname VARCHAR(50) NOT NULL\n" +
            "    ,PRIMARY KEY (ID_PK)\n" +
            ");";
            statement.executeUpdate(sql);
                
          
            //con.commit();
        }
        catch (SQLException e){
            System.out.println("createTable : Could not execute statement");
            System.out.println(e);
        }
        finally{
            //System.out.println(result);
        }
    }
    
    public static ArrayList<User> getUsers(Connection con){
        Statement statement;
        ResultSet results;
        ArrayList users = new ArrayList<User>();
        try {
            statement = con.createStatement();
            results = statement.executeQuery(
                "SELECT * FROM Users;"
            );
            while(results.next()){
                users.add(new User(
                        results.getInt("id_pk"),
                        results.getString("username"),
                        results.getString("firstname"),
                        results.getString("lastname")
                ));
            }
        }
        catch (Exception e){
            System.out.println("getUsers : Could not execute statement");
            System.out.println(e);
        }
        /*finally{
            System.out.println(result);
        }*/
        return users;
    }
    
    public void insertUser(Connection con){
        Statement statement;
        String sql;
        try {
            statement = con.createStatement();
            sql = "INSERT INTO Users (username, firstname, lastname) VALUES\n" +
                "('"+this.username+"','"+this.firstname+"','"+this.lastname+"');";
            statement.executeUpdate(sql);
            //con.commit();
        }
        catch (Exception e){
            System.out.println("insertUser : Could not execute statement");
            System.out.println(e);
        }
        /*finally{
            System.out.println(result);
        }*/
        //return results;
    }
    
    public static ArrayList<User> searchUsers(Connection con, String text, int option){
        ArrayList<User> users = new ArrayList<>();
        Statement statement;
        ResultSet results = null;
        try{
            switch(option){
                case 1:
                    statement = con.createStatement();
                    results = statement.executeQuery("SELECT * FROM Users WHERE username LIKE'"+text+"';");  
                    break;
                case 2:
                    statement = con.createStatement();
                    results = statement.executeQuery("SELECT * FROM Users WHERE firstname LIKE'"+text+"';"); 
                    break;
                case 3:
                    statement = con.createStatement();
                    results = statement.executeQuery("SELECT * FROM Users WHERE lastname LIKE'"+text+"';"); 
                    break;
                case 4:
                    statement = con.createStatement();
                    results = statement.executeQuery("SELECT * FROM Users WHERE lastname LIKE'"+text+"' OR firstname LIKE'"+text+"' OR username LIKE '"+text+"';"); 
                    break;
            }
            while(results.next()){
                        users.add(new User(
                        results.getInt("id_pk"),
                        results.getString("username"),
                        results.getString("firstname"),
                        results.getString("lastname")
                        ));
                    }
        }catch (Exception e){
            System.out.println("searchUser : Could not execute statement");
        }
        return users;
    }
}
