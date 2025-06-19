package Service;

import java.util.ArrayList;
import java.util.List;
import DAO.AccountDAO;
import Model.Account;
public class AccountService {
    AccountDAO accountDAO = new AccountDAO();
    

public AccountService(){
//accountDAO = this.accountDAO;

}

public Account getAccountById(int id){
return accountDAO.getAccountById(id);


}

public Account getAccountByUser(String user){
    return accountDAO.getAccountByUser(user);
}


public Account login(String user, String pass){

    return accountDAO.login(user, pass);
}


public Account register(String user, String pass){

    return accountDAO.register(user, pass);
}




}
