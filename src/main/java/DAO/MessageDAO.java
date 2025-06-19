package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    public List<Message> getAllMessages(){
    
    Connection connection = ConnectionUtil.getConnection();
    List<Message> aList = new ArrayList<>();
    String query = "Select* from Message";
    
    
     try { 

    PreparedStatement preparedStatement = connection.prepareStatement(query);
    ResultSet rs = preparedStatement.executeQuery();
    

    while(rs.next()){
        Message message = new Message(rs.getInt("posted_by"),rs.getInt("Message_id"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
        aList.add(message);
    }
    System.out.println("done");
    return aList;

    
  
        
    } catch (SQLException e) {
        // TODO: handle exception
        
        System.out.println(e.getMessage());
        return aList;
    }






}

public Message getMessagesById (int aId){

    Connection connection = ConnectionUtil.getConnection();
    String query = String.format("Select* from Message where Message_id  = '%d'", aId);
    List<Message> rsList = new ArrayList<>();
    try{
    PreparedStatement preparedStatement = connection.prepareStatement(query);
     ResultSet rs = preparedStatement.executeQuery();

     if(rs.next()){
         Message message = new Message(rs.getInt("posted_by"),rs.getInt("Message_id"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
         rsList.add(message);
return rsList.get(0);

     }
     else{
        return null;
     }
        
    }
    
    catch (SQLException e){
        System.out.println(e.getMessage());
         Message empty = new Message();
         return empty;
    }
   


}

public void deleteMessage(int id){
Connection connection = ConnectionUtil.getConnection();
String query = String.format("DELETE from message where Message_id = '%d'", id);
try{


PreparedStatement preparedStatement = connection.prepareStatement(query);
 preparedStatement.executeUpdate();
}
catch(SQLException e){
System.out.println( e.getMessage());
}

}


public List <Message> getMessagesByUid (int aId){

    Connection connection = ConnectionUtil.getConnection();
    String query = String.format("Select* from Message where posted_by  = '%d'", aId);
    List<Message> rsList = new ArrayList<>();
    try{
    PreparedStatement preparedStatement = connection.prepareStatement(query);
     ResultSet rs = preparedStatement.executeQuery();

     while(rs.next()){
         Message message = new Message(rs.getInt("posted_by"),rs.getInt("Message_id"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
         rsList.add(message);


     }
    return rsList;
        
    }
    
    catch (SQLException e){
        System.out.println(e.getMessage());
         //Message empty = new Message();
         return null;
    }
   


}


public Message createMessage(Message message){
    /* message_id integer primary key auto_increment,
posted_by integer,
message_text varchar(255),
time_posted_epoch long,
foreign key (posted_by) references Account(account_id)*/

 Connection connection = ConnectionUtil.getConnection();
    String sql = "INSERT INTO Message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";

    try {
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, message.getPosted_by());
        preparedStatement.setString(2, message.getMessage_text());
        preparedStatement.setLong(3, message.getTime_posted_epoch());

        // Tell JDBC to return generated keys
      
        preparedStatement.executeUpdate();

        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            int pk = rs.getInt(1);
            Message mess =  getMessagesById(pk);
            int holder = mess.getMessage_id();
           mess.setMessage_id(mess.getPosted_by());
            mess.setPosted_by(holder);
            return mess;
            
        } else {  
            return null;
        }
    } catch(SQLException e) {
        System.out.println(e.getMessage());
        return null;
    }


}
public Message updateMessageByMessageId(int id, String mess){

    Connection connection = ConnectionUtil.getConnection();
    String sql  = "Update message Set message_text = ? where message_id = ? ";

    try{
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
 preparedStatement.setString(1, mess);
 preparedStatement.setInt(2, id);
 preparedStatement.executeUpdate();
 return getMessagesById(id);


    }
    catch(SQLException e){

        System.out.println(e.getMessage());
        return null;

    }
}

}