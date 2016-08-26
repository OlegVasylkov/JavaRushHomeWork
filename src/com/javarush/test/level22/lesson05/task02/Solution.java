package com.javarush.test.level22.lesson05.task02;

/* Между табуляциями
Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией.
На некорректные данные бросить исключение TooShortStringException.
Класс TooShortStringException не менять.
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException{
        if (string == null) throw new TooShortStringException();
        int ind1 = string.indexOf("\t")+1;
        int ind2 = string.indexOf("\t", ind1);
        if (ind2 < 0)throw new TooShortStringException();
        return string.substring(ind1, ind2);
    }

    public static class TooShortStringException extends Exception {
    }

//    public static void main(String[] args) throws TooShortStringException
//    {
//        String s = "a \t sdf";
//        System.out.println(getPartOfString(s));
//
//    }
}
