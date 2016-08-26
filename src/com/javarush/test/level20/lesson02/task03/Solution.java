package com.javarush.test.level20.lesson02.task03;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception
    {
        //implement this method - реализуйте этот метод
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            FileInputStream inputStream = new FileInputStream(fileName);
            reader.close();
            load(inputStream);
            inputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties property = new Properties();
        for(Map.Entry<String, String> pair : properties.entrySet())
        {
            String key = pair.getKey(); // ключ
            String value = pair.getValue(); //значение
            property.setProperty(key, value);
        }
        property.store(outputStream, null);
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties property = new Properties();
        property.load(inputStream);
        for (String s : property.stringPropertyNames()){
            properties.put(s, property.getProperty(s));
        }
    }
}
