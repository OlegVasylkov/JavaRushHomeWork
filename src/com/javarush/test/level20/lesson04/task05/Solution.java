package com.javarush.test.level20.lesson04.task05;

import java.io.*;

/* Как сериализовать что-то свое?
Сделайте так, чтобы сериализация класса Object была возможной
*/
public class Solution {
    public static class Object implements Serializable {
        public String string1;
        public String string2;
    }

    public static int countStrings;

    public static class String implements Serializable {
        private final int number;

        public String() {
            number = ++countStrings;
        }

        public void print() {
            System.out.println("string #" + number);
        }
    }

   /* public static void main(java.lang.String[] args) throws IOException, ClassNotFoundException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        Object o = new Object();
        o.string1 = new String();
        o.string2 = new String();
        oos.writeObject(o);
        oos.flush();
        oos.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object o2 = (Object) ois.readObject();
        o2.string2.print();



    }*/
}
