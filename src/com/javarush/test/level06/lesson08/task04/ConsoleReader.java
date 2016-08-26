package com.javarush.test.level06.lesson08.task04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.IntSummaryStatistics;

/* Класс ConsoleReader
Сделать класс ConsoleReader, у которого будут 4 статических метода:
String readString() – читает с клавиатуры строку
int readInt() – читает с клавиатуры число
double readDouble() – читает с клавиатуры дробное число
void readLn() – ждет нажатия enter [использовать readString()]
*/

public class ConsoleReader
{
    public static String readString() throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String readString = reader.readLine();
        return readString;
        //Напишите тут ваш код

    }

    public static int readInt() throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int readInt = Integer.parseInt(reader.readLine());
        return readInt;
        //Напишите тут ваш код

    }

    public static double readDouble() throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double readInt = Double.parseDouble(reader.readLine());
        return readInt;
        //Напишите тут ваш код
    }

    public static void readLn() throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        if (reader.readLine().equals("\n"))
        {
            System.out.println("ENTER was pressed");
        }
    }
//Напишите тут ваш код


    public static void main(String[] args){}
}
