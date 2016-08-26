package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by 309_newpower on 04.07.2016.
 */
public class Player extends CollisionObject implements Movable
{
    public Player(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.YELLOW);
        graphics.drawOval(getX() - getWidth()/2, getY() - getWidth()/2, getWidth(), getHeight());
        graphics.fillOval(getX() - getWidth()/2, getY() - getWidth()/2, getWidth(), getHeight());
    }

    @Override
    public void move(int x, int y)
    {
//        System.out.println(x + "   " + y);
        setX(getX() + x);
        setY(getY() + y);
    }
}
