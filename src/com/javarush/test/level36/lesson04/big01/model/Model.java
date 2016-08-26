package com.javarush.test.level36.lesson04.big01.model;

/**
 * Created by 309_newpower on 18.07.2016.
 */
public interface Model
{
    public ModelData getModelData();

    public void loadUsers();

    public void loadDeletedUsers();

    public void loadUserById(long userId);

    public void deleteUserById(long id);

    public void changeUserData(String name, long id, int level);
}
