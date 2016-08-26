package com.javarush.test.level26.lesson15.big01;


import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator
{
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void addAmount(int denomination, int count)
    {
        if (denominations.containsKey(denomination))
            denominations.put(denomination, denominations.get(denomination) + count);
        else
            denominations.put(denomination, count);
    }

    public int getTotalAmount()
    {
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet())
        {
            sum = sum + (entry.getKey() * entry.getValue());
        }
        return sum;
    }

    public boolean hasMoney()
    {
        return denominations.size() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount)
    {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
    {
        // копия старой мапы, в которой еще не меняли значения, его храним, если окажется, что нам надо бросать NotEnoughMoneyException
        TreeMap<Integer, Integer> copyOfDenominations = new TreeMap<>(new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                return -o1.compareTo(o2);
            }
        });
        copyOfDenominations.putAll(denominations);
        //мапа с результом
        TreeMap<Integer, Integer> result = new TreeMap<>(new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                return -o1.compareTo(o2);
            }
        });

        Integer[] denomination = new Integer[denominations.size()];
        copyOfDenominations.keySet().toArray(denomination);

        int tmp = expectedAmount;
        for (int i = 0; i < denomination.length; i++)
        {
            for (int j = i; j < denomination.length; j++)
            {
                int count = 0;
                while (count < denominations.get(denomination[j]) && tmp >= denomination[j])
                {
                    tmp = tmp - denomination[j];
                    count++;
                }
                if (tmp == 0)
                {
                    result.put(denomination[j], count);
                    break;
                } else if (count > 0 && count < denominations.get(denomination[j]) && tmp < denomination[j])
                {
                    result.put(denomination[j], count);
                    continue;
                } else if (count == denominations.get(denomination[j]) && tmp > 0)
                {
                    result.put(denomination[j], count);
                    continue;
                }
            }
            if (tmp == 0) break;
            else
            {
                result.clear();
                tmp = expectedAmount;
            }
        }

        if (result.size() == 0 && tmp != 0) throw new NotEnoughMoneyException();

        for (Map.Entry<Integer, Integer> map : result.entrySet())
        {
            int numberOfBanknotes = denominations.get(map.getKey()) - map.getValue();
            if (numberOfBanknotes != 0)
                denominations.put(map.getKey(), numberOfBanknotes);
            else denominations.remove(map.getKey());
        }
        return result;
    }


}