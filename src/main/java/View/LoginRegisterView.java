package View;

public class LoginRegisterView {
    private static LoginRegisterView single_instance = null;
    public static LoginRegisterView getInstance()
    {
        if (single_instance == null)
            single_instance = new LoginRegisterView();

        return single_instance;
    }


}
