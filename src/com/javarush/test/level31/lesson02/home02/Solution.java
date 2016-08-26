package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        File folder = new File(root);
        List<String> list = new ArrayList<>();
        Queue<File> queue = new LinkedBlockingQueue<>();
        queue.addAll(Arrays.asList(folder.listFiles()));
        for (File file : queue){
            if (file.isFile())
                list.add(file.getAbsolutePath());
            else queue.addAll(Arrays.asList(file.listFiles()));
        }
        return list;
    }

//    public static void main(String[] args) throws IOException
//    {
//        List<String> list = getFileTree("d:\\Test31\\");
//        for (String file : list)
//            System.out.println(file);
//    }
}
