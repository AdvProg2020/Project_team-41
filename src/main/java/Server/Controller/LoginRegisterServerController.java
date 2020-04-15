package Server.Controller;

public class LoginRegisterServerController {
    private static LoginRegisterServerController single_instance = null;
    public static LoginRegisterServerController getInstance()
    {
        if (single_instance == null)
            single_instance = new LoginRegisterServerController();

        return single_instance;
    }
    public void createAccount(String type,String username,String Password){

    }
    public void login (String username,String password){

    }
    public void logout(){

    }
    private LoginRegisterServerController(){
    }
}
