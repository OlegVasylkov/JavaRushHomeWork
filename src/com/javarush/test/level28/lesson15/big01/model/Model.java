package com.javarush.test.level28.lesson15.big01.model;import com.javarush.test.level28.lesson15.big01.view.View;import com.javarush.test.level28.lesson15.big01.vo.Vacancy;import java.util.ArrayList;import java.util.List;/** * Created by 309_newpower on 25.01.2016. */public class Model{    private Provider[] providers;    private View view;    public Model(View view, Provider... providers)    {        if (view == null || providers.length == 0) throw new IllegalArgumentException();        else        {            this.providers = providers;            this.view = view;        }    }    public void selectCity(String city)    {        List<Vacancy> vacancies = new ArrayList<>();        for (Provider provider : providers)        {            vacancies.addAll(provider.getJavaVacancies(city));        }        view.update(vacancies);    }////    public void scan()//    {//        List<Vacancy> list = new ArrayList<>();//        for (Provider provider : providers)//        {//            try//            {//                list.addAll(provider.getJavaVacancies("Kiev"));//            }catch (NullPointerException ignore){//                /*NOP*///            }//        }//        System.out.println(list.size());//    }}