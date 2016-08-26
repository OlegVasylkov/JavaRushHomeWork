package com.javarush.test.level26.lesson15.big01;

/**
 * Created by 309_newpower on 17.12.2015.
 */
public enum Operation
{
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;



    public static Operation getAllowableOperationByOrdinal(Integer i){
        if (i == 1)return INFO;
        else if (i == 2)return DEPOSIT;
        else if (i == 3) return WITHDRAW;
        else if (i == 4) return EXIT;
        else throw new IllegalArgumentException();
    }
}
