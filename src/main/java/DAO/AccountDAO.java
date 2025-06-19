package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

public List<Account> getAllAccounts(){
    
    Connection connection = ConnectionUtil.getConnection();
    List<Account> aList = new ArrayList<>();
    String query = "Select* from Account";
    
     try { 
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    ResultSet rs = preparedStatement.executeQuery();
    while(rs.next()){
        Account account = new Account(rs.getString("username"), rs.getString("password"));
        aList.add(account);
    }
    return aList;

    
  
        
    } catch (SQLException e) {
        // TODO: handle exception
        
        System.out.println(e.getMessage());
        return null;
    }






}

public Account getAccountById(int id){
Connection connection = ConnectionUtil.getConnection();
String sql = "select* from Account where account_id = ?";

try{
PreparedStatement preparedStatement = connection.prepareStatement(sql);
preparedStatement.setInt(1, id);
ResultSet rs = preparedStatement.executeQuery();
if(rs.next()){

Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
System.out.print("too string: " + account.toString());
return account;
}
else{
    System.out.println("nully");
    return null;}
}
catch(SQLException e ){
    System.out.println(id+" "+e.getMessage());
    return null;

}


}


public Account getAccountByUser(String name){

    Connection connection = ConnectionUtil.getConnection();
    String sql = "select* from Account where username = ?";
try{
    
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, name);
    
        ResultSet rs = preparedStatement.executeQuery();
        if(!rs.next()){
            return null;
        }
        Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
        return account;
    }catch(SQLException e){

        System.out.println(e.getMessage());
        return null;
    }


}

public Account login(String uname, String pass){
Connection connection = ConnectionUtil.getConnection();
String sql = String.format("Select* from account where username = '%s' and password = '%s'", uname, pass );


try{
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ResultSet rs = preparedStatement.executeQuery();
    if(!rs.next()){
        return null;
    }
    else {
        Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
        return account;
    }

}
catch(SQLException e){
    System.out.println(e.getMessage());
    return null;


}


}


public Account register(String user, String pass){
Connection connection = ConnectionUtil.getConnection();
String sql = String.format("INSERT INTO ACCOUNT (username, password) values('%s', '%s')", user, pass);

try{
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
System.out.println("done in dao");
    preparedStatement.executeUpdate();

    return getAccountByUser(user);




}catch(SQLException e){

    System.out.println(e.getMessage());
    return null;
}

}



    
}
