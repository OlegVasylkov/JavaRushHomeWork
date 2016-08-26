package com.javarush.test.level22.lesson09.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример, "мор"-"ром", "трос"-"сорт"
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        BufferedReader reader1 = new BufferedReader(new FileReader(fileName));
        StringBuilder stringBuilder = new StringBuilder();
        while (reader1.ready()){
            stringBuilder = stringBuilder.append(reader1.readLine()).append(" ");
        }
        String s = stringBuilder.toString();
        String[] words = s.split(" ");
        for (int i = 0; i < words.length-1; i++){
            for (int j = i+1; j < words.length; j++){
                if (words[i].equals(new StringBuilder(words[j]).reverse().toString()))
                {
                    Pair p = new Pair();
                    p.first = words[i];
                    p.second = words[j];
                    result.add(p);
                    break;
                }
            }
        }
        for (Pair p : result){
            System.out.println(p);
        }

    }

    public static class Pair {
        String first;
        String second;

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
