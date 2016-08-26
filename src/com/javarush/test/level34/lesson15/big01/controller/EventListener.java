package com.javarush.test.level34.lesson15.big01.controller;

import com.javarush.test.level34.lesson15.big01.model.Direction;

/**
 * Created by 309_newpower on 04.07.2016.
 */
public interface EventListener
{
    public void move(Direction direction);
    public void restart();
    public void startNextLevel();
    public void levelCompleted(int level);
}
