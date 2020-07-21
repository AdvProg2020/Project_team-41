package Server.Controller;

import java.util.Random;

public class RandomNumberGenerator {
    private static final Random random = new Random();
    private static final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";
    private static final String NUMBERS="123456789";

    public static String getToken(int length) {
        StringBuilder token = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            token.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return token.toString();
    }
    public static String getIdNumber(int length){
        StringBuilder id=new StringBuilder(length);
        for (int i = 0; i <length ; i++) {
            id.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        }
        return id.toString();
    }
}
