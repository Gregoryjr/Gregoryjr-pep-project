import Controller.SocialMediaController;
import DAO.AccountDAO;
import io.javalin.Javalin;

/**
 * This class is provided with a main method to allow you to manually run and test your application. This class will not
 * affect your program in any way and you may write whatever code you like here.
 */
public class Main {
    public static void main(String[] args) {
        
        AccountDAO test = new AccountDAO();
        System.out.println(test.getAllAccounts().get(0).getUsername());
        SocialMediaController controller = new SocialMediaController();
        Javalin app = controller.startAPI();
        app.start(8080);
    }
}
