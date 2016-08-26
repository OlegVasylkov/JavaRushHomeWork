package com.javarush.test.level20.lesson10.home02;

import java.io.*;

/* Десериализация
На вход подается поток, в который записан сериализованный объект класса A либо класса B.
Десериализуйте объект в методе getOriginalObject предварительно определив, какого именно типа там объект.
Реализуйте интерфейс Serializable где необходимо.
*/
public class Solution implements Serializable {
    public A getOriginalObject(ObjectInputStream objectStream) throws IOException, ClassNotFoundException
    {
        Object object = objectStream.readObject();
        if (object instanceof Solution.B) return (B) object;
        else return (A) object;
    }

    public class A implements Serializable {
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

//    public static void main(String[] args) throws IOException, ClassNotFoundException
//    {
//        Solution solution = new Solution();
//        Solution.A b = solution.new B();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream out = new ObjectOutputStream(baos);
//        out.writeObject(b);
//        out.flush();
//        out.close();
//        System.out.println(solution.getOriginalObject(new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()))));
//
//    }

}
