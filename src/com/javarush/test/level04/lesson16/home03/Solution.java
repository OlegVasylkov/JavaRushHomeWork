package com.javarush.test.level04.lesson16.home03;

/* Посчитать сумму чисел
Вводить с клавиатуры числа и считать их сумму. Если пользователь ввел -1, вывести на экран сумму и завершить программу.  -1 должно учитываться в сумме.
*/
import java.io.BufferedReader;
        import java.io.InputStreamReader;
public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int sum=0;
        for (;true;)
        {
            String i = reader.readLine();
            int a = Integer.parseInt(i);
            sum = sum + a;

            if (i.equals("-1"))
            {
                System.out.println(sum);
                break;
            }
        }
    }
}
