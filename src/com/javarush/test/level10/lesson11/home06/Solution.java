package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

public class Solution
{
    public static void main(String[] args)
    {
        new Human("Raul");
        new Human("Taras", 15);
        new Human("Pavel", 20, true);
        new Human("Pavel", 20, true, "Kyev");
        new Human("Pavel", 20, true, "Kyev", "White");
        new Human("Pavel", 20, true, "Kyev", "White", "Ukrainian");
        new Human("Vika", false);
        new Human(40, true, "White");
        new Human("Viktor", true, "Russian");
        new Human("Stepan", "Kyev");
    }

    public static class Human
    {
        //напишите тут ваши переменные и конструкторы
        private String name;
        private int age;
        private boolean sexMale;
        private String address;
        private String color;
        private String nation;

        Human(String name){
            this.name = name;
        }
        Human(String name,int age){
            this.name=name;
            this.age = age;
        }
        Human(String name, int age, boolean sexMale){
            this.name = name;
            this.age = age;
            this.sexMale = sexMale;
        }
        Human(String name, int age, boolean sexMale, String address){
            this.name = name;
            this.age = age;
            this.sexMale = sexMale;
            this.address = address;
        }
        Human(String name, int age, boolean sexMale, String address, String color){
            this.name = name;
            this.age = age;
            this.sexMale = sexMale;
            this.address = address;
            this.color = color;
        }
        Human(String name, int age, boolean sexMale, String address, String color, String nation){
            this.name = name;
            this.age = age;
            this.sexMale = sexMale;
            this.address = address;
            this.color = color;
            this.nation = nation;
        }
        Human(String name, boolean sexMale){
            this.name = name;
            this.sexMale = sexMale;
        }
        Human(int age, boolean sexMale, String color){
            this.age = age;
            this.sexMale = sexMale;
            this.color = color;
        }
        Human(String name, boolean sexMale, String nation){
            this.name = name;
            this.sexMale = sexMale;
            this.nation = nation;
        }
        Human(String name, String address){
            this.name = name;
            this.address = address;
        }
    }
}
