package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
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

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            /*User user = new User();
            user.setFirstName("Mark");
            user.setLastName("Y");
            user.setBirthDate(new Date());
            user.setMale(true);
            user.setCountry(User.Country.UKRAINE);

            javaRush.users.add(user);*/
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
           /* System.out.println(javaRush.equals(loadedObject));
            User user1 = loadedObject.users.get(0);
            System.out.println(user1.getFirstName());
            System.out.println(user1.getLastName());
            System.out.println(user1.getBirthDate());
            System.out.println(user1.isMale());
            System.out.println(user1.getCountry());*/

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

    public static class JavaRush
    {
        public List<User> users = new ArrayList<User>();

        public void save(OutputStream outputStream) throws Exception
        {
            //implement this method - реализуйте этот метод
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            PrintWriter printWriter = new PrintWriter(outputStream);
            for (User user : users)
            {
                printWriter.println("New User");
                printWriter.println(user.getFirstName());
                printWriter.println(user.getLastName());
                printWriter.println(format.format(user.getBirthDate()));
                printWriter.println(user.isMale());
                printWriter.println(String.valueOf(user.getCountry() != null ? user.getCountry().getDisplayedName() : null));
            }
            printWriter.close();
        }

        public void load(InputStream inputStream) throws Exception
        {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            while (reader.ready())
            {
                if (reader.readLine().equals("New User"))
                {
                    User user = new User();
                    String firstName = reader.readLine();
                    if (!firstName.equals("null")) user.setFirstName(firstName);
                    String lastName = reader.readLine();
                    if (!lastName.equals("null")) user.setLastName(lastName);
                    String bd = reader.readLine();
                    if (!bd.equals("null")) user.setBirthDate(format.parse(bd));
                    user.setMale(Boolean.parseBoolean(reader.readLine()));
                    String country = reader.readLine();
                    if (country.equals(User.Country.UKRAINE.getDisplayedName())) user.setCountry(User.Country.UKRAINE);
                    else if (country.equals(User.Country.RUSSIA.getDisplayedName())) user.setCountry(User.Country.RUSSIA);
                    else if (country.equals(User.Country.OTHER.getDisplayedName())) user.setCountry(User.Country.OTHER);
                    users.add(user);
                }
            }
            reader.close();
        }
    }
}
