package Service;
import java.util.ArrayList;
import java.util.List;
import DAO.MessageDAO;
import Model.Message;

public class MessageService {
private MessageDAO messageDAO;

public MessageService(){
this.messageDAO = new MessageDAO();

}

public MessageService(MessageDAO messageDAO){
    this.messageDAO = messageDAO;
}


public List<Message> getAllMessage(){

    return messageDAO.getAllMessages();
}
    
public Message getMessagesById(int aId){

    return messageDAO.getMessagesById(aId);
}

public Message deleteMessage(int id){
    Message message = getMessagesById(id);

    messageDAO.deleteMessage(id);
    return message;
}
public List<Message> getMessagesByUid (int aId){

return messageDAO.getMessagesByUid(aId);



}

public Message createMessage(Message message){
return messageDAO.createMessage(message);


}
public Message updateMessageByMessageId(int id, String mess){

    return messageDAO.updateMessageByMessageId(id, mess);
}

    
}
