package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by 309_newpower on 04.07.2016.
 */
public class Box extends CollisionObject implements Movable
{
    public Box(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.ORANGE);
        int leftUpperCornerX = getX() - getWidth() / 2;
        int leftUpperCornerY = getY() - getHeight() / 2;
        graphics.drawRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
        graphics.drawLine(leftUpperCornerX, leftUpperCornerY, leftUpperCornerX+getWidth(), leftUpperCornerY+getHeight());
    }

    @Override
    public void move(int x, int y)
    {
        setX(getX() + x);
        setY(getY() + y);
    }
}
