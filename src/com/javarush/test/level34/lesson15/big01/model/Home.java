package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by 309_newpower on 04.07.2016.
 */
public class Home extends GameObject
{
    public Home(int x, int y)
    {
        super(x, y);
        setHeight(2);
        setWidth(2);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.RED);
        graphics.drawOval(getX() - getWidth()/2, getY() - getWidth()/2, getWidth(), getHeight());
    }
}
