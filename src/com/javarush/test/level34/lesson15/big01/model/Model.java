package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by 309_newpower on 01.07.2016.
 */
public class Model
{
    public static int FIELD_SELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 10;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("src/com/javarush/test/level34/lesson15/big01/res/levels.txt"));

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects()
    {
        return gameObjects;
    }

    public void restartLevel(int level)
    {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart()
    {
        restartLevel(currentLevel);
    }

    public void startNextLevel()
    {
        currentLevel++;
        restart();
    }

    public void move(Direction direction)
    {
        Player player = gameObjects.getPlayer();

        if (checkWallCollision(player, direction))
            return;
        if (checkBoxCollision(direction))
            return;

        switch (direction)
        {
            case LEFT:
                player.move(-FIELD_SELL_SIZE, 0);
                break;
            case RIGHT:
                player.move(FIELD_SELL_SIZE, 0);
                break;
            case UP:
                player.move(0, -FIELD_SELL_SIZE);
                break;
            case DOWN:
                player.move(0, FIELD_SELL_SIZE);
                break;
        }
        checkCompletion();

    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction)
    {
        for (Wall wall : gameObjects.getWalls())
        {
            if (gameObject.isCollision(wall, direction))
                return true;
        }
        return false;
    }

    public boolean checkBoxCollision(Direction direction)
    {
        Player player = gameObjects.getPlayer();
        Set<Box> boxes = gameObjects.getBoxes();

        for (Box box : boxes)
        {
            if (player.isCollision(box, direction))
            {
                if (checkWallCollision(box, direction)) return true;
                for (Box box2 : boxes)
                {
                    if (box.isCollision(box2, direction))
                        return true;
                }

                switch (direction)
                {
                    case LEFT:
                        box.move(-FIELD_SELL_SIZE, 0);
                        break;
                    case RIGHT:
                        box.move(FIELD_SELL_SIZE, 0);
                        break;
                    case UP:
                        box.move(0, -FIELD_SELL_SIZE);
                        break;
                    case DOWN:
                        box.move(0, FIELD_SELL_SIZE);
                        break;
                }
                return false;
            }
        }
        return false;
    }

    public void checkCompletion()
    {
        Set<Box> boxes = gameObjects.getBoxes();
        Set<Home> homes = gameObjects.getHomes();
        Map<Box, Home> completion = new HashMap<>();
        for (Box box : boxes)
        {
            for (Home home : homes)
                if (box.getX() == home.getX() && box.getY() == home.getY())
                {
                    completion.put(box, home);
                    continue;
                }
        }
        if (completion.size() == boxes.size())
            eventListener.levelCompleted(currentLevel);

    }
}
