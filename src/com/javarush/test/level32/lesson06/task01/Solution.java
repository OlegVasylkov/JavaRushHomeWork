package com.javarush.test.level32.lesson06.task01;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution
{
    public static void main(String[] args)
    {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword()
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        List<Byte> list = new ArrayList<>();
        for (byte i = 48; i < 58; i++)
            list.add(i);
        for (byte i = 65; i < 91; i++)
            list.add(i);
        for (byte i = 97; i < 123; i++)
            list.add(i);

        boolean hasNum = false;
        boolean hasLowCase = false;
        boolean hasUpCase = false;

        for (int i = 0; i < 8; i++)
        {
            Random random = new Random();
            byte b = list.get(random.nextInt(list.size()));
            if (b < 58 && b >= 48) hasNum = true;
            else if (b < 91 && b >= 65) hasUpCase = true;
            else hasLowCase = true;
            baos.write(b);
        }

        if (hasNum && hasLowCase && hasUpCase)
            return baos;
        else return getPassword();
    }
}
