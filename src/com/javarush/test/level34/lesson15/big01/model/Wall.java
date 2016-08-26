package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by 309_newpower on 04.07.2016.
 */
public class Wall extends CollisionObject
{
    public Wall(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.BLUE);
        graphics.drawRect(getX() - getWidth()/2, getY() - getWidth()/2, getWidth(), getHeight());
        graphics.fillRect(getX() - getWidth()/2, getY() - getWidth()/2, getWidth(), getHeight());

    }
}
