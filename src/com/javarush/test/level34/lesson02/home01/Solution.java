package com.javarush.test.level34.lesson02.home01;

import java.text.DecimalFormat;
import java.util.Locale;

/* Рекурсия для мат.выражения
На вход подается строка - математическое выражение.
Выражение включает целые и дробные числа, скобки (), пробелы, знак отрицания -, возведение в степень ^, sin(x), cos(x), tan(x)
Для sin(x), cos(x), tan(x) выражение внутри скобок считать градусами, например, cos(3 + 19*3)=0.5
Степень задается так: a^(1+3) и так a^4, что эквивалентно a*a*a*a.
С помощью рекурсии вычислить выражение и количество математических операций. Вывести через пробел результат в консоль.
Результат выводить с точностью до двух знаков, для 0.33333 вывести 0.33, использовать стандартный принцип округления.
Не создавайте статические переменные и поля класса.
Не пишите косвенную рекурсию.
Пример, состоящий из операций sin * - + * +:
sin(2*(-5+1.5*4)+28)
Результат:
0.5 6
*/
public class Solution
{
    public static void main(String[] args)
    {
        Solution solution = new Solution();
        solution.recursion("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
    }

    public void recursion(final String expression, int countOperation)
    {
        //implement
        Locale.setDefault(Locale.ENGLISH);
        DecimalFormat df = new DecimalFormat("#.##");

        countOperation++;
        String string = expression.replaceAll(" ", "");
        int count = 0;

        if (string.startsWith("sin"))
            Math.sin(0);
        else if (string.startsWith("cos"))
            Math.cos(0);
        else if(string.startsWith("tan"))
            Math.tan(0);

        if (string.startsWith("-"))
            ;
        else if(string.startsWith("."))
            ;
        else if(string.startsWith("+"))
            ;
        else if(string.startsWith("*"))
            ;
        else if(string.startsWith("^"))
            ;
        //TODO
        if (string.contains("("))
        {
            int index = string.indexOf("(");
            string = string.substring(index + 1);
            int nextInd = 0;
            if (string.contains("("))
                nextInd = string.indexOf("(");
            int endIndex = string.indexOf(")");
            if (nextInd > endIndex)
            {
                string = string.substring(index);
                recursion(string, countOperation);
            }
        }

    }

    public Solution()
    {
        //don't delete
    }
}
