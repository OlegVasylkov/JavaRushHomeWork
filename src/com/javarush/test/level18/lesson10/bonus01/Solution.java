package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        if (args[0].equals("-e")) cript(args[1], args[2]);
        else if (args[0].equals("-d")) decript(args[1], args[2]);
    }

    public static void cript(String fileName, String fileOutputName) throws IOException
    {
        FileInputStream inputStream = new FileInputStream(fileName);
        FileOutputStream outputStream = new FileOutputStream(fileOutputName);
        while (inputStream.available() > 0) //пока есть еще непрочитанные байты
        {
            int data = inputStream.read(); // прочитать очередной байт в переменную data
            outputStream.write(data<<1); // и записать его во второй поток
        }
    }

    public static void decript(String fileName, String fileOutputName) throws IOException{
        FileInputStream inputStream = new FileInputStream(fileName);
        FileOutputStream outputStream = new FileOutputStream(fileOutputName);
        while (inputStream.available() > 0) //пока есть еще непрочитанные байты
        {
            //outputStream.write(1);
            int data = inputStream.read(); // прочитать очередной байт в переменную data
            outputStream.write(data>>1); // и записать его во второй поток
        }

    }

}
