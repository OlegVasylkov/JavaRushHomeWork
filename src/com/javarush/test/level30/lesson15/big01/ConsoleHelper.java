package com.javarush.test.level30.lesson15.big01;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by 309_newpower on 08.02.2016.
 */
public class ConsoleHelper
{
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString()
    {
        try
        {
            return bufferedReader.readLine();
        }
        catch (IOException e)
        {
            writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            return readString();
        }
    }

    public static int readInt(){
        try
        {
            return Integer.parseInt(readString());
        }catch (NumberFormatException e){
            writeMessage("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            return readInt();
        }
    }
}
