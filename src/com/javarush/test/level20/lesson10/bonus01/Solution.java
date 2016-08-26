package com.javarush.test.level20.lesson10.bonus01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution
{
    public static int[] getNumbers(int N)
    {
        int[] result = null;
        ArrayList<Integer> list = new ArrayList<>();
        int length = (int) Math.log10(N) + 1;

        int[][] massData = new int[length+1][10];
        for (int i = 1; i < 10; i++)
        {
            for (int j = 1; j < length+1; j++)
            {
                massData[j][i] = (int) Math.pow((double) i, (double) j);
            }
        }

        System.out.println(Arrays.deepToString(massData));

        for (int i = 1; i < N; i++)
        {
            int lengthI = (int) Math.log10(i) + 1;
            byte[] array = new byte[lengthI];
            int temp = i;
            for (int j = 0; j < array.length; j++)
            {
                array[j] = (byte) (temp % 10);
                temp /= 10;
            }
            int sum = 0;
            for (int j = 0; j < array.length; j++)
            {
                sum += massData[array.length][array[j]];
            }
            if (sum == i) list.add(i);
        }

        Collections.sort(list);
        result = new int[list.size()];
        for (int i = 0; i < list.size(); i++){
            result[i] = list.get(i);
        }
        return result;
    }

    public static void main(String[] args)
    {
        Long t0 = System.currentTimeMillis();
        long memoryStart = Runtime.getRuntime().freeMemory();
        System.out.println(Arrays.toString(getNumbers(Integer.MAX_VALUE)));
        Long t1 = System.currentTimeMillis();
        long memoryEnd = Runtime.getRuntime().freeMemory();
        long memoTaken = memoryStart - memoryEnd; // итог получаем байты.
        System.out.println(memoTaken);
        System.out.println("Time need to create the arrray = " + (t1 - t0));
    }
}
