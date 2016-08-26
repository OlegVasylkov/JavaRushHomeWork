package com.javarush.test.level26.lesson15.big01;


import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/**
 * Created by 309_newpower on 17.12.2015.
 */
public class ConsoleHelper
{
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common");

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException{
        try
        {
            String s = reader.readLine();
            if (s.equalsIgnoreCase(res.getString("operation.EXIT"))){
                throw new InterruptOperationException();
            }
            return s;
        }
        catch (IOException e) {
        }
        return null;
    }

    public static String askCurrencyCode() throws InterruptOperationException
    {
        writeMessage(res.getString("choose.currency.code"));
        String currency = readString();
        if (currency.length() == 3)
            return currency.toUpperCase();
        else {
            writeMessage(res.getString("invalid.data"));
            return askCurrencyCode();
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        String[] strings = readString().split(" ");
        for (String s : strings){
            try
            {
                if (Integer.parseInt(s) < 0) throw new NumberFormatException();
                if (strings.length != 2) throw new NumberFormatException();
            }catch (NumberFormatException e){
                writeMessage(res.getString("invalid.data"));
                return getValidTwoDigits(currencyCode);
            }
        }
        return strings;
    }

    public static Operation askOperation() throws InterruptOperationException
    {
        writeMessage(res.getString("choose.operation") + " 1 — " + res.getString("operation.INFO") + ", 2 — "
                + res.getString("operation.DEPOSIT") + ", 3 — " + res.getString("operation.WITHDRAW") + ", 4 — " + res.getString("operation.EXIT"));
        String string = readString();
        Operation operation = null;
        try
        {
            operation = Operation.getAllowableOperationByOrdinal(Integer.parseInt(string));
            return operation;
        }catch (IllegalArgumentException e){
            writeMessage(res.getString("invalid.data"));
            return askOperation();
        }

    }

    public static void printExitMessage(){
        writeMessage(res.getString("the.end"));
    }
}
