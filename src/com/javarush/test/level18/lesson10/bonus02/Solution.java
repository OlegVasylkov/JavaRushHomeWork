package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.Scanner;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        String s = "";
        String mass = "";
        while (scanner.hasNext())
        {
            s = scanner.nextLine();
            mass = mass + s + "\r\n";
        }
        scanner.close();
        int maxId = Integer.parseInt(s.substring(0, 8));
        int ourId = maxId + 1;
        String id = ourId + "";
        while (id.length() < 8) id = id + " ";
        String productName = args[1];
        if (productName.length() > 30) productName.substring(0, 30);
        else
        {
            while (productName.length() < 30)
                productName = productName + " ";
        }
        String price = args[2];
        while (price.length() < 8) price = price + " ";
        String quantity = args[3];
        while (quantity.length() < 4) quantity = quantity + " ";
        Writer writer = new FileWriter(fileName);

        writer.write((mass + id + productName + price + quantity).toCharArray());
        writer.close();
    }
}
