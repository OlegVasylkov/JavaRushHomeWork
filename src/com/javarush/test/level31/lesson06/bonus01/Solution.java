package com.javarush.test.level31.lesson06.bonus01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/tests.zip.003
C:/pathToTest/tests.zip.001
C:/pathToTest/tests.zip.004
C:/pathToTest/tests.zip.002
*/
public class Solution {
    public static void main(String[] args) throws IOException
    {
        File resultFileName = new File(args[0]);
        List<File> list = new ArrayList<>();
        for (int i = 1; i < args.length; i++){
            list.add(new File(args[i]));
        }
        Collections.sort(list);

        byte[] arr = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for(File f : list){
            FileInputStream fis = new FileInputStream(f);
            while(fis.read(arr)>-1){
                baos.write(arr);
                baos.flush();
            }
        }

        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(baos.toByteArray()));
        FileOutputStream fosLoc = new FileOutputStream(resultFileName);
        ZipEntry ze = zis.getNextEntry();
        while(ze!=null){
            int count;
            while((count=zis.read(arr))>-1){
                fosLoc.write(arr,0,count);
                fosLoc.flush();
            }
            ze = zis.getNextEntry();
        }
        fosLoc.close();
    }
}
