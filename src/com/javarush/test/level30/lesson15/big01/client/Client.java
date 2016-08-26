package com.javarush.test.level30.lesson15.big01.client;


import com.javarush.test.level30.lesson15.big01.Connection;
import com.javarush.test.level30.lesson15.big01.ConsoleHelper;
import com.javarush.test.level30.lesson15.big01.Message;
import com.javarush.test.level30.lesson15.big01.MessageType;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by 309_newpower on 09.02.2016.
 */
public class Client
{
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    public void run(){
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        try {
            synchronized (this) {
                this.wait();
            }
        }
        catch (InterruptedException e) {
            ConsoleHelper.writeMessage("Программа прервана");
            return;
        }

        if (clientConnected)
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
        else
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");

        while (clientConnected){
            String s = ConsoleHelper.readString();
            if (s.equals("exit"))break;
            if (shouldSentTextFromConsole())sendTextMessage(s);
        }
    }

    protected String getServerAddress()
    {
        ConsoleHelper.writeMessage("Введите адресс сервера");
        return ConsoleHelper.readString();
    }

    protected int getServerPort(){
        ConsoleHelper.writeMessage("Введите порт сервера");
        return ConsoleHelper.readInt();
    }

    protected String getUserName(){
        ConsoleHelper.writeMessage("Введите имя");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSentTextFromConsole(){
        return true;
    }

    protected SocketThread getSocketThread(){
        return new SocketThread();
    }

    protected void sendTextMessage(String text){
        try
        {
            connection.send(new Message(MessageType.TEXT, text));
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage("Сообщение не доставленно");
            clientConnected = false;
        }
    }

    public class SocketThread extends Thread{

        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage(String.format("%s присоеденился к чату", userName));
        }

        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage(String.format("%s покинул чат", userName));
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected){
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this)
            {
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException{
            while (true){
                Message message = connection.receive();
                if (message.getType() == MessageType.NAME_REQUEST){
                    String name = getUserName();
                    Message newMessage = new Message(MessageType.USER_NAME, name);
                    connection.send(newMessage);
                }
                else if (message.getType() == MessageType.NAME_ACCEPTED){
                    notifyConnectionStatusChanged(true);
                    break;
                }
                else
                    throw new IOException("Unexpected MessageType");
            }
        }
        protected void clientMainLoop() throws IOException, ClassNotFoundException{
            while (true)
            {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT)
                    processIncomingMessage(message.getData());
                else if (message.getType() == MessageType.USER_ADDED)
                    informAboutAddingNewUser(message.getData());
                else if (message.getType() == MessageType.USER_REMOVED)
                    informAboutDeletingNewUser(message.getData());
                else
                    throw new IOException("Unexpected MessageType");
            }
        }
        @Override
        public void run()
        {
            String serverAddress = getServerAddress();
            int serverPort = getServerPort();
            try
            {
                Socket socket = new Socket(serverAddress, serverPort);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                notifyConnectionStatusChanged(false);
            }
            catch (ClassNotFoundException e)
            {e.printStackTrace();
                notifyConnectionStatusChanged(false);
            }


        }
    }
}
