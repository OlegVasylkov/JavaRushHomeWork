package com.javarush.test.level16.lesson13.home09;

/* Только по-очереди!
1. В классе Solution создать нить public static Read3Strings унаследовавшись от Thread.
2. В методе run реализовать чтение с консоли трех строк.
3. Три подряд введенных строки должны считываться в одной нити и объединяться в одну строку через пробел.
4. В методе main вывести результат для каждой нити.
5. Используйте join

Пример:
Входные данные
a
b
c
d
e
f
Выходные данные:
a b c
d e f
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static volatile BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws InterruptedException {
        Read3Strings t1 = new Read3Strings();
        Read3Strings t2 = new Read3Strings();
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        System.out.println(t1.toString());
        System.out.println(t2.toString());
    }
    private static class Read3Strings extends Thread
    {
        String string;
        @Override
        public void run()
        {
            try
            {
                string = reader.readLine();
                for (int i = 0; i < 2; i++)
                {
                    string = string + " " + reader.readLine();
                }
            }catch (IOException e){
            }
                        // нужно сделать что б этот ресурс был заблокирован
        }
        @Override
        public String toString()
        {
            return string;
        }
    }
}