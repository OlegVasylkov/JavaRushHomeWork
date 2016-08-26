package com.javarush.test.level04.lesson16.home04;

/* Меня зовут 'Вася'...
Ввести с клавиатуры строку name.
Ввести с клавиатуры дату рождения (три числа): y, m, d.
Вывести на экран текст:
«Меня зовут name
Я родился d.m.y»
Пример:
Меня зовут Вася
Я родился 15.2.1988
*/
import java.util.Scanner;
public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        int y = sc.nextInt();
        int m = sc.nextInt();
        int d = sc.nextInt();
        System.out.println("Меня зовут "+name);
        System.out.println("Я родился "+d+"."+m+"."+y);

        //Напишите тут ваш код
    }
}

