package com.javarush.test.level33.lesson15.big01.strategies;

/**
 * Created by 309_newpower on 15.06.2016.
 */
public interface StorageStrategy
{
    public boolean containsKey(Long key);
    public boolean containsValue(String value);
    public void put(Long key, String value);
    public Long getKey(String value);
    public String getValue(Long key);
}
