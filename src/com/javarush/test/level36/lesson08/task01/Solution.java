package com.javarush.test.level36.lesson08.task01;

import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортировать буквы по алфавиту и вывести на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то вывести их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрыть потоки.

Пример 1 данных входного файла:
zBk yaz b-kN
Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB
Пример 2 вывода:
abc

Подсказка: использовать TreeSet
*/
public class Solution
{
    public static void main(String[] args) throws IOException {
        try (FileReader fileReader = new FileReader(args[0])) {
            TreeSet<String> characters = new TreeSet<>();
            char[] chars = new char[1];
            while (fileReader.ready()) {
                fileReader.read(chars);
                if (test((chars[0] + "").toLowerCase()))
                    characters.add((chars[0] + "").toLowerCase());
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (String character : characters)
                stringBuffer.append(character);
            if (stringBuffer.length()>5)
            System.out.println(stringBuffer.toString().substring(0,5));
            else System.out.println(stringBuffer.toString());
        }
    }

    public static boolean test(String testString)
    {
        Pattern p = Pattern.compile("[a-z]");
        Matcher m = p.matcher(testString);
        return m.matches();
    }
}
