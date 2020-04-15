package Client.Controller;

public class LoginRegisterController {
    private static LoginRegisterController single_instance = null;
    public static LoginRegisterController getInstance()
    {
        if (single_instance == null)
            single_instance = new LoginRegisterController();

        return single_instance;
    }
    public void createAccount(String type,String username,String Password){

    }
    public void login (String username,String password){

    }
    public void logout(){

    }
    private LoginRegisterController(){
    }
}
