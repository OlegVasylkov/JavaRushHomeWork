package com.javarush.test.level21.lesson02.task03;

/* Все гениальное - просто!
Упростить. Переменные не переименовывать, комментарии не оставлять.
*/
public class Solution {
    public static boolean calculate(boolean a, boolean b, boolean c, boolean d) {
        //System.out.println(true && (b||!a) && (c||!a) && (!d||!a) && (a||c) && (b||c) && c && (!d||c) || !b && c || c && d);
        //System.out.println(true && (c||!a||!b) && (!d||!a||!b) && (a||c||!b) && (!d||c||!b) && (!d||!a||c) && c && (!d||c) || c && d);
       // System.out.println(true && (c||!a||!b) && (!d||!a||!b||c) && (a||c||!b) && (!d||c||!b) && (!d||!a||c) && c && (!d||c) &&
        //                    true && (c||!a||!b||d) && true && (a||c||!b||!d) && (!d||c||!b) && true && (c||d) && true);
       // System.out.println((!a||!b||c) && (!a||!b||c||!d) && (a||!b||c) && (c||!b||!d) && (!a||!d||c) && c && (!a||!b||c||d) && (a|!b||c||!d));
        System.out.println(c && ( a && b && !d || !a || !b || d));
        System.out.println(c);

        return             a && b && c && !d || !a && c || !b && c || c && d;
    }

    public static void main(String[] args)
    {
        System.out.println(calculate(true, true, false, false));
    }
}
