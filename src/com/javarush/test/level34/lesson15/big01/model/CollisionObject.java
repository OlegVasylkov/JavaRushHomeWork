package com.javarush.test.level34.lesson15.big01.model;

/**
 * Created by 309_newpower on 04.07.2016.
 */
public abstract class CollisionObject extends GameObject
{
    public CollisionObject(int x, int y)
    {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction){
        boolean result = false;
        switch (direction){
            case LEFT:
                if (gameObject.getX() == getX() - Model.FIELD_SELL_SIZE && gameObject.getY() == getY())
                    result = true;
                break;
            case RIGHT:
                if (gameObject.getX() == getX() + Model.FIELD_SELL_SIZE && gameObject.getY() == getY())
                    result = true;
                break;
            case UP:
                if (gameObject.getX() == getX() && gameObject.getY() == getY() - Model.FIELD_SELL_SIZE)
                    result = true;
                break;
            case DOWN:
                if (gameObject.getX() == getX() && gameObject.getY() == getY() + Model.FIELD_SELL_SIZE)
                    result = true;
                break;
        }
        return result;
    }
}
