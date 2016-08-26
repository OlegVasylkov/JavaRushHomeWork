package com.javarush.test.level36.lesson04.big01.view;

import com.javarush.test.level36.lesson04.big01.controller.Controller;
import com.javarush.test.level36.lesson04.big01.model.ModelData;

/**
 * Created by 309_newpower on 18.07.2016.
 */
public interface View
{
    public void refresh(ModelData modelData);

    public void setController(Controller controller);
}
