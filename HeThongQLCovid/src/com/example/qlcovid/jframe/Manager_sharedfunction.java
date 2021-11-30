/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlcovid.jframe;
import java.util.Calendar;
/**
 *
 * @author nhonnhon
 */
public class Manager_sharedfunction {
    int curyear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    int curmonth(){
        return Calendar.getInstance().get(Calendar.MONTH)+1;
    }
    int curday(){
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
    int maxmonth(int year){
        int m = 12;
        if(year == Calendar.getInstance().get(Calendar.YEAR)) m = Calendar.getInstance().get(Calendar.MONTH) + 1;
        return m;
    }
    int maxpastday(int year, int month){
        int maxday = 31;
        if(month == 2){
            if(year%4 ==0 && year%100!=0)  maxday = 29;
            else maxday = 28;
        }
        else if(month == 4 || month == 6 || month == 9 || month == 11)maxday = 30;
        if(month == Calendar.getInstance().get(Calendar.MONTH)+1 && Integer.parseInt(cb_year.getSelectedItem().toString()) == Calendar.getInstance().get(Calendar.YEAR)) maxday = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        return maxday;
    }
    int maxday(int month, int year){
        int maxday = 31;
        if(month == 2){
            if(year%4 ==0 && year%100!=0)  maxday = 29;
            else maxday = 28;
        }
        else if(month == 4 || month == 6 || month == 9 || month == 11)maxday = 30;
        return maxday;
    }
}
