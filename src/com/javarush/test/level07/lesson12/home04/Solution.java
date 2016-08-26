package com.javarush.test.level07.lesson12.home04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Вводить с клавиатуры строки, пока пользователь не введёт строку 'end'
Создать список строк.
Ввести строки с клавиатуры и добавить их в список.
Вводить с клавиатуры строки, пока пользователь не введёт строку "end".  "end" не учитывать.
Вывести строки на экран, каждую с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean a = true;
        ArrayList<String> list = new ArrayList<String>();
        for (;a;){
            String b = reader.readLine();
            if (b.equals("end"))a=false;
            else list.add(b);
        }

        for (int i = 0; i<list.size();i++) System.out.println(list.get(i));

        //Напишите тут ваш код
    }
}
