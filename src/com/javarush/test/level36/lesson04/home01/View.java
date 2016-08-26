package com.javarush.test.level36.lesson04.home01;

import java.util.List;

/**
 * Created by 309_newpower on 11.07.2016.
 */
public class View
{
    private Controller controller = new Controller();

    public void fireEventShowData()
    {
        System.out.println(controller.onDataListShow());
    }
}
