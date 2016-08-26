package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Solution
{

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        Map<String, Double> map = new HashMap<String, Double>();
        while (reader.ready())
        {
            String[] s = reader.readLine().split(" ");
            if (map.containsKey(s[0]))
            {
                map.put(s[0],
                        map.get(s[0]) + Double.parseDouble(s[1]));
            } else
            {
                map.put(s[0], Double.parseDouble(s[1]));
            }
        }
        reader.close();
        String name = null;
        double maxValue = 0;
        for (Map.Entry<String, Double> entry : map.entrySet())
        {
            if (entry.getValue() > maxValue){
                name = entry.getKey();
                maxValue = entry.getValue();
            }
        }
        System.out.println(name);
        for (Map.Entry<String, Double> entry : map.entrySet()){
            if (entry.getValue() == maxValue && !entry.getKey().equals(name))
                System.out.println(name);
        }
    }
}

