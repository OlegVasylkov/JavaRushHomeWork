package com.javarush.test.level08.lesson11.home09;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/* Работа с датой
1. Реализовать метод isDateOdd(String date) так, чтобы он возвращал true, если количество дней с начала года - нечетное число, иначе false
2. String date передается в формате MAY 1 2013
Пример:
JANUARY 1 2000 = true
JANUARY 2 2020 = false
*/

public class Solution
{
    public static void main(String[] args)
    {
    }

    public static boolean isDateOdd(String date) throws Exception
    {
        SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
        Date endDate = df.parse(date);
        Date startDate = new Date();
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);
        startDate.setMonth(0);
        startDate.setDate(0);
        startDate.setYear(endDate.getYear());
        long msDay = 24 * 60 * 60 * 1000;
        long msData = endDate.getTime() - startDate.getTime();
        int dayCount = (int) (msData / msDay) + 1;
        if (dayCount % 2 == 0){
            System.out.println(endDate + " = false");
            return false;
        } else {
            System.out.println(endDate + " = true");
            return true;
        }
    }
}
