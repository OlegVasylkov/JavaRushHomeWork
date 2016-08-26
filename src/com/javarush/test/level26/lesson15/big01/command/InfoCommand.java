package com.javarush.test.level26.lesson15.big01.command;


import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import java.util.Collection;
import java.util.*;

class InfoCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info");
    @Override
    public void execute()
    {
        Collection<CurrencyManipulator> manipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        int count = 0;
        for (CurrencyManipulator manipulator : manipulators)
        {
            if (manipulator.hasMoney())
                ConsoleHelper.writeMessage(res.getString("before") + manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
            else
                count++;
        }
        if (count == manipulators.size())
            ConsoleHelper.writeMessage(res.getString("no.money"));
    }
}
