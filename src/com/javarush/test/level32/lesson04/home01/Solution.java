package com.javarush.test.level32.lesson04.home01;

import java.io.*;

/* Читаем из потока
Реализуйте логику метода getAllDataFromInputStream. Он должен вернуть StringWriter, содержащий все данные из переданного потока.
Возвращаемый объект ни при каких условиях не должен быть null.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        if (is == null)return new StringWriter();
        StringWriter stringWriter = new StringWriter();
            while (is.available() > 0)
            {
                byte[] bytes = new byte[is.available()];
                is.read(bytes);
                stringWriter.write(new String(bytes));
            }
        return stringWriter;
    }
}
