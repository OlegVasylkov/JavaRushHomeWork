package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран частоту встречания пробела. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
4. Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        FileInputStream inputStream = new FileInputStream(args[0]);
        int countTotal = inputStream.available();
       // System.out.println(countTotal);
        int count = 0;
        while (inputStream.available()>0){
            if ((char)inputStream.read() == '\u0020')count++;

        }

        System.out.print(String.format("%.2f%n", count * 100.00 / countTotal));
        inputStream.close();
    }
}
