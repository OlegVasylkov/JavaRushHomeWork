package com.javarush.test.level15.lesson12.home01;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/* Разные методы для разных типов
1. Считать с консоли данные, пока не введено слово "exit".
2. Для каждого значения вызвать метод print. Если значение:
2.1. содержит точку '.', то вызвать метод print для Double;
2.2. больше нуля, но меньше 128, то вызвать метод print для short;
2.3. больше либо равно 128, то вызвать метод print для Integer;
2.4. иначе, вызвать метод print для String.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напиште тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<String> list = new LinkedList();
        String s = reader.readLine();
        while (!s.equals("exit")){
            list.add(s);
            s = reader.readLine();
        }
        for (int i = 0; i < list.size(); i++){
            try{
                if (list.get(i).contains("."))
                    print(Double.parseDouble(list.get(i)));
                else {
                    int param = Integer.parseInt(list.get(i));
                    if (param > 0 && param < 128)
                        print((short)param);
                    else if (param >= 128)
                        print(param);
                    else print(Integer.toString(param));
                }
            }catch (Exception e){
                print(list.get(i));
            }
        }

    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
