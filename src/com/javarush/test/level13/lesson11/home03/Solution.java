package com.javarush.test.level13.lesson11.home03;

/* Чтение файла
1. Считать с консоли имя файла.
2. Вывести в консоль(на экран) содержимое файла.
3. Не забыть освободить ресурсы. Закрыть поток чтения с файла и поток ввода с клавиатуры.
*/

import java.io.*;


public class Solution
{
    public static void main(String[] args) throws IOException
    {
        //add your code here
        String path;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        path = reader.readLine();
        //System.out.println(path);
        InputStream inStream = new FileInputStream(path);
        while (inStream.available() > 0)
        {
            int data = inStream.read(); //читаем один int из потока для чтения
            System.out.print((char)data);
        }
        inStream.close();
        reader.close();
    }
}
