package com.javarush.test.level17.lesson10.bonus02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
//НЕ ПРОШЛО

/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
*/

public class Solution
{
    public static List<Person> allPeople = new ArrayList<Person>();

    static
    {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    static Person person = null;
    static SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    static SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);


    public static void main(String[] args) throws ParseException
    {
        //start here - начни тут
        if (args[0].equals("-c"))
        {
            create(args);
        } else if (args[0].equals("-u"))
        {
            update(args);
        } else if (args[0].equals("-d"))
        {
            delete(args);
        } else if (args[0].equals("-i"))
        {
            inform(args);
        }
    }

    private static synchronized void create(String[] args) throws ParseException
    {
        for (int i = 0; i < args.length - 3; i = i + 3)
        {
            if (args[2 + i].equals("м")) person = Person.createMale(args[1 + i], dateFormat1.parse(args[3 + i]));
            else person = Person.createFemale(args[1 + i], dateFormat1.parse(args[3 + i]));
            allPeople.add(person);
            System.out.println(allPeople.indexOf(person));
        }
    }

    private static synchronized void update(String[] args) throws ParseException
    {
        for (int i = 0; i < args.length - 4; i = i + 4)
        {
            person = allPeople.get(Integer.parseInt(args[1 + i]));
            person.setName(args[2 + i]);
            if (args[3 + i].equals("м")) person.setSex(Sex.MALE);
            else person.setSex(Sex.FEMALE);
            person.setBirthDay(dateFormat1.parse(args[4 + i]));
        }
    }

    private static synchronized void delete(String[] args)
    {
        for (int i = 0; i < args.length-1; i++)
        {
            person = allPeople.get(Integer.parseInt(args[1 + i]));
            person.setSex(null);
            person.setName(null);
            person.setBirthDay(null);
        }

    }

    private static synchronized void inform(String[] args)
    {
        for (int i = 0; i < args.length-1; i++)
        {
            person = allPeople.get(Integer.parseInt(args[1 + i]));
            char sex;
            if (person.getSex().equals(Sex.MALE)) sex = 'м';
            else sex = 'ж';
            System.out.println(person.getName() + " " + sex + " " + dateFormat2.format(person.getBirthDay()));
        }
    }
}
