package Server.Controller.UserSectionController;

import Client.Models.Off;
import Client.Models.Product;
import ir.huri.jcal.JalaliCalendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class TimeControl {
    public static JalaliCalendar convertGregorianToJalali(Date date){
        JalaliCalendar jalaliCalendar = new JalaliCalendar();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        jalaliCalendar.fromGregorian(gregorianCalendar);
        return jalaliCalendar;
    }
    public static Date convertJalaliToGregorian(String year, String month, String day, String hour, String minute, String second){

        JalaliCalendar jalaliCalendar = new JalaliCalendar();
        Date date = new Date();
        jalaliCalendar.set(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
        date.setTime(jalaliCalendar.toGregorian().getTimeInMillis()+1000*((Integer.parseInt(hour)*60*60)+Integer.parseInt(minute)*60+Integer.parseInt(second)));
        return date;
    }
    public static Date getDateByDateTime(String[] dateTime){
        String givenDate;
        String givenTime;
        String[] dayMonthYear;
        String[] hourMinuteSecond;
        givenDate = dateTime[0];
        givenTime = dateTime[1];
        dayMonthYear = givenDate.split("/");
        hourMinuteSecond = givenTime.split(":");
        Date exactDate = convertJalaliToGregorian(dayMonthYear[0],dayMonthYear[1],dayMonthYear[2],hourMinuteSecond[0],hourMinuteSecond[1],hourMinuteSecond[2]);
        return exactDate;
    }


}
