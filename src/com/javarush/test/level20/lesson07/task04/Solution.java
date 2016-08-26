package com.javarush.test.level20.lesson07.task04;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* Serializable Solution
Сериализуйте класс Solution.
Подумайте, какие поля не нужно сериализовать, пометить ненужные поля — transient.
Объект всегда должен содержать актуальные на сегодняшний день данные.
Метод main не участвует в тестировании.
Написать код проверки самостоятельно в методе main:
1) создать файл, открыть поток на чтение (input stream) и на запись(output stream)
2) создать экземпляр класса Solution - savedObject
3) записать в поток на запись savedObject (убедитесь, что они там действительно есть)
4) создать другой экземпляр класса Solution с другим параметром
5) загрузить из потока на чтение объект - loadedObject
6) проверить, что savedObject.string равна loadedObject.string
7) обработать исключения
*/
public class Solution implements Serializable
{
    public static void main(String[] args)
    {
        //System.out.println(new Solution(4));
        try
        {
            File data = new File("d:\\tempreture.txt");
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(data));
            Solution savedObject = new Solution(4);
            outputStream.writeObject(savedObject);
            outputStream.flush();
            outputStream.close();
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(data));
            Solution loadedObject = (Solution) inputStream.readObject();
            inputStream.close();
            System.out.println(savedObject.toString().equals(loadedObject.toString()));
//            System.out.println(loadedObject);
//            System.out.println(loadedObject.currentDate);
//            System.out.println(loadedObject.pattern);
//            System.out.println(loadedObject.string);
//            System.out.println(loadedObject.temperature);

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    String string;
    //transient private static final long serialVersionUID = 170501993;

    //public  Solution(){}

    public Solution(int temperature)
    {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString()
    {
        return this.string;
    }
}
