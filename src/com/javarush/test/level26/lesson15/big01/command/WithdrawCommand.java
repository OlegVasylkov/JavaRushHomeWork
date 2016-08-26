package com.javarush.test.level26.lesson15.big01.command;


import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by 309_newpower on 18.12.2015.
 */
class WithdrawCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw");
    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currency = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currency);
        do
        {
            try
            {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                int sum = Integer.parseInt(ConsoleHelper.readString());
                if (sum <= 0) {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                    continue;
                }
                if (!currencyManipulator.isAmountAvailable(sum)) {
                    ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                    continue;
                }

                Map<Integer, Integer> cash = currencyManipulator.withdrawAmount(sum);

                for (Map.Entry<Integer, Integer> map : cash.entrySet())
                {
                    ConsoleHelper.writeMessage(String.format("\t%s - %s", map.getKey(), map.getValue()));
                }
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sum, currency));
                break;
            }
            catch (NotEnoughMoneyException e)
            {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            }
            catch (NumberFormatException e)
            {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            }catch (ConcurrentModificationException e){

            }
        }
        while (true);

    }
}
