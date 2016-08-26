package com.javarush.test.level14.lesson08.bonus02;

/* НОД
Наибольший общий делитель (НОД).
Ввести с клавиатуры 2 целых положительных числа.
Вывести в консоль наибольший общий делитель.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());

        if (a>0 && b>0)
        {
            /*int i;
            if (a > b) i = b;
            else i = a;
            for (; i > 0; i--)
            {
                if ((a % i) == 0 && (b % i) == 0) break;
            }
            System.out.println(i);
            */
            int min;
            int max;

            if (a>b){
                min = b;
                max = a;
            }else {
                min = a;
                max = b;
            }
            while (max%min!=0){
                int k = max%min;
                max = min;
                min = k;
            }
            System.out.println(min);
            reader.close();
        }
    }
}
