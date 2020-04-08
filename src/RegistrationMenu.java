import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationMenu {
    static void menu() {
        String command;
        Matcher matcher;
        while (true) {
            command = Main.scanner.nextLine();
            if((matcher = getMatcher(command, "register (\\S+) (\\S+)")).matches()){
                register(matcher.group(1),matcher.group(2));
            }
            else if((matcher = getMatcher(command, "login (\\S+) (\\S+)")).matches()){
                login(matcher.group(1),matcher.group(2));
            }
            else if((matcher = getMatcher(command, "remove (\\S+) (\\S+)")).matches()){
                remove(matcher.group(1),matcher.group(2));
            }

            else if (command.equals("list_users"))
                listUsers();
            else if (command.equals("help"))
                help();
            else if (command.equals("exit")) {
                Main.scanner.close();
                exit();

            }
            else
                System.out.println("invalid command");

        }
    }

     static void register(String username,String password){
        boolean incorrectFormat;
        incorrectFormat = formatCheck("username",username);
        if(incorrectFormat)
            menu();
        incorrectFormat = formatCheck("password",password);
        if(incorrectFormat)
            menu();
        if(User.allUsersContainsUsername(username) != null){
             System.out.println("a user exists with this username");
             menu();
         }
        System.out.println("register successful");
        new User(username,password);
        menu();

    }
     static void login(String username,String password){
        boolean incorrectFormat;
        User foundUser;
         incorrectFormat = formatCheck("username",username);
         if(incorrectFormat)
             menu();
         incorrectFormat = formatCheck("password",password);
         if(incorrectFormat)
             menu();
        foundUser=User.allUsersContainsUsername(username);
        if(foundUser == null){
            System.out.println("no user exists with this username");
            menu();
        }
         assert foundUser != null;
         if(foundUser.getPassword().equals(password)) {
            System.out.println("login successful");
            Menu.mainUser = username;
            Menu.menu();

        }
        else{
            System.out.println("incorrect password");
            menu();
        }

    }
     static void remove(String username,String password){
        boolean incorrectFormat;
        User foundUser;
         incorrectFormat = formatCheck("username",username);
         if(incorrectFormat)
             menu();
         incorrectFormat = formatCheck("password",password);
         if(incorrectFormat)
             menu();
         foundUser=User.allUsersContainsUsername(username);
         if(foundUser == null){
             System.out.println("no user exists with this username");
             menu();
         }

         assert foundUser != null;
         if(foundUser.getPassword().equals(password)) {
            System.out.println("removed " + username + " successfully");
            User.allUsers.remove(foundUser);
         }
        else{
            System.out.println("incorrect password");
         }
         menu();

     }
     static void listUsers(){
        User.listUsers();
         menu();

    }
     static void help(){
         System.out.println("register [username] [password]\n" +
                 "login [username] [password]\n" +
                 "remove [username] [password]\n" +
                 "list_users\n" +
                 "help\n" +
                 "exit");
         menu();
     }
     static void exit(){
        System.out.println("program ended");
        System.exit(0);
     }

    private static Matcher getMatcher(String input, String regex){
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
    private static boolean formatCheck(String userOrPass,String toBeChecked){
        Matcher matcher;
        matcher = getMatcher(toBeChecked, "[A-Za-z0-9_]+");
        if(matcher.matches()) {
           return false;
        }
        else {
            System.out.println(userOrPass + " format is invalid");
            return true;
        }
    }

}
