package Client.Controller;

public class SortController {
    private static SortController single_instance = null;
    public static SortController getInstance()
    {
        if (single_instance == null)
            single_instance = new SortController();

        return single_instance;
    }
}
