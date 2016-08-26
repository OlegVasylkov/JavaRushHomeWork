package com.javarush.test.level32.lesson08.home01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by 309_newpower on 03.06.2016.
 */
public class CustomInvocationHandler implements InvocationHandler
{
    private SomeInterfaceWithMethods interfaceWithMethods;

    public CustomInvocationHandler(SomeInterfaceWithMethods interfaceWithMethods){
        this.interfaceWithMethods = interfaceWithMethods;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        System.out.println(method.getName() + " in");
        method.invoke(interfaceWithMethods, args);
        System.out.println(method.getName() + " out");
        return null;
    }
}
