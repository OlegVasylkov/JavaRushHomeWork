package com.javarush.test.level06.lesson05.task02;

/* Классы Cat и Dog и метод finalize для каждого
В каждом классе Cat и Dog написать метод finalize, который выводит на экран текст о том, что такой-то объект уничтожен.
*/

public class Cat {
    public static void main(String[] args){}

    String name;

    /*Cat(String name) {
        this.name = name;
    }*/
    protected void finalize() throws Throwable
    {
        System.out.println(name + "destroyed");
    }
}
//Напишите тут ваш код
class Dog
{
    String name1;

    /*Dog(String name1) {
        this.name1 = name1;
    }*/
    //Напишите тут ваш код
    protected void finalize() throws Throwable
    {
        System.out.println(name1 + "destroyed");
    }//Напишите тут ваш код

}
