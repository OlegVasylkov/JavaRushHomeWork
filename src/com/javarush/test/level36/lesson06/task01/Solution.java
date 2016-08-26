package com.javarush.test.level36.lesson06.task01;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/* Найти класс по описанию
1. Реализует интерфейс List
2. Является приватным статическим классом внутри популярного утилитного класса
3. Доступ по индексу запрещен - кидается исключение IndexOutOfBoundsException
4. Используйте рефлекшн, чтобы добраться до искомого класса
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class<?>[] classes = Collections.class.getDeclaredClasses();
        for (Class clazz : classes){
            int mods = clazz.getModifiers();
            if(Modifier.isStatic(mods) && Modifier.isPrivate(mods) && List.class.isAssignableFrom(clazz)){
                if (clazz.getName().contains("EmptyList"))return clazz;
//                try {
//                    Constructor[] constructors = clazz.getDeclaredConstructors();
//                    constructors[0].setAccessible(true);
//                    Constructor constructor = constructors[0];
//                    Object c = constructor.newInstance();
//
//                    System.out.println(constructors);
//
//                    Method method = clazz.getDeclaredMethod("get", int.class);
//                    method.setAccessible(true);
//                    method.invoke(c,0);
//                }
//                catch (IndexOutOfBoundsException e){
//                    return clazz;
//                }
//                catch (Exception e)
//                {
//                }
            }
        }
        return null;

    }
}
