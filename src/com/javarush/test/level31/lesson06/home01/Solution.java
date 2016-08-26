package com.javarush.test.level31.lesson06.home01;

import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.util.ArrayList;
//import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/tests.zip

Файлы внутри tests.zip:
a.txt
b.txt

После запуска Solution.main архив tests.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution
{
    public static void main(String[] args) throws IOException{
        Map<ZipEntry, ByteArrayOutputStream> map = new HashMap<>();
        try (FileInputStream fileInputStream = new FileInputStream(args[1]);
             ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(fileInputStream))) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count;
                while ((count = zipInputStream.read(buffer)) != -1) {
                    baos.write(buffer, 0, count);
                }
                map.put(zipEntry, baos);
                baos.close();
            }
        }

        File fileToWrite = new File(args[0]);
        ZipEntry zipToWrite = new ZipEntry("new/" + fileToWrite.getName());

        try (FileInputStream fileInputStream = new FileInputStream(fileToWrite)) {
            int length = fileInputStream.available();
            byte[] bytes = new byte[length];
            fileInputStream.read(bytes);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
            byteArrayOutputStream.write(bytes);
            for (ZipEntry zipEntry : map.keySet()) {
                    if (zipEntry.getName().equals(zipToWrite.getName())) {
                    map.remove(zipEntry);
                    break;
                }
            }
            map.put(zipToWrite, byteArrayOutputStream);
            byteArrayOutputStream.close();
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(args[1]);
             ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(fileOutputStream))) {
            for (Map.Entry<ZipEntry, ByteArrayOutputStream> entry : map.entrySet()) {
                zipOutputStream.putNextEntry(entry.getKey());
                zipOutputStream.write(entry.getValue().toByteArray());
                zipOutputStream.closeEntry();
            }
        }
//
//        Path tempZipFile = Files.createTempFile(null, null);
//        List<Path> archiveFiles = new ArrayList<>();
//
//        File fileToWrite = new File(args[0]);
//        ZipEntry zipToWrite = new ZipEntry("new/" + fileToWrite.getName());
//
//        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(tempZipFile))) {
//            try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(args[1]))) {
//
//                ZipEntry zipEntry = zipInputStream.getNextEntry();
//                while (zipEntry != null && !zipEntry.getName().equals(zipToWrite.getName())) {
//                    String fileName = zipEntry.getName();
//                    archiveFiles.add(Paths.get(fileName));
//
//                    zipOutputStream.putNextEntry(new ZipEntry(fileName));
//                    byte[] buffer = new byte[8 * 1024];
//                    int len;
//                    while ((len = zipInputStream.read(buffer)) > 0) {
//                        zipOutputStream.write(buffer, 0, len);
//                    }
//                    zipInputStream.closeEntry();
//                    zipEntry = zipInputStream.getNextEntry();
//                }
//            }
//
//            try(FileInputStream fileInputStream = new FileInputStream(fileToWrite))
//            {
//                zipOutputStream.putNextEntry(zipToWrite);
//                byte[] buffer = new byte[8 * 1024];
//                int len;
//                while ((len = fileInputStream.read(buffer)) > 0) {
//                    zipOutputStream.write(buffer, 0, len);
//                }
//            }
//            zipOutputStream.closeEntry();
//        }
//        Files.move(tempZipFile, Paths.get(args[1]), StandardCopyOption.REPLACE_EXISTING);
    }
}
