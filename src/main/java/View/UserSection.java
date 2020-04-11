package View;

public class UserSection {
    private static UserSection single_instance = null;
    private boolean loggedIn;
    public static UserSection getInstance()
    {
        if (single_instance == null)
            single_instance = new UserSection();

        return single_instance;
    }
    public void checkLoggedIn(){
        if(loggedIn){

        }
        else{
            register();
        }
    }
    private void register(){

    }

}
