package com.javarush.test.level22.lesson13.task01;

import java.util.ArrayList;
import java.util.StringTokenizer;

/* StringTokenizer
Используя StringTokenizer разделить query на части по разделителю delimiter.
Пример,
getTokens("level22.lesson13.task01", ".") == {"level22", "lesson13", "task01"}
*/
public class Solution {
    public static String [] getTokens(String query, String delimiter) {
        ArrayList<String> arrayList = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        while (tokenizer.hasMoreTokens()){
            arrayList.add(tokenizer.nextToken());
        }
        String [] mass = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++){
            mass[i] = arrayList.get(i);
        }
        return mass;
    }
}
