package com.javarush.test.level31.lesson02.home01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его.
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке
2.2.2. переименовать resultFileAbsolutePath в 'allFilesContent.txt'
2.2.3. в allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять "\n"
2.3. Удалить директории без файлов (пустые).
Все файлы имеют расширение txt.
*/
public class Solution {

    public static void main(String[] args) throws IOException
    {
        ArrayList<File> files = new ArrayList<>();
        File folder = new File(args[0]);
        File result = new File(args[1]);
        removeBigFiles(folder, result, files);
        Collections.sort(files, new Comparator<File>()
        {
            @Override
            public int compare(File o1, File o2)
            {
                return o1.getName().compareTo(o2.getName());
            }
        });
        File newResult = new File(result.getParent() + "/allFilesContent.txt");
        result.renameTo(newResult);

        FileOutputStream fileOutputStream = new FileOutputStream(newResult);

        for (int i = 0; i < files.size(); i++)
        {
            FileInputStream fileInputStream = new FileInputStream(files.get(i));
            byte buffer[] = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            fileOutputStream.write(buffer);
            if (i < files.size() - 1)
                fileOutputStream.write("\n".getBytes());
        }
        fileOutputStream.close();
        removeEmptyFolders(folder);
    }

    public static void removeBigFiles(File folder, File notRemove, ArrayList<File> files)
    {
        for (File fileEntry : folder.listFiles())
        {
            if (!fileEntry.equals(notRemove))
            {
                if (fileEntry.isDirectory())
                {
                    removeBigFiles(fileEntry, notRemove, files);
                } else if (fileEntry.length() > 50) fileEntry.delete();
                else files.add(fileEntry);
            }
        }
    }

    public static void removeEmptyFolders(File folder){
        for (File fileEntry : folder.listFiles()){
            if (fileEntry.isDirectory()){
                if (fileEntry.listFiles().length==0)
                    fileEntry.delete();
                else removeEmptyFolders(fileEntry);
            }
        }
    }
}
