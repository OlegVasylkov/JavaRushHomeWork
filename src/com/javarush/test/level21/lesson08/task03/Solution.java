package com.javarush.test.level21.lesson08.task03;

/* Запретить клонирование
Разрешите клонировать класс А
Запретите клонировать класс B
Разрешите клонировать класс C
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        public Object clone() throws CloneNotSupportedException
        {
            A a = new A(getI(), getJ());
            return a;
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }
        public String getName() {
            return name;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }
    }

    public static class C extends B {
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        public Object clone() throws CloneNotSupportedException
        {
            C c = new C(super.getI(), super.getJ(), super.getName());
            return c;
        }
    }

//    public static void main(String[] args) throws CloneNotSupportedException
//    {
//        A a = new A(1, 2);
//        A cloneA = (A)a.clone();
//        System.out.println(a);
//        System.out.println(cloneA);
//        C c = new C(1, 2, null);
//        C cloneC = (C)c.clone();
//    }
}
