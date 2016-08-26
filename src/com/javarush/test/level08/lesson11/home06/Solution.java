package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;
import java.util.Vector;

public class Solution
{
    public static void main(String[] args)
    {
        Human Vasya =  new Human("Vasya", true, 45);
        Human Viktor = new Human("Viktor", true, 70);
        Human Viktoria = new Human("Viktoria", false, 65);

        Human Olga = new Human("Olga", false, 40);
        Human Oleg = new Human("Oleg", true, 65);
        Human Oksana = new Human("Oksana", false, 60);

        Human Roma = new Human("Roma", true, 20);
        Human Zina = new Human("Zina", false, 18);
        Human Vova = new Human("Vova", true, 15);

        Vasya.children.add(Roma);
        Vasya.children.add(Zina);
        Vasya.children.add(Vova);
        Olga.children.add(Roma);
        Olga.children.add(Zina);
        Olga.children.add(Vova);
        Viktor.children.add(Vasya);
        Viktoria.children.add(Vasya);
        Oleg.children.add(Olga);
        Oksana.children.add(Olga);

        System.out.println(Viktor.toString());
        System.out.println(Viktoria.toString());
        System.out.println(Oleg.toString());
        System.out.println(Oksana.toString());
        System.out.println(Olga.toString());
        System.out.println(Vasya.toString());
        System.out.println(Roma.toString());
        System.out.println(Zina.toString());
        System.out.println(Vova.toString());
        //Написать тут ваш код
    }

    public static class Human
    {
        //Написать тут ваш код
        private String name;
        private boolean sex;
        private int age;
        private ArrayList<Human> children = new ArrayList<Human>();


        Human (String name, boolean sex, int age){
            this.name = name;
            this.sex = sex;
            this.age=age;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: "+this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", "+child.name;
                }
            }

            return text;
        }
    }

}
