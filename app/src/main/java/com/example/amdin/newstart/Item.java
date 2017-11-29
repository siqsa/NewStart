package com.example.amdin.newstart;

/**
 * Created by amdin on 2017-11-22.
 */

public class Item {

    String year;
    String month;
    String day;
    String text;
    public Item(String year,String month,String day,String text){
        this.year=year;
        this.month=month;
        this.day=day;
        this.text=text;
    }
    public void setYear(String year){this.year=year;}
    public void setMonth(String month){this.month=month;}
    public void setDay(String day){this.day=day;}
    public void setText(String text){this.text=text;}
    public String getYear(){
        return year;
    }
    public String getMonth(){
        return month;
    }
    public String getDay(){
        return day;
    }
    public String getText(){
        return text;
    }
}
