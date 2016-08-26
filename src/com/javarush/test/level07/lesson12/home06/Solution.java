package com.javarush.test.level07.lesson12.home06;

/* Семья
Создай класс Human с полями имя(String), пол(boolean),возраст(int), отец(Human), мать(Human). Создай объекты и заполни их так, чтобы получилось: Два дедушки, две бабушки, отец, мать, трое детей. Вывести объекты на экран.
Примечание:
Если написать свой метод String toString() в классе Human, то именно он будет использоваться при выводе объекта на экран.
Пример вывода:
Имя: Аня, пол: женский, возраст: 21, отец: Павел, мать: Катя
Имя: Катя, пол: женский, возраст: 55
Имя: Игорь, пол: мужской, возраст: 2, отец: Михаил, мать: Аня
…
*/

public class Solution
{
    public static void main(String[] args)
    {
        Human grandFa1 = new Human("Granny Tom", true, 70, null, null);
        Human grandFa2 = new Human("Granny Jon", true, 65, null, null);
        Human grandMa1 = new Human("Granny Zoe", false, 63, null, null);
        Human grandMa2 = new Human("Granny Sofia", false, 58, null, null);
        Human father1 = new Human("Bil", true, 40, grandFa1, grandMa1);
        Human mother1 = new Human("Rebeca", false, 33, grandFa2, grandMa2);
        Human kid1= new Human("Pol", true, 13, father1, mother1);
        Human kid2= new Human("Pollee", false, 10, father1, mother1);
        Human kid3= new Human("Ron", true, 3, father1, mother1);

        System.out.println(grandFa1);
        System.out.println(grandFa2);
        System.out.println(grandMa1);
        System.out.println(grandMa2);
        System.out.println(father1);
        System.out.println(mother1);
        System.out.println(kid1);
        System.out.println(kid2);
        System.out.println(kid3);

        //Написать тут ваш код
    }

    public static class Human
    {
        private String name;
        private boolean sex;
        private int age;
        private Human father;
        private Human mother;

        Human (String name, boolean sex, int age, Human father, Human mother){
            this.name=name;
            this.age=age;
            this.sex=sex;
            this.father=father;
            this.mother=mother;
        }
        //Написать тут ваш код

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }

}
