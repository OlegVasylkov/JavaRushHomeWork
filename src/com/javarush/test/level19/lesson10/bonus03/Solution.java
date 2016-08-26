package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Solution
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        if (fileName.endsWith(".html"))
        {
            BufferedReader inputStream = new BufferedReader(new FileReader(fileName));
            String data = "";
            String thisLine;
            while ((thisLine = inputStream.readLine()) != null) {data = data + thisLine;}
            inputStream.close();

            String tagSt = "<" + args[0];
            String tagEnd = "</" + args[0] + ">";
            TreeMap<Integer, String> map = new TreeMap<>();
//            String data2 = data;
            int ind = 0;
            while (data.indexOf(tagSt, ind+1)> -1){
                if (ind == 0)
                ind = data.indexOf(tagSt, ind);
                else ind = data.indexOf(tagSt, ind+1);
                map.put(ind, "Start");
            }
            ind = 0;
            while (data.indexOf(tagEnd, ind+1)> -1){
                ind = data.indexOf(tagEnd, ind+1);
                map.put(ind, "End");

            }
//            while (data2.indexOf(tagSt)>=0){
//                if (ind == 0)
//                ind = ind + data2.indexOf(tagSt);
//                else ind = ind + data2.indexOf(tagSt)+1;
//                map.put(ind+1, "Start");
//                data2 = data2.substring(data2.indexOf(tagSt)+1);
//            }
//            ind = 0;
//            data2 = data;
//            while (data2.indexOf(tagEnd)>=0){
//                ind = ind + data2.indexOf(tagEnd) + tagEnd.length();
//                map.put(ind, "End");
//                data2 = data2.substring(data2.indexOf(tagEnd)+tagEnd.length());
//            }
            int count = 0;
//            for (Map.Entry<Integer, String> pair : map.entrySet()){
//                System.out.println(pair.getKey() + " " + pair.getValue());
//            }
            TreeMap<Integer, Integer> mapForPrint = new TreeMap<>();
            for (Map.Entry<Integer, String> tmp : map.entrySet()){
                if (tmp.getValue().equals("Start")){
                    count++;
                    mapForPrint.put(tmp.getKey(), count);
                }
                else {
                    for (Map.Entry<Integer, Integer> tmp2 : mapForPrint.entrySet())
                    {
                        if (tmp2.getValue()== count){
                            mapForPrint.put(tmp2.getKey(), tmp.getKey());
                            break;
                        }
                    }
                    count--;
                }
            }
            for (Map.Entry<Integer, Integer> pair : mapForPrint.entrySet()){
                System.out.println(data.substring(pair.getKey(), pair.getValue()+tagEnd.length()));
            }
        }
    }

}
