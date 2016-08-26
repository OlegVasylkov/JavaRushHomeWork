package com.javarush.test.level04.lesson16.home02;

/* Среднее такое среднее
Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String i = reader.readLine();
        int a = Integer.parseInt(i);
        String i2 = reader.readLine();
        int b = Integer.parseInt(i2);
        String i3 = reader.readLine();
        int c = Integer.parseInt(i3);
        if (a>b){
            if(b>c)
                System.out.println(b);
            else if (a>c)
                System.out.println(c);
            else
                System.out.println(a);
        }
        else if (b>c){
            if (c>a)
                System.out.println(c);
            else System.out.println(a);
        }
        else System.out.println(c);
    }


}



