package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TreeSet<String> filesName = new TreeSet<String>();
        while (true){
            String fileName = reader.readLine();
            if (fileName.equals("end"))break;
            filesName.add(fileName);
        }
        reader.close();
        int index = filesName.first().indexOf(".part");
        String resultFileName = filesName.first().substring(0, index);
        FileOutputStream outputStream = new FileOutputStream(resultFileName);
        while (filesName.size() > 0){
            FileInputStream inputStream = new FileInputStream(filesName.first());
            while (inputStream.available()>0){
                byte[] buffer = new byte[inputStream.available()];
                int count = inputStream.read(buffer);
                outputStream.write(buffer, 0, count);

            }
            inputStream.close();
            filesName.remove(filesName.first());
        }
        outputStream.close();

    }
}
