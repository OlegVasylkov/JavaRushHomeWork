package com.javarush.test.level08.lesson11.bonus01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* Номер месяца
Программа вводит с клавиатуры имя месяца и выводит его номер на экран в виде: «May is 5 month».
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        ArrayList<String> months = new ArrayList<String>();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        int n = 0;
        Iterator<String> iterator = months.iterator();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        String s = reader.readLine();

        while (iterator.hasNext()) {
            if (months.contains(s))
                n = months.indexOf(s) + 1;
            break;
        }

        System.out.println(s + " is " + n + " month");
        //add your code here - напиши код тут
    }

}
