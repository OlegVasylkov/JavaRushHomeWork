package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки. Не использовать try-with-resources

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import java.io.*;


public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = bufferedReader.readLine();
        String fileName2 = bufferedReader.readLine();
        bufferedReader.close();
        BufferedReader reader = new BufferedReader(new FileReader(fileName1));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName2));
        int count = 0;
        while (reader.ready()){
            String[] mass = reader.readLine().split(" ");
            for (int i = 0; i < mass.length; i++){
                try
                {
                    int j = Integer.parseInt(mass[i]);
                    //System.out.println(j);
                    if (count == 0){
                        writer.write("" + j);
                        count++;
                    }
                    else writer.write(" " + j);
                }catch (NumberFormatException e){
                }
            }
        }
        reader.close();
        writer.close();

    }
}
