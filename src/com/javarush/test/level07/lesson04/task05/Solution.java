package com.javarush.test.level07.lesson04.task05;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Один большой массив и два маленьких
1. Создать массив на 20 чисел.
2. Ввести в него значения с клавиатуры.
3. Создать два массива на 10 чисел каждый.
4. Скопировать большой массив в два маленьких: половину чисел в первый маленький, вторую половину во второй маленький.
5. Вывести второй маленький массив на экран, каждое значение выводить с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        int[] big = new int[20];
        int[] small1=new int[big.length/2];
        int[] small2=new int[big.length/2];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < big.length; i++){
        big[i]=Integer.parseInt(reader.readLine());
        }

        for (int i = 0; i < (big.length/2); i++){
            small1[i]=big[i];
        }

        int k = big.length/2;
        for (int i = (big.length/2); i < big.length; i++){
            small2[i-k]=big[i];
            System.out.println(small2[i-k]);
        }

        //Напишите тут ваш код

    }
}
