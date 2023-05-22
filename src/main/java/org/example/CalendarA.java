package org.example;

import java.util.Calendar;

public class CalendarA {

    int year=2023,month=0;

    //判断这个月有多少天
    public String[] daysOfMonth(){
        String a[]=new String[42];
        Calendar data= Calendar.getInstance();
        data.set(year,month-1,1);
        int week=data.get(Calendar.DAY_OF_WEEK)-1;
        int day=0;
        if (month==1||month==3||month==5||month==7||month==8||month==10||month==12){
            day=31;
        }
        if (month==4||month==6||month==9||month==11){
            day=30;
        }
        if (month==2){
            if (((year%4==0)&&(year%100!=0))||(year%400==0)) {
                day=29;
            }else {
                day=28;
            }
        }
        for (int i=week,n=1;i<week+day;i++){
            a[i]=String.valueOf(n);
            n++;
        }
        return a;
    }

    public void setMonth(int month){
        this.month=month;
    }

    public void setYear(int year){
        this.year=year;
    }

    public static void main(String[] args) {
        new GUI();
    }

}
