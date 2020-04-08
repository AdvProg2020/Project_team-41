import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class UsersComparator implements Comparator<User> {
    public int compare(User first, User second)
    {
        int difference;
        if((difference = first.getScore()-second.getScore()) == 0){
            if((difference = (first.getWins()+first.getSurrenderWins()-second.getWins()-second.getSurrenderWins())) == 0){
                if((difference = (first.getDraws()-second.getDraws())) == 0){
                    if((difference = (second.getLosses()+second.getSurrenderLosses()-first.getLosses()-first.getSurrenderLosses())) == 0) {
                        return (first.getUsername().compareTo(second.getUsername()));
                    }
                }

            }
        }
        return -difference;
    }

}

public class Menu {
    public static String mainUser;
    public static void menu(){
            String command;
            Matcher matcher;

            while (true) {
                    command = Main.scanner.nextLine();
                if((matcher = getMatcher(command, "new_game (\\S+) ([-+]?\\d+)")).matches()){
                    newGame(matcher.group(1),Integer.parseInt(matcher.group(2)));
                }
                else if (command.equals("scoreboard"))
                    scoreboard();
                else if (command.equals("list_users"))
                    listUsers();
                else if (command.equals("help"))
                    help();
                else if (command.equals("logout"))
                    logout();
                else
                    System.out.println("invalid command");

            }
        }

    private static void newGame(String username,int limit){
        boolean incorrectFormat;

        incorrectFormat = formatCheck(username);
        if(incorrectFormat)
            menu();
        else if(limit<0) {
            System.out.println("number should be positive to have a limit or 0 for no limit");
            menu();
        }
        else if(username.equals(mainUser)){
            System.out.println("you must choose another player to start a game");
            menu();
        }
        else if(User.allUsersContainsUsername(username) == null){
            System.out.println("no user exists with this username");
            menu();
        }
        else{
            System.out.println("new game started successfully between "+mainUser+" and "+username+" with limit "+limit);
            new Game(mainUser,username,limit).initializeBoard();
            menu();
        }

    }
    private static void scoreboard(){
        User.allUsers.sort(new UsersComparator());
        for (User user : User.allUsers) {
            System.out.println(user);
        }
    }
    private static void listUsers(){
       User.listUsers();
       menu();

    }
    private static void help(){
        System.out.println("new_game [username] [limit]\n" +
                "scoreboard\n" +
                "list_users\n" +
                "help\n" +
                "logout");
        menu();
    }
    private static void logout(){
        System.out.println("logout successful");
        RegistrationMenu.menu();
    }
    private static Matcher getMatcher(String input, String regex){
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
    private static boolean formatCheck(String toBeChecked){
        Matcher matcher;
        matcher = getMatcher(toBeChecked, "[A-Za-z0-9_]+");
        if(matcher.matches()) {
            return false;
        }
        else {
            System.out.println("username format is invalid");
            return true;
        }
    }
}
