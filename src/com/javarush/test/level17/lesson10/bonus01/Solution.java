package com.javarush.test.level17.lesson10.bonus01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
//ПРОШЛО

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException{
        //start here - начни тут
        Person person = null;
        String name = "";
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        if (args[0].equals("-c")){
            for (int i = 1; i < args.length-2; i++){
                name = name + " " + args[i];
            }
            name = name.substring(1, name.length());
            if (args[args.length-2].equals("м"))person = Person.createMale(name, dateFormat1.parse(args[args.length-1]));
            else person = Person.createFemale(name, dateFormat1.parse(args[args.length-1]));
            allPeople.add(person);
            System.out.println(allPeople.indexOf(person));
        }
        else if (args[0].equals("-u")){
            for (int i = 2; i < args.length-2; i++){
                name = name + " " + args[i];
            }
            name = name.substring(1, name.length());
            person = allPeople.get(Integer.parseInt(args[1]));
            person.setName(name);
            if (args[args.length-2].equals("м"))person.setSex(Sex.MALE);
            else person.setSex(Sex.FEMALE);
            person.setBirthDay(dateFormat1.parse(args[args.length - 1]));

        }
        else if (args[0].equals("-d")){
            person = allPeople.get(Integer.parseInt(args[1]));
            person.setSex(null);
            person.setName(null);
            person.setBirthDay(null);

        }
        else if (args[0].equals("-i")){
            person = allPeople.get(Integer.parseInt(args[1]));
            char sex;
            if (person.getSex().equals(Sex.MALE))sex = 'м';
            else sex = 'ж';
            System.out.println(person.getName()+" "+sex+" "+dateFormat2.format(person.getBirthDay()));
        }
        //System.out.println(allPeople.get(0).getName()+allPeople.get(0).getSex()+allPeople.get(0).getBirthDay());
    }
}
