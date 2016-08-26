package com.javarush.test.level22.lesson09.task02;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/* Формируем Where
Сформируйте часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.
Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}
Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"
*/
public class Solution {

    public static StringBuilder getCondition(Map<String, String> params) {
        StringBuilder string = new StringBuilder();
        for (Map.Entry<String, String> param : params.entrySet()){
            if (param.getValue() != null)
            {
                if (string.length()>0)string.append(" and ");
                string.append(param.getKey()).append(" = '").append(param.getValue()).append("'");
            }
        }
        return string;
    }

//    public static void main(String[] args)
//    {
//        LinkedHashMap<String, String> params = new LinkedHashMap<>();
//        params.put("name", "Ivanov");
//        params.put("country", null);
//        params.put("city", "Kiev");
//        params.put("age", null);
//        System.out.println(getCondition(params));
//    }
}
