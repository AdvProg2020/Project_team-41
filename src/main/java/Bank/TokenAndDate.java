package Bank;

import java.util.Date;

public class TokenAndDate {
    private String token;
    private Date date;

    public TokenAndDate(String token, Date date) {
        this.token = token;
        this.date = date;
    }

    public String getToken() {
        return token;
    }

    public Date getDate() {
        return date;
    }
}
