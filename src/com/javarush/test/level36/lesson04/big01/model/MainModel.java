package com.javarush.test.level36.lesson04.big01.model;

import com.javarush.test.level36.lesson04.big01.bean.User;
import com.javarush.test.level36.lesson04.big01.model.service.UserService;
import com.javarush.test.level36.lesson04.big01.model.service.UserServiceImpl;

import java.util.List;

/**
 * Created by 309_newpower on 18.07.2016.
 */
public class MainModel implements Model
{
    private ModelData modelData = new ModelData();
    private UserService userService = new UserServiceImpl();

    @Override
    public ModelData getModelData()
    {
        return modelData;
    }
    @Override
    public void loadUsers()
    {
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(filter(userService.getUsersBetweenLevels(1, 100)));
    }

    @Override
    public void loadDeletedUsers() {
        modelData.setDisplayDeletedUserList(true);
        List<User> users = userService.getAllDeletedUsers();
        modelData.setUsers(users);
    }

    public void loadUserById(long userId) {
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
    }

    @Override
    public void deleteUserById(long id)
    {
        userService.deleteUser(id);
        modelData.setUsers(filter(userService.getUsersBetweenLevels(1, 100)));
        modelData.setDisplayDeletedUserList(false);
    }

    @Override
    public void changeUserData(String name, long id, int level)
    {
        User user = userService.getUsersById(id);
        user.setName(name);
        user.setLevel(level);
        modelData.setUsers(filter(userService.getUsersBetweenLevels(1, 100)));
    }

    private List<User> filter(List<User> users) {
        return userService.filterOnlyActiveUsers(users);
    }
}
