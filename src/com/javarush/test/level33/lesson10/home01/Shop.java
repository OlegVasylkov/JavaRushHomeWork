package com.javarush.test.level33.lesson10.home01;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 309_newpower on 14.06.2016.
 */

@XmlType(name = "shop")
@XmlRootElement
public class Shop
{
    @XmlElementWrapper(name = "goods",nillable = true)
    public List<String> names = new ArrayList<>() ;

    public int count;

    public double profit;

    public List<String> secretData = new ArrayList<>();

    @Override
    public String toString()
    {
        return "Shop{" +
                "goods=" + names +
                ", count=" + count +
                ", profit=" + profit +
                ", secretData=" + secretData +
                '}';
    }
}
