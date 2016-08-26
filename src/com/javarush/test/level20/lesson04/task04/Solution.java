package com.javarush.test.level20.lesson04.task04;

import java.io.*;

/* Как сериализовать static?
Сделайте так, чтобы сериализация класса ClassWithStatic была возможной
*/
public class Solution
{
    public static class ClassWithStatic implements Serializable {
        public static String staticString = "it's tests static string";
        public int i;
        public int j;
    }

    /*public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        ClassWithStatic c = new ClassWithStatic();
        c.i = 5;
        c.j = 10;
        oos.writeObject(c);
        oos.flush();
        oos.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        ClassWithStatic c2 = (ClassWithStatic)ois.readObject();
        System.out.println(c2.staticString + " " + c2.i + " " + c.j);
    }*/

}
