package com.javarush.test.level37.lesson04.big01.female;

import com.javarush.test.level37.lesson04.big01.AbstractFactory;
import com.javarush.test.level37.lesson04.big01.Human;

/**
 * Created by 309_newpower on 26.08.2016.
 */
public class FemaleFactory implements AbstractFactory
    {
    public Human getPerson(int age){
        if (age <= new KidGirl().MAX_AGE)
            return new KidGirl();
        else if (age <= new TeenGirl().MAX_AGE)
            return new TeenGirl();
        else return new Woman();
    }
}
