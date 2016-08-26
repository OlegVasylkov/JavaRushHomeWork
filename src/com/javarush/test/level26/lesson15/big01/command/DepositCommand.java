package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by 309_newpower on 18.12.2015.
 */
class DepositCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit");

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        while (true)
        {
            try
            {
                String[] nominalCount = ConsoleHelper.getValidTwoDigits(code);
                CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
                manipulator.addAmount(Integer.parseInt(nominalCount[0]), Integer.parseInt(nominalCount[1]));
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), Integer.parseInt(nominalCount[0])*Integer.parseInt(nominalCount[1]), code));
                break;
            }
            catch (NumberFormatException ex)
            {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
    }
}
