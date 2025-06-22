package Controller;
import java.util.ArrayList;
import java.util.List;
//import DAO.MessageDAO
import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import com.fasterxml.jackson.databind.JsonNode;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("/messages", ctx->{

        MessageService mt = new MessageService();
         System.out.println("oyo");
        List<Message> list = mt.getAllMessage();
       //System.out.println(list.get(0).getMessage_text());
       System.out.println("yo");
        ctx.json(list);
       // ctx.status(200).result("200");
        });

/// ________________________________

    app.get("/messages/{id}", ctx->{
       String id =  ctx.pathParam("id");
        int number = Integer.parseInt(id);

        MessageService mt = new MessageService();
         System.out.println("oyo");
        Message list = mt.getMessagesById(number);
       //System.out.println(list.get(0).getMessage_text());
       System.out.println("yo");
    if( list!= null){
        ctx.json(list);
    }
       // ctx.status(200).result("200");
        }





        
        );


//____________________________________________
            app.delete("/messages/{id}", ctx->{
       String id =  ctx.pathParam("id");
        int number = Integer.parseInt(id);

        MessageService mt = new MessageService();
         System.out.println("oyo");
        Message message = mt.deleteMessage(number);
       //System.out.println(list.get(0).getMessage_text());
       System.out.println("yo");
       if(message != null){
        //System.out.println("opted");
       ctx.json(message);
       }



            }
        
        );


    //______________________________________________________

        app.get("accounts/{account_id}/messages", ctx->{
       String id =  ctx.pathParam("account_id");
        int number = Integer.parseInt(id);

        MessageService mt = new MessageService();
         System.out.println("oyo");
        List<Message> list = mt.getMessagesByUid(number);
       //System.out.println(list.get(0).getMessage_text());
       System.out.println("yo");
    if( list!= null){
        ctx.json(list);
    }
       // ctx.status(200).result("200");
        }





        
        );
//______________________________________________________

            app.post("/messages", ctx->{
       System.out.println("post message point seen");
        
       Message newMessage = ctx.bodyAsClass(Message.class);
       
       int len =newMessage.getMessage_text().length();
       System.out.println(newMessage.getMessage_text());
       AccountService as = new AccountService();
       //System.out.println(as.getAccountById(newMessage.posted_by).toString());

       if( len<=255 && len>0 && as.getAccountById(newMessage.getPosted_by())!= null){
        MessageService mt = new MessageService();
         System.out.println("account found");
         System.out.println(newMessage.getMessage_text());
        Message message = mt.createMessage(newMessage); 
       // ctx.status(200);
        
       //System.out.println(list.get(0).getMessage_text());
       
       if(message != null){
        System.out.println("y7o");
        System.out.println(message.getMessage_text());
       
       ctx.json(message);
       
       }
       
    }else{ 
        
    ctx.status(400);

System.out.println("bad");
System.out.println(len);
}
            }
        
        );
//__________________________________________________________________

            app.patch("/messages/{message_id}", ctx->{
                 int id =  Integer.parseInt(ctx.pathParam("message_id"));
                
                 System.out.println("found endpoint");
                 JsonNode jsonNode = ctx.bodyAsClass(JsonNode.class);
                String mess = jsonNode.get("message_text").asText();
              
                 MessageService ms = new MessageService();
                    

                 if(mess.length()>0&& mess.length()<=255&& ms.getMessagesById(id)!=null){
                    
                    
                    ctx.json(ms.updateMessageByMessageId(id, mess));


                 }
                 else
                 {
                    ctx.status(400);
                 }
                
                
                
                
                
                
                
                
            });


//____________________________________________________________________________________________

 app.post("/login", ctx->{
                
                 System.out.println("found endpoint login");
                 JsonNode jsonNode = ctx.bodyAsClass(JsonNode.class);
                 AccountService as = new AccountService();

                 String user = jsonNode.get("username").asText();
                 String pass = jsonNode.get("password").asText();
                
                 if(as.login(user, pass)== null){

                    ctx.status(401);


                 }
                 else{

                    ctx.json(as.login(user, pass));
                 }
                 


 });


 //________________________________________________________________________________________________

  app.post("/register", ctx->{
                
                 System.out.println("found endpoint reg");
                 JsonNode jsonNode = ctx.bodyAsClass(JsonNode.class);
                 AccountService as = new AccountService();

                 String user = jsonNode.get("username").asText();
                 String pass = jsonNode.get("password").asText();
                 //System.out.println(user);
                 //System.out.println(pass);
                
                 if(as.getAccountByUser(user) !=null || pass.length()<4|| user == ""){
                    System.out.println("dup or length");
                    ctx.status(400);
                    //ctx.json(null);
                    
                 }
                 else{
                    System.out.println("golden");
                    ctx.json(as.register(user, pass));
                 }
                 


 });









        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }


}