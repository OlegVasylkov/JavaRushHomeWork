package com.javarush.test.level14.lesson06.home01;

/**
 * Created by 309_newpower on 31.08.2015.
 */
public class MoldovanHen extends Hen
{
    @Override
    int getCountOfEggsPerMonth()
    {
        return 20;
    }
    String getDescription()
    {
        return super.getDescription()+" Моя страна - "+ Country.MOLDOVA + ". Я несу "+ getCountOfEggsPerMonth()+" яиц в месяц.";
    }
}
