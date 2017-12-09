package com.example.amdin.newstart;
import java.util.ArrayList;
/**
 * Created by amdin on 2017-11-22.
 */
public class Item {
    int year;
    int month;
    int day;
    String text;

    public Item(int year, int month, int day, String text) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.text = text;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getText() {
        return text;
    }

    public static ArrayList<Item> createContactsList(int numContacts) {
        ArrayList<Item> contacts = new ArrayList<Item>();
//        contacts.add(new Item("2017년","11월","29일","오늘은 팀프로젝트를 하러 왔다"));
//        contacts.add(new Item("2017년","11월","30일","내일은 끔찍한 목요일"));
//        contacts.add(new Item("2017년","12월","01일","벌써 12월이다."));
        return contacts;
    }
}
