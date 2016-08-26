package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution
{
    public static void main(String[] args) throws IOException
    {

        FileInputStream fileInputStream = new FileInputStream(args[0]);
        String latinChars;
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (char ch = 'A'; ch <= 'z'; ch++)
        {
            stringBuilder.append(ch);
            if (ch == 'Z') ch = 'a' - 1;
        }
        latinChars = stringBuilder.toString();
        while (fileInputStream.available() > 0)
        {
            if (latinChars.contains(Character.toString((char)fileInputStream.read()))) count++;
        }
        System.out.println(count);
        fileInputStream.close();
    }
}
