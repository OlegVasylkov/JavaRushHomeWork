package com.javarush.test.level05.lesson09.task03;

/* Создать класс Dog
Создать класс Dog (собака) с тремя конструкторами:
- Имя
- Имя, рост
- Имя, рост, цвет
*/

public class Dog
{
    public static void main (String[]args){}
    private String name;
    private int age;
    private String gender;
    public Dog (String name){
        this.name=name;
    }
    public Dog(String name, int age){
        this.name=name;
        this.age = age;
    }
    public Dog (String name, int age, String gender){
        this.name=name;
        this.age = age;
        this.gender = gender;
    }//Напишите тут ваш код//Напишите тут ваш код

}
