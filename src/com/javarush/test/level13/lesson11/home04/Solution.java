package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести абсолютно все введенные строки в файл, каждую строчку с новой стороки.
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args)throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileWriter writer = new FileWriter(reader.readLine());
        //OutputStream a = new FileOutputStream(reader.readLine());
        String s = reader.readLine();
        String textForCopy="";
        while (!s.equals("exit"))
        {
            textForCopy = textForCopy + s + "\r\n";
            s = reader.readLine();
        }

        writer.write(textForCopy+s);

        //byte [] byteMass = textForCopy.substring(0,textForCopy.length()-1).getBytes();
        //a.write(byteMass);
        //a.close();

        reader.close();
        writer.close();
    }
}
