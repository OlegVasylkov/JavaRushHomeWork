package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

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
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        if (args[0].equals("-u")) updateItem(fileName, args);
        else if (args[0].equals("-d")) deleteItem(fileName, args);
    }

    private static void updateItem(String fileName, String[] args) throws IOException
    {
        String product = "";
        String mass1 = "";
        String mass2 = "";
        Boolean flag = false;
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        while (scanner.hasNext())
        {
            String s = scanner.nextLine();
            if (args[1].length() < 8 && s.startsWith(args[1] + " "))
            {
                product = s;
                flag = true;
                s = "";
            } else if (args[1].length() == 8 && s.startsWith(args[1]))
            {
                product = s;
                flag = true;
                s = "";
            }
            if (flag) mass2 = mass2 + s + "\r\n";
            else mass1 = mass1 + s + "\r\n";
        }
        scanner.close();

        String id = product.substring(0, 8);
        String productName = args[2];
        if (productName.length() > 30) productName.substring(0, 30);
        else
        {
            while (productName.length() < 30)
                productName = productName + " ";
        }
        String price = args[3];
        while (price.length() < 8) price = price + " ";
        String quantity = args[4];
        while (quantity.length() < 4) quantity = quantity + " ";
        Writer writer = new FileWriter(fileName);
        writer.write((mass1 + id + productName + price + quantity + mass2.substring(0, mass2.lastIndexOf("\r\n"))).toCharArray());
        writer.close();
    }
    private static void deleteItem(String fileName, String[] args) throws IOException{
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        String mass1 = "";
        String mass2 = "";
        while (scanner.hasNext())
        {
            String s = scanner.nextLine();
            Boolean flag = false;
            if (args[1].length() < 8 && s.startsWith(args[1] + " "))
            {
                flag = true;
                s = "";
            } else if (args[1].length() == 8 && s.startsWith(args[1]))
            {
                flag = true;
                s = "";
            }
            if (flag) mass2 = mass2 + s + "\r\n";
            else mass1 = mass1 + s + "\r\n";
        }
        scanner.close();

        Writer writer = new FileWriter(fileName);
        writer.write((mass1 + mass2.substring(0, mass2.lastIndexOf("\r\n"))).toCharArray());
        writer.close();
    }
}
