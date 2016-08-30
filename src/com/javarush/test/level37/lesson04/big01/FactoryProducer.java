package com.javarush.test.level37.lesson04.big01;

import com.javarush.test.level37.lesson04.big01.female.FemaleFactory;
import com.javarush.test.level37.lesson04.big01.male.MaleFactory;

/**
 * Created by 309_newpower on 29.08.2016.
 */
public class FactoryProducer
{
    public static enum HumanFactoryType{
        MALE, FEMALE
    }

    public static AbstractFactory getFactory(HumanFactoryType type){
        if (type.equals(HumanFactoryType.FEMALE))return new FemaleFactory();
        else if (type.equals(HumanFactoryType.MALE)) return new MaleFactory();
        return null;
    }
}
