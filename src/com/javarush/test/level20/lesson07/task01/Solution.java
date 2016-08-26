package com.javarush.test.level20.lesson07.task01;

import java.io.*;

/* Externalizable для апартаментов
Реализуйте интерфейс Externalizable для класса Apartment
Подумайте, какие поля не нужно сериализовать.
*/
public class Solution {

    public static class Apartment implements Externalizable {

        private String address;
        private int year;

        /**
         * Mandatory public no-arg constructor.
         */
        public Apartment() { super(); }

        public Apartment(String adr, int y) {
            address = adr;
            year = y;
        }

        /**
         * Prints out the fields. used for testing!
         */
        public String toString() {
            return("Address: " + address + "\n" + "Year: " + year);
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException
        {
            out.writeObject(address);
            out.writeInt(year);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
        {
            address = (String)in.readObject();
            year = in.readInt();
        }
    }

   /* public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        Apartment a = new Apartment("asdf", 1);
        oos.writeObject(a);
        oos.flush();
        oos.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Apartment a2 = (Apartment)ois.readObject();
        System.out.println(a2.toString());
    }*/
}
