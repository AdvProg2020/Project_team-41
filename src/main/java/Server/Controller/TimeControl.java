package Server.Controller;

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
    public static Date convertJalaliToGregorian(String year, String month, String day, String hour, String minute, String second) throws Exception {
        int yearInt,monthInt,dayInt,hourInt ,minuteInt,secondInt;
        try {
            yearInt = Integer.parseInt(year);
            monthInt = Integer.parseInt(month);
            dayInt = Integer.parseInt(day);
            hourInt = Integer.parseInt(hour);
            minuteInt = Integer.parseInt(minute);
            secondInt = Integer.parseInt(second);
        } catch (NumberFormatException e) {
            throw new Exception("Oh for god sake enter number!");
        }
        if((monthInt>12) || (monthInt<1) || (dayInt<1) || (dayInt>30) || (hourInt>24) || (hourInt<0) || (minuteInt>59) || (minuteInt<0) || (secondInt>59) || (secondInt<0))
            throw new Exception("Sigh, you entered one of these wrong : day,month,year,hour,minute,second");
        JalaliCalendar jalaliCalendar = new JalaliCalendar();
        Date date = new Date();
        jalaliCalendar.set(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
        date.setTime(jalaliCalendar.toGregorian().getTimeInMillis()+1000*((Integer.parseInt(hour)*60*60)+Integer.parseInt(minute)*60+Integer.parseInt(second)));
        return date;
    }
    public static Date getDateByDateTime(String[] dateTime) throws Exception {
        String givenDate;
        String givenTime;
        String[] dayMonthYear;
        String[] hourMinuteSecond;
        givenDate = dateTime[0];
        givenTime = dateTime[1];
        dayMonthYear = givenDate.split("/");
        hourMinuteSecond = givenTime.split(":");
        Date exactDate = convertJalaliToGregorian(dayMonthYear[2],dayMonthYear[1],dayMonthYear[0],hourMinuteSecond[0],hourMinuteSecond[1],hourMinuteSecond[2]);
        return exactDate;
    }
    public static String getJalaliDateAndTimeForPrint(Date date){
        return convertGregorianToJalali(date).toString() + "\t" +date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
    }


}
