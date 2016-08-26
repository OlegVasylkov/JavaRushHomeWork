package com.javarush.test.level22.lesson05.task01;

/* Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
Сигнатуру метода getPartOfString не менять.
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException
    {
            if (string == null) throw new TooShortStringException();
            int startInd = string.indexOf(" ");
            int endInd = -1;
            for (int i = 0; i < 4; i++)
            {
                if (endInd + 1 < string.length())
                {
                    endInd = string.indexOf(" ", endInd + 1);
                }
                if (endInd < 0) break;
            }
            if ((endInd < 0) || (endInd == startInd) || (endInd + 1 == string.length()))
                throw new TooShortStringException();
            else if (endInd + 1 < string.length() && string.indexOf(" ", endInd + 1) > 0)
                return string.substring(startInd + 1, string.indexOf(" ", endInd + 1));
            else return string.substring(startInd + 1);
    }

    public static class TooShortStringException extends Throwable {
    }
}
