import java.util.ArrayList;

public class User {
    private int wins;
    private int draws;
    private int losses;
    private int surrenderLosses;
    private int surrenderWins;
    private String password;
    private String username;
    public static ArrayList<User> allUsers = new ArrayList<>();

    @Override
    public String toString() {
        return username + " " + getScore() + " " + (wins+surrenderWins) + " " + draws + " " + (losses+surrenderLosses) ;
    }

    public User(String username, String password){
        this.setUsername(username);
        this.setPassword(password);
        User.allUsers.add(this);

    }

    public int getScore() {
        return (wins * 3 + surrenderWins * 2 + draws + surrenderLosses * -1);
    }

    public int getWins() {
        return wins;
    }

    public void addWins() {
        this.wins++;
    }

    public int getDraws() {
        return draws;
    }

    public void addDraws() {
        this.draws++;
    }

    public int getLosses() {
        return losses;
    }

    public void addLosses() {
        this.losses++;
    }

    public int getSurrenderLosses() {
        return surrenderLosses;
    }

    public void addSurrenderLosses() {
        this.surrenderLosses++;
    }

    public int getSurrenderWins() {
        return surrenderWins;
    }

    public void addSurrenderWins() {
        this.surrenderWins++;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public static User allUsersContainsUsername(String username){
        for (User user : User.allUsers) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    public static void listUsers(){
        ArrayList<String> users = new ArrayList<>();
        for (User user : User.allUsers) {
            if(!users.contains(user.getUsername()))
                users.add(user.getUsername());
        }
        users.sort(null);
        for (String user : users) {
            System.out.println(user);
        }

    }

}
