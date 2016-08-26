package com.javarush.test.level19.lesson10.bonus01;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
[Файл 1]
строка1
строка2
строка3

[Файл 2]
строка1
строка3
строка4

[Результат - список lines]
SAME строка1
REMOVED строка2
SAME строка3
ADDED строка4
*/

public class Solution
{
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader1 = new BufferedReader(new FileReader(reader.readLine()));
        BufferedReader reader2 = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();


        while (reader1.ready() && reader2.ready())
        {
            String line11 = reader1.readLine();
            String line21 = reader2.readLine();
            String line12 = null;
            String line22 = null;
            while (reader1.ready() && reader2.ready())
            {
                line12 = reader1.readLine();
                line22 = reader2.readLine();
                if (line11.equals(line21))
                {
                    lines.add(new LineItem(Type.SAME, line11));
                    line11 = line12;
                    line21 = line22;
                } else if (line11.equals(line22))
                {
                    lines.add(new LineItem(Type.ADDED, line21));
                    lines.add(new LineItem(Type.SAME, line11));
                    line11 = line12;
                    line21 = reader2.readLine();
                } else
                {
                    lines.add(new LineItem(Type.REMOVED, line11));
                    lines.add(new LineItem(Type.SAME, line12));
                    line11 = reader1.readLine();
                    line21 = line22;
                }
            }
            if (line11 == null) lines.add(new LineItem(Type.ADDED, line21));
            else if (line21 == null) lines.add(new LineItem(Type.REMOVED, line11));
            else
            {
                lines.add(new LineItem(Type.SAME, line11));
                if (reader1.ready()) lines.add(new LineItem(Type.REMOVED, reader1.readLine()));
                else if (reader2.ready()) lines.add(new LineItem(Type.ADDED, reader2.readLine()));
            }
        }
        reader1.close();
        reader2.close();
        //for (LineItem a : lines) System.out.println(a.line + a.type);
    }

    public static enum Type
    {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem
    {
        public Type type;
        public String line;

        public LineItem(Type type, String line)
        {
            this.type = type;
            this.line = line;
        }
    }
}
