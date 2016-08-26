package com.javarush.test.level35.lesson10.bonus01;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/* ClassLoader - что это такое?
Реализуйте логику метода getAllAnimals.
Аргумент метода pathToAnimals - это абсолютный путь к директории, в которой хранятся скомпилированные классы.
Путь не обязательно содержит / в конце.
НЕ все классы наследуются от интерфейса Animal.
НЕ все классы имеют публичный конструктор без параметров.
Только для классов, которые наследуются от Animal и имеют публичный конструктор без параметров, - создать по одному объекту.
Добавить созданные объекты в результирующий сет и вернуть.
Метод main не участвует в тестировании.
*/
public class Solution
{
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException
    {
//        Set<? extends Animal> allAnimals = getAllAnimals("C://pathToClasses/");
        Set<? extends Animal> allAnimals = getAllAnimals("C://Users//309_newpower//Desktop//не работа//Project//JavaRushHomeWork//out//production//JavaRushHomeWork//com//javarush//test//level35//lesson10//bonus01//data//");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals)
    {
        if (!pathToAnimals.endsWith("\\") && !pathToAnimals.endsWith("/"))
            pathToAnimals = pathToAnimals + "/";

        final String FINALpathToAnimals = pathToAnimals;

        Set<Animal> set = new HashSet<>();
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> findClass(String className) throws ClassNotFoundException {
                try {
                    Path path = Paths.get(FINALpathToAnimals + className + ".class");
                    byte[] buffer = Files.readAllBytes(path);
                    return defineClass(null, buffer, 0, buffer.length);
                }
                catch (IOException ex)
                {
                    return super.findClass(className);
                }
            }
        };

        File folder = new File(pathToAnimals);
        for (File file : folder.listFiles()){
            try {
                Class clz = loader.loadClass(file.getName().substring(0, file.getName().length()-6));
                Object object = clz.newInstance();
                if (object instanceof Animal)
                    set.add((Animal)object);
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return set;
    }
}
