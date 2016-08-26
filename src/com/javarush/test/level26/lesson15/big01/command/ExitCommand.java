package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import java.util.ResourceBundle;

/**
 * Created by 309_newpower on 18.12.2015.
 */
class ExitCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit");
    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String s = ConsoleHelper.readString();
        if (s.equalsIgnoreCase(res.getString("yes"))){
            ConsoleHelper.writeMessage(res.getString("thank.message"));
        }
    }
}
