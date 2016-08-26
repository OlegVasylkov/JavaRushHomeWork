package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import java.util.*;

/**
 * Created by 309_newpower on 24.12.2015.
 */
public class LoginCommand implements Command
{
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login");

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        while (true)
        {
            String s = ConsoleHelper.readString();
            String s2 = ConsoleHelper.readString();

            if (!s.matches("^[0-9]{12}$") || !s2.matches("^[0-9]{4}$")){
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }
            else if (validCreditCards.containsKey(s) && s2.equals(validCreditCards.getString(s)))
            {
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), s));
                break;
            }else {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), s));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            }
        }
    }
}
