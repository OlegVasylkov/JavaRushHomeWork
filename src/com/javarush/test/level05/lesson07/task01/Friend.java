package com.javarush.test.level05.lesson07.task01;

/* Создать класс Friend
Создать класс Friend (друг) с тремя инициализаторами (тремя методами initialize):
- Имя
- Имя, возраст
- Имя, возраст, пол
*/

public class Friend
{
    public static void main (String[]args){}
    private String name = null;
    private int age = 0;
    private String gender = null;
    public void initialize (String name){
        this.name=name;
    }
    public void initialize (String name, int age){
        this.name=name;
        this.age = age;
    }
    public void initialize (String name, int age, String gender){
        this.name=name;
        this.age = age;
        this.gender = gender;
    }
    //Напишите тут ваш код

}
