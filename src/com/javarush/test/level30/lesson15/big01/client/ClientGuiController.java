package com.javarush.test.level30.lesson15.big01.client;

/**
 * Created by 309_newpower on 27.02.2016.
 */
public class ClientGuiController extends Client
{
    private ClientGuiModel model = new ClientGuiModel();
    private ClientGuiView view = new ClientGuiView(this);
    public class GuiSocketThread extends SocketThread
    {
        @Override
        protected void processIncomingMessage(String message)
        {
            model.setNewMessage(message);
            view.refreshMessages();
        }
        @Override
        protected void informAboutAddingNewUser(String userName)
        {
            model.addUser(userName);
            view.refreshUsers();
        }
        @Override
        protected void informAboutDeletingNewUser(String userName)
        {
            model.deleteUser(userName);
            view.refreshUsers();
        }
        @Override
        protected void notifyConnectionStatusChanged(boolean clientConnected)
        {
            view.notifyConnectionStatusChanged(clientConnected);
        }
    }
    @Override
    protected SocketThread getSocketThread()
    {
        return new GuiSocketThread();
    }
    @Override
    public void run()
    {
        getSocketThread().run();
    }
    @Override
    protected String getServerAddress()
    {
        return view.getServerAddress();
    }
    @Override
    protected int getServerPort()
    {
        return view.getServerPort();
    }

    public ClientGuiModel getModel()
    {
        return model;
    }

    public static void main(String[] args)
    {
        ClientGuiController clientGuiController = new ClientGuiController();
        clientGuiController.run();
    }
    @Override
    protected String getUserName()
    {
        return view.getUserName();
    }
}