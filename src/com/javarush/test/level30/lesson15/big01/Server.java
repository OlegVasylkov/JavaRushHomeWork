package com.javarush.test.level30.lesson15.big01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 309_newpower on 08.02.2016.
 */
public class Server
{
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    private static class Handler extends Thread
    {
        private Socket socket;

        private Handler(Socket socket)
        {
            this.socket = socket;
        }

        public void run()
        {
            SocketAddress socketAddress = socket.getRemoteSocketAddress();
            ConsoleHelper.writeMessage("Установленно новое соединение" + socketAddress);
            String userName = null;
            try (Connection connection = new Connection(socket)){
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            }
            catch (IOException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом");
            }
            catch (ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом");
            }finally
            {
                if (userName != null)
                {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED));
                }
            }
            ConsoleHelper.writeMessage("сообщение с удаленным адресом закрыто");
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException
        {
            Message messageSend = new Message(MessageType.NAME_REQUEST);
            connection.send(messageSend);
            Message messageReceive = connection.receive();
            String name;
            if (messageReceive.getType().equals(MessageType.USER_NAME))
            {
                name = messageReceive.getData();
                if (name != null && name.length() > 0 && !connectionMap.containsKey(name))
                {
                    connectionMap.put(name, connection);
                    connection.send(new Message(MessageType.NAME_ACCEPTED));
                } else name = serverHandshake(connection);
            } else name = serverHandshake(connection);
            return name;
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException
        {
            for (Map.Entry<String, Connection> entry : connectionMap.entrySet())
            {
                if (!userName.equals(entry.getKey()))
                    connection.send(new Message(MessageType.USER_ADDED, entry.getKey()));
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException
        {
            while (true)
            {
                Message recievedMessage = connection.receive();
                if (recievedMessage.getType() == MessageType.TEXT)
                {
                    sendBroadcastMessage(new Message(MessageType.TEXT, String.format("%s: %s", userName, recievedMessage.getData())));
                } else
                {
                    ConsoleHelper.writeMessage("Сообщение не отправленно");
                }
            }
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
            try {
                entry.getValue().send(message);
            }
            catch (IOException e) {
                ConsoleHelper.writeMessage("Сообщение не может быть отправленно пользователю: " + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {
        ConsoleHelper.writeMessage("Введите порт сервера");
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())) {
            ConsoleHelper.writeMessage("Server Socket создан");
            while (true) {
                Socket s = serverSocket.accept();
                new Handler(s).start();
            }
        }
        catch (IOException e) {
            ConsoleHelper.writeMessage(e.getMessage());
        }
    }

}
