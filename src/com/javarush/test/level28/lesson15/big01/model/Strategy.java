package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.util.List;

/**
 * Created by 309_newpower on 19.01.2016.
 */
public interface Strategy
{
    public List<Vacancy> getVacancies(String searchString);
}
