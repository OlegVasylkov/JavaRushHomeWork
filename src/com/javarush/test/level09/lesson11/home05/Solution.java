package com.javarush.test.level09.lesson11.home05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/* Гласные и согласные буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа должна вывести на экран две строки:
1. первая строка содержит только гласные буквы
2. вторая - только согласные буквы и знаки препинания из введённой строки.
Буквы соединять пробелом.

Пример ввода:
Мама мыла раму.
Пример вывода:
а а ы а а у
М м м л р м .
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char [] mass = reader.readLine().toCharArray();
        LinkedList<Character> vowelList = new LinkedList<Character>();
        LinkedList<Character> notVowelList = new LinkedList<Character>();
        for (int i = 0; i<mass.length;i++){
            if (mass[i] != ' '){
            if (isVowel(mass[i]))vowelList.add(mass[i]);
            else notVowelList.add(mass[i]);
            }
        }
        for (char a: vowelList) System.out.print(a + " ");
        System.out.println();
        for (char b: notVowelList) System.out.print(b + " ");
        //Написать тут ваш код
    }


    public static char[] vowels = new char[]{'а', 'я', 'у', 'ю', 'и', 'ы', 'э', 'е', 'о', 'ё'};

    //метод проверяет, гласная ли буква
    public static boolean isVowel(char c)
    {
        c = Character.toLowerCase(c);  //приводим символ в нижний регистр - от заглавных к строчным буквам

        for (char d : vowels)   //ищем среди массива гласных
        {
            if (c == d)
                return true;
        }
        return false;
    }
}
