package com.javarush.test.level12.lesson04.task02;

/* print(int) и print(Integer)
Написать два метода: print(int) и print(Integer).
Написать такой код в методе main, чтобы вызвались они оба.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Solution s = new Solution();
        s.print((Integer)5);
        s.print(5);
    }

    //Напишите тут ваши методы

    public void print (int a){
        System.out.println(a);
    }
    public void print (Integer a){
        System.out.println(a);
    }

}
