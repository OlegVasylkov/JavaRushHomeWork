package com.javarush.test.level06.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/* Задача по алгоритмам
Задача: Написать программу, которая вводит с клавиатуры 5 чисел и выводит их в возрастающем порядке.
Пример ввода:
3
2
15
6
17
Пример вывода:
2
3
6
15
17
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int[] list = new int[5];
            for (int i = 0; i < list.length; i++)
            {
                list[i] = Integer.parseInt(reader.readLine());
            }

            for (int j = 0; j < list.length - 1; j++)
            {

                for (int i = 0; i < list.length - 1 - j; i++)
                {
                    if (list[i] > list[i + 1])
                    {
                        int tmp;
                        tmp = list[i];
                        list[i] = list[i + 1];
                        list[i + 1] = tmp;
                    }
                }
            }
            for (int i = 0; i < list.length; i++) System.out.println(list[i]);

            //Напишите тут ваш код
        }
    }

