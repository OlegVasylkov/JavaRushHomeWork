package com.javarush.test.level07.lesson06.task01;

/* 5 различных строчек в списке
1. Создай список строк.
2. Добавь в него 5 различных строчек.
3. Выведи его размер на экран.
4. Используя цикл выведи его содержимое на экран, каждое значение с новой строки.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws Exception
    {

        ArrayList<String> list=new ArrayList<String>();
        list.add("s");
        list.add("sf");
        list.add("sa");
        list.add("sg");
        list.add("ss");
        System.out.println(list.size());


       /* BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            for(int i = 0; i<5; i++){
            list.add(reader.readLine());
            }*/
        for (int i = 0; i< list.size(); i++){
            System.out.println(list.get(i));
        }

        //Напишите тут ваш код

    }
}
