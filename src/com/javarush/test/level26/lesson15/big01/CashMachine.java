package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.command.CommandExecutor;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import java.util.Locale;

public class CashMachine
{
    public static final String RESOURCE_PATH = "com.javarush.tests.level26.lesson15.big01.resources.";

    public static void main(String[] args)
    {
        try
        {
            Locale.setDefault(Locale.ENGLISH);
            Operation operation = null;
            CommandExecutor.execute(Operation.LOGIN);
            do
            {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            }
            while (operation != Operation.EXIT);
        }catch (InterruptOperationException e){
            ConsoleHelper.printExitMessage();
        }
    }
}
