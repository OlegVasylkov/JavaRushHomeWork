package com.javarush.test.level18.lesson05.task02;

/* Подсчет запятых
С консоли считать имя файла
Посчитать в файле количество символов ',', количество вывести на консоль
Закрыть потоки. Не использовать try-with-resources

Подсказка: нужно сравнивать с ascii-кодом символа ','
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        int count = 0;
        FileInputStream inputStream = new FileInputStream(fileName);
        while (inputStream.available() > 0)
        {
            if (((char)inputStream.read()) ==',')count++;
        }
        inputStream.close();
        System.out.println(count);
    }
}
