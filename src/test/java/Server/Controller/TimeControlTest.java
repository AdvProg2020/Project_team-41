package Server.Controller;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TimeControlTest {

    @Test
    void convertJalaliToGregorianTest() {

        try {
            Date date = TimeControl.convertJalaliToGregorian("1399","2","27","21","17","0");
            assertEquals(date.toString(),"Sat May 16 21:17:00 IRDT 2020");
        } catch (Exception e) {
            fail();
        }
    }
}