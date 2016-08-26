package com.javarush.test.level07.lesson12.home01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Вывести числа в обратном порядке
Ввести с клавиатуры 10 чисел и заполнить ими список.
Вывести их в обратном порядке.
Использовать только цикл for.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] ms=new int[10];
        for (int i = 0; i<ms.length;i++)ms[i]=Integer.parseInt(reader.readLine());
        for (int i = 0; i<ms.length;i++) System.out.println(ms[ms.length-1-i]);

        //Напишите тут ваш код
    }
}
