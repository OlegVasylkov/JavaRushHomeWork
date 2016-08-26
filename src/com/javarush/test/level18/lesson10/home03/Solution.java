package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать содержимое третьего файла
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileOutputStream outputStream = new FileOutputStream(reader.readLine());
        FileInputStream inputStream = new FileInputStream(reader.readLine());

        FileInputStream inputStream1 = new FileInputStream(reader.readLine());
        reader.close();
        byte[] buffer = new byte[inputStream.available()+inputStream1.available()];
        while (inputStream.available() > 0){
            int count = inputStream.read(buffer);
            outputStream.write(buffer, 0, count);
        }
        while (inputStream1.available()>0){
            int count = inputStream1.read(buffer);
            outputStream.write(buffer, 0,count);
        }
        inputStream.close();
        inputStream1.close();
        outputStream.close();
    }
}
