package com.javarush.test.level20.lesson02.task01;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Читаем и пишем в файл: Human
Реализуйте логику записи в файл и чтения из файла для класса Human
Поле name в классе Human не может быть пустым
В файле your_file_name.tmp может быть несколько объектов Human
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution
{
    public static void main(String[] args)
    {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try
        {

            File your_file_name = new File("d:\\file1.txt");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            System.out.println(ivanov.equals(somePerson));

            outputStream.close();
            inputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Human
    {
        public String name;
        public List<Asset> assets = new ArrayList<Asset>();

        public Human()
        {
        }

        public Human(String name, Asset... assets)
        {
            this.name = name;
            if (assets != null)
            {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        public void save(OutputStream outputStream) throws Exception
        {
            //implement this method - реализуйте этот метод
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println(name);
            String isAssentPresent = assets != null ? Integer.toString(assets.size()) : "noAssets";
            printWriter.println(isAssentPresent);
            if (assets != null)
            {
                for (Asset a : assets)
                {
                    printWriter.println(a.getName());
                    String isPricePresent = a.getPrice() != 0.0 ? "yesPrise" : "noPrice";
                    printWriter.println(isPricePresent);
                    if (a.getPrice() != 0.0)
                        printWriter.println(String.valueOf(a.getPrice()));
                }
            }
            printWriter.close();
        }

        public void load(InputStream inputStream) throws Exception
        {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            name = reader.readLine();
            String isAssentPresent = reader.readLine();
            if (!isAssentPresent.equals("noAssets"))
            {
                while (reader.ready())
                {
                    Asset asset = new Asset(reader.readLine());
                    String isPricePresent = reader.readLine();
                    if (isPricePresent.equals("yesPrise"))
                    {
                        asset.setPrice(Double.parseDouble(reader.readLine()));
                    }
                    assets.add(asset);
                }
            }
            reader.close();
        }
    }
}
