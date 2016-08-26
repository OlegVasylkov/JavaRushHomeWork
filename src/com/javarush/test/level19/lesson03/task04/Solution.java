package com.javarush.test.level19.lesson03.task04;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные одного человека.
*/

public class Solution {
    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner scanner;
        PersonScannerAdapter(Scanner scanner){
            this.scanner = scanner;
        }

        @Override
        public Person read() throws IOException, ParseException
        {
            SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
            String lastName = scanner.next();
            String firstName = scanner.next();
            String middleName = scanner.next();
            Date date = format.parse(scanner.next() +scanner.next()+scanner.next());
            Person person = new Person(firstName, middleName, lastName, date);
            return person;
        }

        @Override
        public void close() throws IOException
        {
            scanner.close();
        }
    }
}
