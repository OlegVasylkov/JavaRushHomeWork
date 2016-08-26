package com.javarush.test.level15.lesson04.task02;

/* ООП - Перегрузка
Перегрузите метод printMatrix 8 различными способами. В итоге должно получиться 10 различных методов printMatrix.
*/

public class Solution {
    public static void main(String[] args) {
        printMatrix(2, 3, "8");
    }
//1
    public static void printMatrix(int m, int n, String value) {
        System.out.println("Заполняем объектами String");
        printMatrix(m, n, (Object) value);
    }
//2
    public static void printMatrix(int m, int n, Object value) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(value);
            }
            System.out.println();
        }
        printMatrix(m, n, Integer.parseInt((String) value));
    }
    //3
    public static void printMatrix(int m, int n, int value){
        System.out.println("int: " + value);
        printMatrix(m, n, (byte)value);
    }
    //4
    public static void printMatrix(int m, int n, byte value){
        System.out.println("byte: " + value);
        printMatrix(m, n, (short)value);
    }
    //5
    public static void printMatrix(int m, int n, short value){
        System.out.println("shot: " + value);
        printMatrix(m, n, (double) value);
    }
    //6
    public static void printMatrix(int m, int n, double value){
        System.out.println("double: " + value);
        printMatrix(m, n, (float) value);
    }
    //7
    public static void printMatrix(int m, int n, float value){
        System.out.println("float: " + value);
        printMatrix(m,n,(long)value);
    }
    //8
    public static void printMatrix(int m, int n, long value)
    {
        System.out.println("long: " + value);

    }
    //9
    public static void printMatrix(int m, Object n, long value){

    }
    //10
    public static void printMatrix(int m, double n, double value){

    }

}
