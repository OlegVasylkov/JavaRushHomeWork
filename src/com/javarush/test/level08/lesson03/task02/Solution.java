package com.javarush.test.level08.lesson03.task02;

/* HashMap из 10 пар
Создать коллекцию HashMap<String, String>, занести туда 10 пар строк:
арбуз – ягода, банан – трава, вишня – ягода, груша – фрукт, дыня – овощ, ежевика – куст, жень-шень – корень, земляника – ягода, ирис – цветок, картофель – клубень.
Вывести содержимое коллекции на экран, каждый элемент с новой строки.
Пример вывода (тут показана только одна строка):
картофель – клубень
*/

import java.util.HashMap;
import java.util.Map;

public class Solution
{
    public static void main(String[] args) throws Exception
    {

        Map<String, String> veg = new HashMap<String, String>();

        veg.put("арбуз", "ягода");
        veg.put("банан", "трава");
        veg.put("вишня", "ягода");
        veg.put("груша", "фрукт");
        veg.put("дыня", "овощ");
        veg.put("ежевика", "куст");
        veg.put("жень-шень", "корень");
        veg.put("земляника", "ягода");
        veg.put("ирис", "цветок");
        veg.put("картофель", "клубень");

        for (Map.Entry<String,String> x: veg.entrySet()){
            String key = x.getKey();
            String value = x.getValue();
            System.out.println(key + " - " + value);
        }
        //Напишите тут ваш код

    }
}
