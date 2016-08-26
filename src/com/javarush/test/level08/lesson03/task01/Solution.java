package com.javarush.test.level08.lesson03.task01;

/* HashSet из растений
Создать коллекцию HashSet с типом элементов String.
Добавить в неё 10 строк: арбуз, банан, вишня, груша, дыня, ежевика, жень-шень, земляника, ирис, картофель.
Вывести содержимое коллекции на экран, каждый элемент с новой строки.
Посмотреть, как изменился порядок добавленных элементов.
*/

import java.util.HashSet;
import java.util.Set;

public class Solution
{
    public static void main(String[] args) throws Exception
    {

        Set<String> veg = new HashSet<String>();
        veg.add("арбуз");
        veg.add("банан");
        veg.add("вишня");
        veg.add("груша");
        veg.add("дыня");
        veg.add("ежевика");
        veg.add("жень-шень");
        veg.add("земляника");
        veg.add("ирис");
        veg.add("картофель");

        for(String x: veg) System.out.println(x);
        //Напишите тут ваш код

    }
}
