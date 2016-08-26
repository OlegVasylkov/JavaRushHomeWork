package com.javarush.test.level37.lesson04.big01.male;


import com.javarush.test.level37.lesson04.big01.Human;

/**
 * Created by 309_newpower on 26.08.2016.
 */
public class MaleFactory
{
    public Human getPerson(int age){
        if (age <= new KidBoy().MAX_AGE)
            return new KidBoy();
        else if (age <= new TeenBoy().MAX_AGE)
            return new TeenBoy();
        else return new Man();
    }
}
